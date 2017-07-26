package com.mday.client.ui.render;

import com.mday.client.game.Units;
import com.mday.client.ui.Surface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing the units.
 */
public class UnitRenderer implements Consumer<Surface> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnitRenderer.class);

    @Nonnull
    private final Units units;

    /**
     * Create an instance of this class.
     *
     * @param units the container of all the known units
     */
    public UnitRenderer(@Nonnull final Units units) {
        this.units = units;
    }

    @Override
    public void accept(@Nonnull final Surface surface) {
        // TODO
    }
}
