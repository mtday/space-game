package com.mday.client.ui.render.unit;

import com.mday.client.ui.Surface;
import com.mday.common.model.Unit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

/**
 * The base class for unit renderers.
 */
public abstract class AbstractUnitRenderer implements BiConsumer<Unit, Surface> {
    private static final Color SELECTION_CIRCLE_COLOR = new Color(226, 231, 101);

    /**
     * Draw a bounding circle surrounding this unit.
     *
     * @param unit the unit around which the circle will be drawn
     * @param surface the surface on which the circle will be drawn
     */
    protected void drawSelectionCircle(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        final double radius = unit.getRadius() * surface.getCoordinateSystem().getScale();
        final double diameter = radius * 2;

        final Point2D.Double center = surface.getCoordinateSystem().toPoint(unit.getLocation());

        final Graphics2D graphics = surface.getDrawGraphics();
        graphics.setColor(SELECTION_CIRCLE_COLOR);
        graphics.draw(new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, diameter, diameter));
    }

    @Override
    public void accept(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        if (unit.isSelected()) {
            drawSelectionCircle(unit, surface);
        }
    }
}
