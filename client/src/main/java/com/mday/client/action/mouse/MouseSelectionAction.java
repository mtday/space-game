package com.mday.client.action.mouse;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.input.MouseEvent;
import com.mday.client.event.type.unit.UnitDeselectEvent;
import com.mday.client.event.type.unit.UnitSelectEvent;
import com.mday.client.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.geom.Point2D;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.MOUSE_DRAGGED;
import static java.awt.event.MouseEvent.MOUSE_PRESSED;
import static java.awt.event.MouseEvent.MOUSE_RELEASED;

/**
 * Responsible for drawing the mouse selection rectangle.
 */
public class MouseSelectionAction implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MouseSelectionAction.class);

    @Nonnull
    private final EventQueue eventQueue;

    private boolean wasDrag = false;
    private int startX = 0;
    private int startY = 0;

    /**
     * Create an instance of this class.
     *
     * @param eventQueue the event queue onto which mouse selection events will be published
     */
    public MouseSelectionAction(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof MouseEvent) {
            final MouseEvent mouseEvent = (MouseEvent) event;

            if (mouseEvent.getMouseEvent().getButton() == BUTTON1) {
                if (mouseEvent.getMouseEvent().getID() == MOUSE_PRESSED) {
                    eventQueue.add(new UnitDeselectEvent());

                    startX = mouseEvent.getMouseEvent().getX();
                    startY = mouseEvent.getMouseEvent().getY();
                } else if (mouseEvent.getMouseEvent().getID() == MOUSE_DRAGGED) {
                    wasDrag = true;
                } else if (mouseEvent.getMouseEvent().getID() == MOUSE_RELEASED) {
                    final int endX = mouseEvent.getMouseEvent().getX();
                    final int endY = mouseEvent.getMouseEvent().getY();

                    if (wasDrag) {
                        final Point2D.Double min = new Point2D.Double(Math.min(startX, endX), Math.min(startY, endY));
                        final Point2D.Double max = new Point2D.Double(Math.max(startX, endX), Math.max(startY, endY));
                        eventQueue.add(new UnitSelectEvent(min, max));
                    }

                    wasDrag = false;
                }
            }
        }
    }
}
