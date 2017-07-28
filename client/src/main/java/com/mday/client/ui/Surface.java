package com.mday.client.ui;

import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import com.mday.common.model.Location;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;

/**
 * Represents the display surface on which the game graphics will be drawn.
 */
public class Surface extends Canvas {
    private static final long serialVersionUID = 1L;

    @Nonnull
    private final transient BufferedImage bufferedImage;

    private int zoom = 14;

    @Nonnull
    private final transient Location center;

    /**
     * Create an instance of this class.
     *
     * @param width the width of the display surface
     * @param height the height of the display surface
     * @param center the location describing the center point in this surface
     */
    public Surface(final int width, final int height, @Nonnull final Location center) {
        super.setSize(width, height);
        this.center = center;

        bufferedImage = new BufferedImage(width, height, TYPE_INT_ARGB);

        final Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        graphics.setRenderingHints(new RenderingHints(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON));
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

    /**
     * Retrieve the current zoom level.
     *
     * @return the current zoom level
     */
    public int getZoom() {
        return zoom;
    }

    /**
     * Zoom in on the coordinate indicated.
     *
     * @param x the X coordinate on which to zoom in on
     * @param y the Y coordinate on which to zoom in on
     */
    public void zoomIn(final int x, final int y) {
        zoom++;
        // TODO: update center location
    }

    /**
     * Zoom out from the coordinate indicated.
     *
     * @param x the X coordinate from which to zoom out of
     * @param y the Y coordinate from which to zoom out of
     */
    public void zoomOut(final int x, final int y) {
        zoom--;
        // TODO: update center location
    }

    /**
     * Retrieve the center location of this surface.
     *
     * @return the center location of this surface.
     */
    @Nonnull
    public Location getCenter() {
        return center;
    }

    /**
     * Retrieve the top left location of this surface.
     *
     * @return the top left location of this surface.
     */
    @Nonnull
    public Location getTopLeft() {
        return center.add((int) (-getSize().getWidth() / 2), (int) (-getSize().getHeight() / 2));
    }

    /**
     * Retrieve the top mid location of this surface.
     *
     * @return the top mid location of this surface.
     */
    @Nonnull
    public Location getTopMid() {
        return center.add(0, (int) (-getSize().getHeight() / 2));
    }

    /**
     * Retrieve the top right location of this surface.
     *
     * @return the top right location of this surface.
     */
    @Nonnull
    public Location getTopRight() {
        return center.add((int) (getSize().getWidth() / 2), (int) (-getSize().getHeight() / 2));
    }

    /**
     * Retrieve the mid left location of this surface.
     *
     * @return the mid left location of this surface.
     */
    @Nonnull
    public Location getMidLeft() {
        return center.add((int) (-getSize().getWidth() / 2), 0);
    }

    /**
     * Retrieve the mid right location of this surface.
     *
     * @return the mid right location of this surface.
     */
    @Nonnull
    public Location getMidRight() {
        return center.add((int) (getSize().getWidth() / 2), 0);
    }

    /**
     * Retrieve the bottom left location of this surface.
     *
     * @return the bottom left location of this surface.
     */
    @Nonnull
    public Location getBottomLeft() {
        return center.add((int) (-getSize().getWidth() / 2), (int) (getSize().getHeight() / 2));
    }

    /**
     * Retrieve the bottom mid location of this surface.
     *
     * @return the bottom mid location of this surface.
     */
    @Nonnull
    public Location getBottomMid() {
        return center.add(0, (int) (getSize().getHeight() / 2));
    }

    /**
     * Retrieve the bottom right location of this surface.
     *
     * @return the bottom right location of this surface.
     */
    @Nonnull
    public Location getBottomRight() {
        return center.add((int) (getSize().getWidth() / 2), (int) (getSize().getHeight() / 2));
    }

    /**
     * Determine whether the current view area on this surface contains the specified location.
     *
     * @param location the location to check for existence in the current view area of this surface
     * @return whether the current view area on this surface contains the specified location.
     */
    public boolean contains(@Nonnull final Location location) {
        final Location topLeft = getTopLeft();
        final Location bottomRight = getBottomRight();
        return location.getX() >= topLeft.getX() && location.getX() <= bottomRight.getX()
                && location.getY() >= topLeft.getY() && location.getY() <= bottomRight.getY();
    }
}
