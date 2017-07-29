package com.mday.client.ui.render;

import com.mday.client.ui.Surface;
import com.mday.client.ui.SurfaceConsumer;
import com.mday.common.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing the location grid.
 */
public class GridRenderer implements SurfaceConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GridRenderer.class);

    private static final boolean ENABLED = false;
    private static final int DELTA = 50;

    @Override
    public void accept(@Nonnull final Surface surface) {
        if (ENABLED) {
            final Graphics2D graphics = surface.getDrawGraphics();
            graphics.setColor(new Color(66, 84, 120));

            final Location topLeft = surface.getCoordinateSystem().getTopLeft();
            final Location botRight = surface.getCoordinateSystem().getBottomRight();

            final Location first = new Location(topLeft.getX() - (topLeft.getX() % DELTA) - DELTA,
                    topLeft.getY() - (topLeft.getY() % DELTA) - DELTA);

            final Location last = new Location(botRight.getX() + (botRight.getX() % DELTA),
                    botRight.getY() + (botRight.getY() % DELTA));

            for (double x = first.getX(); x <= last.getX(); x += DELTA) {
                final Point2D.Double start = surface.getCoordinateSystem().toPoint(new Location(x, first.getY()));
                final Point2D.Double end = surface.getCoordinateSystem().toPoint(new Location(x, last.getY()));
                graphics.draw(new Line2D.Double(start, end));
            }
            for (double y = first.getY(); y <= last.getX(); y += DELTA) {
                final Point2D.Double start = surface.getCoordinateSystem().toPoint(new Location(first.getX(), y));
                final Point2D.Double end = surface.getCoordinateSystem().toPoint(new Location(last.getX(), y));
                graphics.draw(new Line2D.Double(start, end));
            }
        }
    }
}
