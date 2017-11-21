package com.mday.ui;

import javax.annotation.Nonnull;

/**
 * Defines the interface for consuming a surface to render graphics on it.
 */
@FunctionalInterface
public interface SurfaceConsumer {
    /**
     * Accept a surface on which to draw game graphics.
     *
     * @param surface the surface to draw on
     */
    void accept(@Nonnull Surface surface);
}
