package com.mday.ui.render;

import com.mday.ui.Surface;
import com.mday.ui.SurfaceConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing the background.
 */
public class BackgroundRenderer implements SurfaceConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackgroundRenderer.class);

    @Override
    public void accept(@Nonnull final Surface surface) {
        final Graphics2D graphics = surface.getDrawGraphics();
        graphics.setColor(new Color(10, 10, 10));
        graphics.fillRect(0, 0, surface.getWidth(), surface.getHeight());
    }
}
