package com.mday.client.ui;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_RENDER_QUALITY;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_GASP;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;
import javax.swing.JPanel;

/**
 * Represents the display surface on which the game graphics will be drawn.
 */
public class Surface extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Surface.class);

    @Nonnull
    private final transient BufferedImage bufferedImage;
    @Nonnull
    private final transient CoordinateSystem coordinateSystem;

    /**
     * Create an instance of this class.
     *
     * @param coordinateSystem the coordinate system managing locations on this draw surface
     */
    @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
    public Surface(@Nonnull final CoordinateSystem coordinateSystem) {
        setSize(coordinateSystem.getWidth(), coordinateSystem.getHeight());
        setPreferredSize(new Dimension(coordinateSystem.getWidth(), coordinateSystem.getHeight()));
        this.coordinateSystem = coordinateSystem;

        bufferedImage = new BufferedImage(coordinateSystem.getWidth(), coordinateSystem.getHeight(), TYPE_INT_ARGB);

        final Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

        // NOTE: Loading the font metrics here takes a long time. See the bug report:
        // https://bugs.openjdk.java.net/browse/JDK-8179209
        // We call getFontMetrics here before the JFrame is shown to prevent an empty frame from being displayed.
        graphics.setFont(new Font("Dialog", Font.PLAIN, 12));
        graphics.getFontMetrics();
    }

    @Override
    protected void paintComponent(@Nonnull final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(getBufferedImage(), 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Retrieve the coordinate system managing locations on this surface.
     *
     * @return the coordinate system managing locations on this surface
     */
    @Nonnull
    public CoordinateSystem getCoordinateSystem() {
        return coordinateSystem;
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
}
