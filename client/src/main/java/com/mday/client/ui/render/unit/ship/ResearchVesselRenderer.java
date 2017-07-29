package com.mday.client.ui.render.unit.ship;

import com.mday.client.ui.Surface;
import com.mday.client.ui.render.unit.AbstractShipRenderer;
import com.mday.common.model.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Responsible for rendering the research vessel.
 */
public class ResearchVesselRenderer extends AbstractShipRenderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResearchVesselRenderer.class);

    @Override
    public void accept(@Nonnull final Ship ship, @Nonnull final Surface surface) {
        super.accept(ship, surface);

        final double radius = ship.getShipType().getRadius() * surface.getCoordinateSystem().getScale();
        final double diameter = radius * 2;

        final Point2D.Double center = surface.getCoordinateSystem().toPoint(ship.getLocation());

        final Graphics2D graphics = surface.getDrawGraphics();

        graphics.setColor(new Color(174, 72, 23));
        graphics.draw(new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, diameter, diameter));
    }
}
