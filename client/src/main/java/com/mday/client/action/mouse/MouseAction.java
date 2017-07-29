package com.mday.client.action.mouse;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.coordinate.PanEvent;
import com.mday.client.event.type.input.MouseEvent;
import com.mday.client.event.type.unit.UnitDeselectEvent;
import com.mday.client.event.type.unit.UnitMoveEvent;
import com.mday.client.event.type.unit.UnitSelectEvent;
import com.mday.client.game.EventQueue;
import com.mday.client.game.Units;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.geom.Point2D;

import static java.awt.event.MouseEvent.*;

/**
 * Responsible for generating events based on mouse activity.
 */
public class MouseAction implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MouseAction.class);

    @Nonnull
    private final EventQueue eventQueue;
    @Nonnull
    private final Units units;

    private boolean unitsSelected = false;
    private boolean wasDrag = false;
    private int startX = 0;
    private int startY = 0;

    private int dragX = 0;
    private int dragY = 0;

    /**
     * Create an instance of this class.
     *
     * @param eventQueue the event queue onto which unit selection events will be published
     * @param units      the manager tracking the available units
     */
    public MouseAction(@Nonnull final EventQueue eventQueue, @Nonnull final Units units) {
        this.eventQueue = eventQueue;
        this.units = units;
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof MouseEvent) {
            final MouseEvent mouseEvent = (MouseEvent) event;

            if (mouseEvent.getMouseEvent().getButton() == BUTTON1) {
                if (mouseEvent.getMouseEvent().getID() == MOUSE_PRESSED) {
                    startX = mouseEvent.getMouseEvent().getX();
                    startY = mouseEvent.getMouseEvent().getY();
                    unitsSelected = units.isUnitsSelected();
                } else if (mouseEvent.getMouseEvent().getID() == MOUSE_DRAGGED) {
                    if (!wasDrag) {
                        // We are now recognizing a drag. Deselect all units since we are about to select more.
                        eventQueue.add(new UnitDeselectEvent());
                    }
                    wasDrag = true;
                } else if (mouseEvent.getMouseEvent().getID() == MOUSE_RELEASED) {
                    final int endX = mouseEvent.getMouseEvent().getX();
                    final int endY = mouseEvent.getMouseEvent().getY();

                    if (!wasDrag && unitsSelected) {
                        // Units were selected and the user clicked somewhere. Treat this as a move.
                        eventQueue.add(new UnitMoveEvent(new Point2D.Double(endX, endY)));
                    } else {
                        // The user drew a selection rectangle on the screen, or clicked somewhere when no units
                        // were selected, so request selection of units.
                        final Point2D.Double min = new Point2D.Double(Math.min(startX, endX), Math.min(startY, endY));
                        final Point2D.Double max = new Point2D.Double(Math.max(startX, endX), Math.max(startY, endY));
                        eventQueue.add(new UnitSelectEvent(min, max));
                    }

                    wasDrag = false;
                }
            } else if (mouseEvent.getMouseEvent().getButton() == BUTTON3) {
                // Right mouse button event.
                if (mouseEvent.getMouseEvent().getID() == MOUSE_PRESSED) {
                    // Deselect units.
                    eventQueue.add(new UnitDeselectEvent());
                    dragX = mouseEvent.getMouseEvent().getX();
                    dragY = mouseEvent.getMouseEvent().getY();
                    unitsSelected = units.isUnitsSelected();
                } else if (mouseEvent.getMouseEvent().getID() == MOUSE_DRAGGED
                        || mouseEvent.getMouseEvent().getID() == MOUSE_RELEASED) {
                    final int endX = mouseEvent.getMouseEvent().getX();
                    final int endY = mouseEvent.getMouseEvent().getY();

                    if (endX != dragX || endY != dragY) {
                        eventQueue.add(new PanEvent(dragX - endX, dragY - endY));
                        dragX = endX;
                        dragY = endY;
                    }
                }
            }
        }
    }
}
