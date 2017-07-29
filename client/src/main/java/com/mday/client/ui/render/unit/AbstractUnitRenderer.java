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
    private static final int SELECTION_SIZE = 35;

    /**
     * Draw the selection indicator over the unit if it is selected.
     *
     * @param unit the unit for which the selection indicator will be drawn
     * @param surface the surface on which to draw
     */
    protected void drawSelection(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        if (unit.isSelected()) {
            final double selectionDiameter = SELECTION_SIZE * surface.getCoordinateSystem().getScale();
            final double selectionRadius = selectionDiameter / 2;

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
