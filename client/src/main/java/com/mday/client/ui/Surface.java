package com.mday.client.ui;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_RENDER_QUALITY;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_GASP;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import com.mday.common.model.Location;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JPanel;

/**
 * Represents the display surface on which the game graphics will be drawn.
 */
public class Surface extends JPanel implements SurfaceConsumer {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Surface.class);

    @Nonnull
    private final transient BufferedImage bufferedImage;

    private double scale = 1.0;
    private double scaleGoal = scale;
    private double scaleIncrement = 0;

    @Nonnull
    private final transient Location center;

    /**
     * Create an instance of this class.
     *
     * @param width the width of the display surface
     * @param height the height of the display surface
     * @param center the location describing the center point in this surface
     */
    @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
    public Surface(final int width, final int height, @Nonnull final Location center) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        this.center = center;

        bufferedImage = new BufferedImage(width, height, TYPE_INT_ARGB);

        final Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

        // NOTE: Loading the font metrics here takes a long time. See the bug report:
        // https://bugs.openjdk.java.net/browse/JDK-8179209
        // We call getFontMetrics here before the JFrame is shown to prevent an empty frame from being displayed.
        graphics.setFont(new Font("Dialog", Font.PLAIN, 12));
        graphics.getFontMetrics();
    }

    @Override
    public void accept(@Nonnull final Surface surface) {
        if (Math.abs(scale - scaleGoal) > Math.abs(scaleIncrement / 2)) {
            scale += scaleIncrement;
        }
    }

    @Override
    protected void paintComponent(@Nonnull final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(getBufferedImage(), 0, 0, getWidth(), getHeight(), null);
    }

    @Nonnull
    private BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    /**
     * Retrieve the graphics on which to draw game elements.
     *
     * @return a {@link Graphics2D} on which to draw game elements
     */
    @Nonnull
    public Graphics2D getDrawGraphics() {
        final Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        final RenderingHints renderingHints = new RenderingHints(null);
        renderingHints.put(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        renderingHints.put(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_GASP);
        renderingHints.put(KEY_RENDERING, VALUE_RENDER_QUALITY);
        graphics.setRenderingHints(renderingHints);
        return graphics;
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
     * Convert the specified location value into a point on the surface.
     *
     * @param location the location to convert
     * @return a point representing the spot on the surface corresponding to the provided location
     */
    @Nonnull
    public Point2D.Double toPoint(@Nonnull final Location location) {
        return new Point2D.Double(
                (getWidth() / 2.0) - (location.getX() - center.getX()) * getScale(),
                (getHeight() / 2.0) - (location.getY() - center.getY()) * getScale());
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
                point.getX() - (getWidth() / 2.0) / scale + center.getX(),
                point.getY() - (getHeight() / 2.0) / scale + center.getY());
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
        return center.add((int) (-getSize().getWidth() / scale / 2), (int) (-getSize().getHeight() / scale / 2));
    }

    /**
     * Retrieve the top mid location of this surface.
     *
     * @return the top mid location of this surface.
     */
    @Nonnull
    public Location getTopMid() {
        return center.add(0, (int) (-getSize().getHeight() / scale / 2));
    }

    /**
     * Retrieve the top right location of this surface.
     *
     * @return the top right location of this surface.
     */
    @Nonnull
    public Location getTopRight() {
        return center.add((int) (getSize().getWidth() / scale / 2), (int) (-getSize().getHeight() / scale / 2));
    }

    /**
     * Retrieve the mid left location of this surface.
     *
     * @return the mid left location of this surface.
     */
    @Nonnull
    public Location getMidLeft() {
        return center.add((int) (-getSize().getWidth() / scale / 2), 0);
    }

    /**
     * Retrieve the mid right location of this surface.
     *
     * @return the mid right location of this surface.
     */
    @Nonnull
    public Location getMidRight() {
        return center.add((int) (getSize().getWidth() / scale / 2), 0);
    }

    /**
     * Retrieve the bottom left location of this surface.
     *
     * @return the bottom left location of this surface.
     */
    @Nonnull
    public Location getBottomLeft() {
        return center.add((int) (-getSize().getWidth() / scale / 2), (int) (getSize().getHeight() / scale / 2));
    }

    /**
     * Retrieve the bottom mid location of this surface.
     *
     * @return the bottom mid location of this surface.
     */
    @Nonnull
    public Location getBottomMid() {
        return center.add(0, (int) (getSize().getHeight() / scale / 2));
    }

    /**
     * Retrieve the bottom right location of this surface.
     *
     * @return the bottom right location of this surface.
     */
    @Nonnull
    public Location getBottomRight() {
        return center.add((int) (getSize().getWidth() / scale / 2), (int) (getSize().getHeight() / scale / 2));
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
