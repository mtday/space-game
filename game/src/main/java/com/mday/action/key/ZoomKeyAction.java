package com.mday.action.key;

import static java.awt.event.KeyEvent.KEY_TYPED;

import com.mday.event.Event;
import com.mday.event.EventConsumer;
import com.mday.event.type.coordinate.ZoomInEvent;
import com.mday.event.type.coordinate.ZoomOutEvent;
import com.mday.event.type.input.KeyEvent;
import com.mday.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Responsible for handling the zoom in and out keys.
 */
public class ZoomKeyAction implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZoomKeyAction.class);

    @Nonnull
    private final EventQueue eventQueue;

    /**
     * Create an instance of this event consumer.
     *
     * @param eventQueue the event queue to notify when the zoom keys are pressed
     */
    public ZoomKeyAction(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Nullable
    private Event toZoomEvent(final char key) {
        if (key == '+' || key == '=') {
            return new ZoomInEvent();
        } else if (key == '-' || key == '_') {
            return new ZoomOutEvent();
        }
        return null;
    }

    @Override
    public void accept(@Nonnull final Event event) {
        Stream.of(event)
                .filter(e -> e instanceof KeyEvent)
                .map(e -> (KeyEvent) e)
                .filter(ke -> ke.getKeyEvent().getID() == KEY_TYPED)
                .map(ke -> ke.getKeyEvent().getKeyChar())
                .map(this::toZoomEvent)
                .forEach(eventQueue::add);
    }
}
