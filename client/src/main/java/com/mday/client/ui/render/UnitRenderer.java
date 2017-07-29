package com.mday.client.ui.render;

import static com.mday.common.model.UnitType.SHIP;
import static java.util.Optional.ofNullable;

import com.mday.client.game.Units;
import com.mday.client.ui.Surface;
import com.mday.client.ui.SurfaceConsumer;
import com.mday.client.ui.render.unit.ShipRenderer;
import com.mday.common.model.Unit;
import com.mday.common.model.UnitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     */
    public UnitRenderer(@Nonnull final Units units) {
        this.units = units;

        unitRenderers.put(SHIP, new ShipRenderer());
    }

    @Override
    public void accept(@Nonnull final Surface surface) {
        units.getAll().stream()
                .filter(unit -> surface.getCoordinateSystem().contains(unit.getLocation()))
                .forEach(unit -> renderUnit(unit, surface));
    }

    private void renderUnit(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        ofNullable(unitRenderers.get(unit.getUnitType())).ifPresent(consumer -> consumer.accept(unit, surface));
    }
}
