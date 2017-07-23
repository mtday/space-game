package com.mday.client.ui;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;

/**
 * Represents the display surface on which the game graphics will be drawn.
 */
public class Surface extends Canvas {
    private static final long serialVersionUID = 1L;

    private final int width;
    private final int height;
    @Nonnull
    private final transient BufferedImage bufferedImage;

    /**
     * Create an instance of this class.
     *
     * @param width the width of the display surface
     * @param height the height of the display surface
     */
    public Surface(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.bufferedImage = new BufferedImage(width, height, TYPE_INT_ARGB);
        super.setSize(width, height);
    }

    /**
     * Retrieve the width of this surface.
     *
     * @return the width of the surface
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Retrieve the height of this surface.
     *
     * @return the height of the surface
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Retrieve the image on which to draw.
     *
     * @return the image on which to draw
     */
    @Nonnull
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
