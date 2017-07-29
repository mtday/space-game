package com.mday.client.ui.render.unit;

import com.mday.client.ui.Surface;
import com.mday.common.model.Unit;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.function.BiConsumer;

/**
 * The base class for unit renderers.
 */
public abstract class AbstractUnitRenderer implements BiConsumer<Unit, Surface> {
    private static final double SELECTION_RADIUS = 35.0;

    /**
     * Draw the selection indicator over the unit if it is selected.
     *
     * @param unit the unit for which the selection indicator will be drawn
     * @param surface the surface on which to draw
     */
    protected void drawSelection(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        if (unit.isSelected()) {
            final double selectionRadius = SELECTION_RADIUS * surface.getCoordinateSystem().getScale();
            final double selectionDiameter = selectionRadius * 2;

            final Point2D.Double center = surface.getCoordinateSystem().toPoint(unit.getLocation());

            final Graphics2D graphics = surface.getDrawGraphics();
            graphics.setColor(new Color(231, 226, 16));
            graphics.draw(new Ellipse2D.Double(center.getX() - selectionRadius, center.getY() - selectionRadius,
                    selectionDiameter, selectionDiameter));
        }
    }

    @Override
    public void accept(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        drawSelection(unit, surface);
    }
}
