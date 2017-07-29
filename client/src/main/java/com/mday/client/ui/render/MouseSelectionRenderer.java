package com.mday.client.ui.render;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.MOUSE_DRAGGED;
import static java.awt.event.MouseEvent.MOUSE_PRESSED;
import static java.awt.event.MouseEvent.MOUSE_RELEASED;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.input.MouseEvent;
import com.mday.client.ui.Surface;
import com.mday.client.ui.SurfaceConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing the mouse selection rectangle.
 */
public class MouseSelectionRenderer implements SurfaceConsumer, EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MouseSelectionRenderer.class);

    private boolean startDraw = false;
    private boolean endDraw = false;

    private int startX = 0;
    private int startY = 0;
    private int endX = 0;
    private int endY = 0;

    @Override
    public void accept(@Nonnull final Surface surface) {
        if (startDraw) {
            final Graphics2D graphics = surface.getDrawGraphics();
            graphics.setColor(new Color(200, 200, 200));

            graphics.drawLine(startX, startY, startX, endY); // Left
            graphics.drawLine(endX, startY, endX, endY); // Right
            graphics.drawLine(startX, startY, endX, startY); // Top
            graphics.drawLine(startX, endY, endX, endY); // Bottom
        }

        if (endDraw) {
            startDraw = false;
            endDraw = false;
        }
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof MouseEvent) {
            final MouseEvent mouseEvent = (MouseEvent) event;

            if (mouseEvent.getMouseEvent().getButton() == BUTTON1) {
                if (mouseEvent.getMouseEvent().getID() == MOUSE_PRESSED) {
                    startX = mouseEvent.getMouseEvent().getX();
                    startY = mouseEvent.getMouseEvent().getY();
                } else if (mouseEvent.getMouseEvent().getID() == MOUSE_DRAGGED) {
                    startDraw = true;
                    endX = mouseEvent.getMouseEvent().getX();
                    endY = mouseEvent.getMouseEvent().getY();
                } else if (mouseEvent.getMouseEvent().getID() == MOUSE_RELEASED) {
                    endDraw = true;
                    endX = mouseEvent.getMouseEvent().getX();
                    endY = mouseEvent.getMouseEvent().getY();
                }
            }
        }
    }
}
