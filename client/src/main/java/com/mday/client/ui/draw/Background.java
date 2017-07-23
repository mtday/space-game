package com.mday.client.ui.draw;

import com.mday.client.ui.Surface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing the background.
 */
public class Background implements Consumer<Surface> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Background.class);

    @Override
    public void accept(@Nonnull final Surface surface) {
        final Graphics graphics = surface.getBufferedImage().getGraphics();
        graphics.setColor(new Color(10, 10, 10));
        graphics.fillRect(0, 0, surface.getWidth(), surface.getHeight());
    }
}
