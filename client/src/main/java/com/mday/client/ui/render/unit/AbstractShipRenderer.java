package com.mday.client.ui.render.unit;

import com.mday.client.ui.Surface;
import com.mday.common.model.Ship;

import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

/**
 * The base class for unit renderers.
 */
public abstract class AbstractShipRenderer implements BiConsumer<Ship, Surface> {
    @Override
    public void accept(@Nonnull final Ship ship, @Nonnull final Surface surface) {
    }
}
