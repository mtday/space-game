package com.mday.client.ui.render.unit;

import com.mday.client.ui.Surface;
import com.mday.common.model.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;

/**
 * The base class for unit renderers.
 */
public abstract class AbstractShipRenderer implements BiConsumer<Ship, Surface> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractShipRenderer.class);

    private final BufferedImage shipImage;

    /**
     * Create an instance of this ship renderer.
     *
     * @param imageResource the name of the image resource to be rendered
     * @throws IOException if there is a problem loading the ship image resource
     */
    public AbstractShipRenderer(@Nonnull final String imageResource) throws IOException {
        shipImage = ImageIO.read(AbstractShipRenderer.class.getClassLoader().getResourceAsStream(imageResource));
    }

    /**
     * Draw the ship itself.
     *
     * @param ship the ship to be drawn
     * @param surface the surface on which the ship will be drawn
     */
    protected void drawShip(@Nonnull final Ship ship, @Nonnull final Surface surface) {
        final double radius = ship.getRadius() * surface.getCoordinateSystem().getScale();

        final Point2D.Double center = surface.getCoordinateSystem().toPoint(ship.getLocation());

        final double imageSize = Math.max(shipImage.getWidth(), shipImage.getHeight());
        final double scale = radius * 2 / imageSize * 0.8;

        final AffineTransform transform = new AffineTransform();
        // 4. Translate the image to the ship's location.
        transform.translate(center.getX(), center.getY());
        // 3. Rotate the image so it matches the ship's direction.
        transform.rotate(ship.getDirection());
        // 2. Scale the image so it fits the scaled ship size.
        transform.scale(scale, scale);
        // 1. Translate the object so that you rotate it around the center.
        transform.translate(-shipImage.getWidth() / 2.0, -shipImage.getHeight() / 2.0);

        final Graphics2D graphics = surface.getDrawGraphics();
        graphics.drawImage(shipImage, transform, null);
    }

    @Override
    public void accept(@Nonnull final Ship ship, @Nonnull final Surface surface) {
        drawShip(ship, surface);
    }
}
