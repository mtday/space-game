package com.mday.client.game;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.UnitAddEvent;
import com.mday.client.event.type.UnitRemoveEvent;
import com.mday.common.model.Unit;
import com.mday.common.model.UnitType;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Manages the units that are known in the game as a mapping between unique unit id and unit.
 */
public class Units implements EventConsumer {
    @Nonnull
    private final ConcurrentHashMap<String, Unit> byId = new ConcurrentHashMap<>();
    @Nonnull
    private final ConcurrentHashMap<UnitType, ConcurrentSkipListSet<Unit>> byType = new ConcurrentHashMap<>();
    @Nonnull
    private final ConcurrentHashMap<String, ConcurrentSkipListSet<Unit>> byOwner = new ConcurrentHashMap<>();

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
     * Retrieve all the units owned by a specific user.
     *
     * @param owner the id of the owner for which units will be retrieved
     * @return the requested units
     */
    @Nonnull
    public Set<Unit> getByOwner(@Nonnull final String owner) {
        return Optional.<Set<Unit>>ofNullable(byOwner.get(owner)).orElseGet(Collections::emptySet);
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof UnitAddEvent) {
            final UnitAddEvent unitAddEvent = (UnitAddEvent) event;
            byId.put(unitAddEvent.getUnit().getId(), unitAddEvent.getUnit());
            byType.computeIfAbsent(unitAddEvent.getUnit().getType(),
                    ignored -> new ConcurrentSkipListSet<>()).add(unitAddEvent.getUnit());
            byOwner.computeIfAbsent(unitAddEvent.getUnit().getOwner(),
                    ignored -> new ConcurrentSkipListSet<>()).add(unitAddEvent.getUnit());
        } else if (event instanceof UnitRemoveEvent) {
            final UnitRemoveEvent unitRemoveEvent = (UnitRemoveEvent) event;
            byId.remove(unitRemoveEvent.getUnit().getId());
            Optional.ofNullable(byType.get(unitRemoveEvent.getUnit().getType()))
                    .ifPresent(set -> set.remove(unitRemoveEvent.getUnit()));
            Optional.ofNullable(byOwner.get(unitRemoveEvent.getUnit().getOwner()))
                    .ifPresent(set -> set.remove(unitRemoveEvent.getUnit()));
        }
    }
}
