package com.mday.action.mouse;

import com.mday.event.Event;
import com.mday.event.EventConsumer;
import com.mday.event.type.coordinate.ZoomInEvent;
import com.mday.event.type.coordinate.ZoomOutEvent;
import com.mday.event.type.input.MouseWheelEvent;
import com.mday.game.EventQueue;
import com.mday.ui.Surface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.geom.Point2D;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

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

    @Nonnull
    private Event toZoomEvent(@Nonnull final MouseWheelEvent mouseWheelEvent) {
        final int rotation = mouseWheelEvent.getMouseWheelEvent().getWheelRotation();
        final Point2D.Double point = new Point2D.Double(
                mouseWheelEvent.getMouseWheelEvent().getX(), mouseWheelEvent.getMouseWheelEvent().getY());
        return (rotation > 0) ? new ZoomInEvent(point) : new ZoomOutEvent(point);
    }

    @Override
    public void accept(@Nonnull final Event event) {
        Stream.of(event)
                .filter(e -> e instanceof MouseWheelEvent)
                .map(e -> (MouseWheelEvent) e)
                // We only care about mouse wheel events that happen on the surface, not the frame.
                .filter(e -> e.getMouseWheelEvent().getSource() instanceof Surface)
                .map(this::toZoomEvent)
                .forEach(eventQueue::add);
    }
}
