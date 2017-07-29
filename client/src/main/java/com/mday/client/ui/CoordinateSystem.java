package com.mday.client.ui;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.ZoomInEvent;
import com.mday.client.event.type.ZoomOutEvent;
import com.mday.common.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.geom.Point2D;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents the display surface on which the game graphics will be drawn.
 */
public class CoordinateSystem implements SurfaceConsumer, EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoordinateSystem.class);

    private int width;
    private int height;

    private double scale = 1.0;
    private double scaleGoal = scale;
    private double scaleIncrement = 0;

    @Nonnull
    private Location center = new Location();

    /**
     * Retrieve the viewable width of the coordinate system in pixels.
     *
     * @return the viewable width of the coordinate system in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the new viewable width of the coordinate system in pixels.
     *
     * @param width the new viewable width of the coordinate system in pixels
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Retrieve the viewable height of the coordinate system in pixels.
     *
     * @return the viewable height of the coordinate system in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the new viewable height of the coordinate system in pixels.
     *
     * @param height the new viewable height of the coordinate system in pixels
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof ZoomInEvent) {
            zoomIn(((ZoomInEvent) event).getPoint());
        } else if (event instanceof ZoomOutEvent) {
            zoomOut(((ZoomOutEvent) event).getPoint());
        }
    }

    @Override
    public void accept(@Nonnull final Surface surface) {
        if (Math.abs(scale - scaleGoal) > Math.abs(scaleIncrement / 2)) {
            scale += scaleIncrement;
        }
    }

    /**
     * Retrieve the current scale, which is the ratio of surface pixels to location points.
     *
     * @return the current scale
     */
    public double getScale() {
        return scale;
    }

    /**
     * Zoom in on the point indicated.
     *
     * @param point the point on the surface on which to zoom in
     */
    public void zoomIn(@Nullable final Point2D.Double point) {
        // Scale is the ratio of surface pixels to location points.
        // Zooming in means we want more pixels per location point.
        // Increase scale by 33%
        scaleGoal *= 3.0 / 2.0;
        scaleIncrement = (scaleGoal - scale) / 12;

        // TODO: update center location based on point
    }

    /**
     * Zoom out from the point indicated.
     *
     * @param point the point on the surface from which to zoom out
     */
    public void zoomOut(@Nullable final Point2D.Double point) {
        // Scale is the ratio of surface pixels to location points.
        // Zooming out means we want fewer pixels per location point.
        // Decrease scale by 33%
        scaleGoal *= 2.0 / 3.0;
        scaleIncrement = (scaleGoal - scale) / 12;

        // TODO: update center location based on point
    }

    /**
     * Retrieve the center location of the draw surface.
     *
     * @return the center location of the draw surface.
     */
    @Nonnull
    public Location getCenter() {
        return center;
    }

    /**
     * Set the new center location of the draw surface.
     *
     * @param center the new center location of the draw surface
     */
    public void setCenter(@Nonnull final Location center) {
        this.center = center;
    }

    /**
     * Retrieve the top left location of the draw surface.
     *
     * @return the top left location of the draw surface.
     */
    @Nonnull
    public Location getTopLeft() {
        return center.add((int) (-width / scale / 2), (int) (-height / scale / 2));
    }

    /**
     * Retrieve the top mid location of the draw surface.
     *
     * @return the top mid location of the draw surface.
     */
    @Nonnull
    public Location getTopMid() {
        return center.add(0, (int) (-height / scale / 2));
    }

    /**
     * Retrieve the top right location of the draw surface.
     *
     * @return the top right location of the draw surface.
     */
    @Nonnull
    public Location getTopRight() {
        return center.add((int) (width / scale / 2), (int) (-height / scale / 2));
    }

    /**
     * Retrieve the mid left location of the draw surface.
     *
     * @return the mid left location of the draw surface.
     */
    @Nonnull
    public Location getMidLeft() {
        return center.add((int) (-width / scale / 2), 0);
    }

    /**
     * Retrieve the mid right location of the draw surface.
     *
     * @return the mid right location of the draw surface.
     */
    @Nonnull
    public Location getMidRight() {
        return center.add((int) (width / scale / 2), 0);
    }

    /**
     * Retrieve the bottom left location of the draw surface.
     *
     * @return the bottom left location of the draw surface.
     */
    @Nonnull
    public Location getBottomLeft() {
        return center.add((int) (-width / scale / 2), (int) (height / scale / 2));
    }

    /**
     * Retrieve the bottom mid location of the draw surface.
     *
     * @return the bottom mid location of the draw surface.
     */
    @Nonnull
    public Location getBottomMid() {
        return center.add(0, (int) (height / scale / 2));
    }

    /**
     * Retrieve the bottom right location of the draw surface.
     *
     * @return the bottom right location of the draw surface.
     */
    @Nonnull
    public Location getBottomRight() {
        return center.add((int) (width / scale / 2), (int) (height / scale / 2));
    }

    /**
     * Determine whether the current view area on the draw surface contains the specified location.
     *
     * @param location the location to check for existence in the current view area of the draw surface
     * @return whether the current view area on the draw surface contains the specified location.
     */
    public boolean contains(@Nonnull final Location location) {
        final Location topLeft = getTopLeft();
        final Location bottomRight = getBottomRight();
        return location.getX() >= topLeft.getX() && location.getX() <= bottomRight.getX()
                && location.getY() >= topLeft.getY() && location.getY() <= bottomRight.getY();
    }

    /**
     * Convert the specified location value into a point on the surface.
     *
     * @param location the location to convert
     * @return a point representing the spot on the surface corresponding to the provided location
     */
    @Nonnull
    public Point2D.Double toPoint(@Nonnull final Location location) {
        return new Point2D.Double(
                (width / 2.0) + (location.getX() - center.getX()) * getScale(),
                (height / 2.0) + (location.getY() - center.getY()) * getScale());
    }

    /**
     * Convert the specified surface point value into a game location.
     *
     * @param point the surface point to convert
     * @return a location corresponding to the provided surface point
     */
    @Nonnull
    public Location toLocation(@Nonnull final Point2D.Double point) {
        return new Location(
                point.getX() - (width / 2.0) / scale + center.getX(),
                point.getY() - (height / 2.0) / scale + center.getY());
    }
}
