package com.mday.client.ui.render.unit;

import com.mday.client.ui.Surface;
import com.mday.common.model.Location;
import com.mday.common.model.Unit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

/**
 * Responsible for rendering the shipyard unit type.
 */
public class ShipyardRenderer implements BiConsumer<Unit, Surface> {
    @Override
    public void accept(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        final int zoom = surface.getZoom();
        final Location center = unit.getLocation().subtract(surface.getTopLeft());
        final Location topLeft = center.subtract(zoom / 2, zoom / 2);

        final Graphics2D graphics = surface.getDrawGraphics();

        graphics.setColor(new Color(172, 9, 174));

        // Draw a circle over the ship location.
        graphics.draw(new Ellipse2D.Float(topLeft.getX(), topLeft.getY(), zoom, zoom));

        // Draw the center of the location.
        graphics.draw(new Line2D.Float(center.getX() - 2, center.getY(), center.getX() + 2, center.getY()));
        graphics.draw(new Line2D.Float(center.getX(), center.getY() - 2, center.getX(), center.getY() + 2));
    }
}
