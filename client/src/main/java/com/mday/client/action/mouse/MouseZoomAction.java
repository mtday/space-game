package com.mday.client.action.mouse;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.coordinate.ZoomInEvent;
import com.mday.client.event.type.coordinate.ZoomOutEvent;
import com.mday.client.event.type.input.MouseWheelEvent;
import com.mday.client.game.EventQueue;
import com.mday.client.ui.Surface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.geom.Point2D;

/**
 * Responsible for performing zoom actions based on mouse scroll-wheel activity.
 */
public class MouseZoomAction implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MouseZoomAction.class);

    @Nonnull
    private final EventQueue eventQueue;

    /**
     * Create an instance of this class.
     *
     * @param eventQueue the event queue onto which zoom events will be published
     */
    public MouseZoomAction(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof MouseWheelEvent) {
            final MouseWheelEvent mouseWheelEvent = (MouseWheelEvent) event;
            // We only care about mouse wheel events that happen on the surface, not the frame.
            if (mouseWheelEvent.getMouseWheelEvent().getSource() instanceof Surface) {
                final int rotation = mouseWheelEvent.getMouseWheelEvent().getWheelRotation();
                final Point2D.Double point = new Point2D.Double(
                        mouseWheelEvent.getMouseWheelEvent().getX(), mouseWheelEvent.getMouseWheelEvent().getY());
                if (rotation > 0) {
                    eventQueue.add(new ZoomInEvent(point));
                } else {
                    eventQueue.add(new ZoomOutEvent(point));
                }
            }
        }
    }
}
