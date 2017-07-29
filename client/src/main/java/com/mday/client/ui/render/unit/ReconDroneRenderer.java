package com.mday.client.ui.render.unit;

import com.mday.client.ui.Surface;
import com.mday.common.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

/**
 * Responsible for rendering the recon drone unit type.
 */
public class ReconDroneRenderer implements BiConsumer<Unit, Surface> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReconDroneRenderer.class);

    @Override
    public void accept(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        final double diameter = unit.getType().getSize() * surface.getScale();
        final double radius = diameter / 2;

        final Point2D.Double center = surface.toPoint(unit.getLocation());

        final Graphics2D graphics = surface.getDrawGraphics();

        graphics.setColor(new Color(33, 174, 75));
        graphics.draw(new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, diameter, diameter));
    }
}
