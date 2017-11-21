package com.mday.ui.render.debug;

import com.mday.ui.Surface;
import com.mday.ui.SurfaceConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing the current surface scale.
 */
public class ScaleRenderer implements SurfaceConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScaleRenderer.class);

    @Override
    public void accept(@Nonnull final Surface surface) {
        final Graphics2D graphics = surface.getDrawGraphics();
        graphics.setColor(new Color(200, 200, 200));

        final String scale = String.format("Scale: %.2f", surface.getCoordinateSystem().getScale());
        graphics.drawString(scale, 10, surface.getHeight() - 10);
    }
}
