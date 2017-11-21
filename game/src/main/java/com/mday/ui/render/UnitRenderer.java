package com.mday.ui.render;

import static com.mday.model.UnitType.SHIP;
import static java.util.Optional.ofNullable;

import com.mday.game.Units;
import com.mday.ui.Surface;
import com.mday.ui.SurfaceConsumer;
import com.mday.ui.render.unit.ShipRenderer;
import com.mday.model.Unit;
import com.mday.model.UnitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing the units.
 */
public class UnitRenderer implements SurfaceConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnitRenderer.class);

    @Nonnull
    private final Units units;

    @Nonnull
    private final ConcurrentHashMap<UnitType, BiConsumer<Unit, Surface>> unitRenderers = new ConcurrentHashMap<>();

    /**
     * Create an instance of this class.
     *
     * @param units the container of all the known units
     * @throws IOException if there is a problem loading an image
     */
    public UnitRenderer(@Nonnull final Units units) throws IOException {
        this.units = units;

        unitRenderers.put(SHIP, new ShipRenderer());
    }

    @Override
    public void accept(@Nonnull final Surface surface) {
        units.getAll().stream()
                .filter(unit -> surface.getCoordinateSystem().contains(unit.getLocation(), unit.getRadius()))
                .forEach(unit -> renderUnit(unit, surface));
    }

    private void renderUnit(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        ofNullable(unitRenderers.get(unit.getUnitType())).ifPresent(consumer -> consumer.accept(unit, surface));
    }
}
