package com.mday.ui.render.debug;

import com.mday.event.Event;
import com.mday.event.EventConsumer;
import com.mday.event.type.input.MouseEvent;
import com.mday.model.Location;
import com.mday.ui.Surface;
import com.mday.ui.SurfaceConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing the current mouse coordinate location.
 */
public class MousePositionRenderer implements SurfaceConsumer, EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MousePositionRenderer.class);

    private int x = 0;
    private int y = 0;

    @Override
    public void accept(@Nonnull final Surface surface) {
        final Graphics2D graphics = surface.getDrawGraphics();
        graphics.setColor(new Color(200, 200, 200));

        final Point2D.Double point = new Point2D.Double(x, y);
        final Location location = surface.getCoordinateSystem().toLocation(point);

        graphics.drawString(String.format("PX: %.2f", point.getX()), 10, 20);
        graphics.drawString(String.format("PY: %.2f", point.getY()), 10, 40);
        graphics.drawString(String.format("LX: %.2f", location.getX()), 10, 60);
        graphics.drawString(String.format("LY: %.2f", location.getY()), 10, 80);
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof MouseEvent) {
            final MouseEvent mouseEvent = (MouseEvent) event;
            x = mouseEvent.getMouseEvent().getX();
            y = mouseEvent.getMouseEvent().getY();
        }
    }
}
