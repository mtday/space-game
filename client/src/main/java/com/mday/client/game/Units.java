package com.mday.client.game;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.unit.*;
import com.mday.client.ui.CoordinateSystem;
import com.mday.common.model.Location;
import com.mday.common.model.Unit;
import com.mday.common.model.UnitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Manages the units that are known in the game as a mapping between unique unit id and unit.
 */
public class Units implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Units.class);

    @Nonnull
    private final CoordinateSystem coordinateSystem;
    @Nonnull
    private final UnitMover unitMover;

    @Nonnull
    private final ConcurrentHashMap<String, Unit> byId = new ConcurrentHashMap<>();
    @Nonnull
    private final ConcurrentHashMap<UnitType, ConcurrentSkipListSet<Unit>> byType = new ConcurrentHashMap<>();

    private boolean unitsSelected = false;

    /**
     * Create an instance of this class.
     *
     * @param coordinateSystem the coordinate system used to manage locations on the draw surface
     * @param unitMover        the mover responsible for relocating units
     */
    public Units(@Nonnull final CoordinateSystem coordinateSystem, @Nonnull final UnitMover unitMover) {
        this.coordinateSystem = coordinateSystem;
        this.unitMover = unitMover;
    }

    /**
     * Retrieve a collection of all the known units.
     *
     * @return a collection of all the known units
     */
    @Nonnull
    public Collection<Unit> getAll() {
        return byId.values();
    }

    /**
     * Retrieve a collection of all the selected units.
     *
     * @return a collection of all the selected units
     */
    @Nonnull
    public Collection<Unit> getSelected() {
        return getAll().stream().filter(Unit::isSelected).collect(toList());
    }

    /**
     * Retrieve a specific unit by unique id.
     *
     * @param id the unique id of the unit to retrieve
     * @return the requested unit if found, otherwise {@code null}
     */
    @Nullable
    public Unit getById(@Nonnull final String id) {
        return byId.get(id);
    }

    /**
     * Retrieve all the units of a specific type.
     *
     * @param type the type of units to retrieve
     * @return the requested units
     */
    @Nonnull
    public Set<Unit> getByType(@Nonnull final UnitType type) {
        return Optional.<Set<Unit>>ofNullable(byType.get(type)).orElseGet(Collections::emptySet);
    }

    /**
     * Whether any units are currently selected.
     *
     * @return whether any units are currently selected
     */
    public boolean isUnitsSelected() {
        return unitsSelected;
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof UnitAddEvent) {
            final UnitAddEvent unitAddEvent = (UnitAddEvent) event;
            unitsSelected |= unitAddEvent.getUnit().isSelected();
            byId.put(unitAddEvent.getUnit().getId(), unitAddEvent.getUnit());
            byType.computeIfAbsent(unitAddEvent.getUnit().getUnitType(), ignored -> new ConcurrentSkipListSet<>())
                    .add(unitAddEvent.getUnit());
        } else if (event instanceof UnitRemoveEvent) {
            final UnitRemoveEvent unitRemoveEvent = (UnitRemoveEvent) event;
            byId.remove(unitRemoveEvent.getUnit().getId());
            ofNullable(byType.get(unitRemoveEvent.getUnit().getUnitType()))
                    .ifPresent(set -> set.remove(unitRemoveEvent.getUnit()));
            unitsSelected = getAll().stream().anyMatch(Unit::isSelected);
        } else if (event instanceof UnitSelectEvent) {
            final UnitSelectEvent unitSelectionEvent = (UnitSelectEvent) event;
            final Location topLeft = coordinateSystem.toLocation(unitSelectionEvent.getTopLeft());
            final Location botRight = coordinateSystem.toLocation(unitSelectionEvent.getBottomRight());
            getAll().forEach(unit ->
                    unit.setSelected(unit.getLocation().isInside(topLeft, botRight, unit.getRadius())));
            unitsSelected = getAll().stream().anyMatch(Unit::isSelected);
        } else if (event instanceof UnitDeselectEvent) {
            getAll().forEach(unit -> unit.setSelected(false));
            unitsSelected = false;
        } else if (event instanceof UnitMoveEvent) {
            final UnitMoveEvent unitMoveEvent = (UnitMoveEvent) event;
            final List<Unit> selectedMoveable = getSelected().stream().filter(Unit::isMoveable).collect(toList());
            unitMover.add(selectedMoveable, coordinateSystem.toLocation(unitMoveEvent.getDestination()));
        }
    }
}
