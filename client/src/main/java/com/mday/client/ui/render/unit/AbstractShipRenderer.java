package com.mday.client.ui.render.unit;

import com.mday.client.ui.Surface;
import com.mday.common.model.Ship;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

/**
 * The base class for unit renderers.
 */
public abstract class AbstractShipRenderer implements BiConsumer<Ship, Surface> {
    /**
     * Retrieve the color to use when drawing the ship.
     *
     * @return the color to use when drawing the ship
     */
    @Nonnull
    protected abstract Color getColor();

    @Override
    public void accept(@Nonnull final Ship ship, @Nonnull final Surface surface) {
        final double radius = ship.getRadius() * surface.getCoordinateSystem().getScale();
        final double diameter = radius * 2;

        final Point2D.Double center = surface.getCoordinateSystem().toPoint(ship.getLocation());

        final Graphics2D graphics = surface.getDrawGraphics();

        graphics.setColor(getColor());
        graphics.draw(new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, diameter, diameter));
    }
}
