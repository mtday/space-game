package com.mday.client.ui.render.unit;

import com.mday.client.ui.Surface;
import com.mday.common.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.annotation.Nonnull;

/**
 * Responsible for rendering the research vessel unit type.
 */
public class ResearchVesselRenderer extends AbstractUnitRenderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResearchVesselRenderer.class);

    @Override
    public void accept(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        super.accept(unit, surface);

        final double diameter = unit.getType().getSize() * surface.getCoordinateSystem().getScale();
        final double radius = diameter / 2;

        final Point2D.Double center = surface.getCoordinateSystem().toPoint(unit.getLocation());

        final Graphics2D graphics = surface.getDrawGraphics();

        graphics.setColor(new Color(174, 72, 23));
        graphics.draw(new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, diameter, diameter));
    }
}
