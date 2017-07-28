package com.mday.client.ui.render;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.MouseEvent;
import com.mday.client.ui.Surface;
import com.mday.client.ui.SurfaceConsumer;
import com.mday.common.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Graphics2D;

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
        final Location mouseLocation = surface.getTopLeft().add(x, y);
        final Graphics2D graphics = surface.getDrawGraphics();
        graphics.setColor(new Color(200, 200, 200));
        graphics.drawString("X: " + mouseLocation.getX(), 10, 20);
        graphics.drawString("Y: " + mouseLocation.getY(), 10, 40);
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
