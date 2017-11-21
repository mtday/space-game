package com.mday.action.key;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

import com.mday.event.Event;
import com.mday.event.EventConsumer;
import com.mday.event.type.coordinate.PanDownEvent;
import com.mday.event.type.coordinate.PanLeftEvent;
import com.mday.event.type.coordinate.PanRightEvent;
import com.mday.event.type.coordinate.PanUpEvent;
import com.mday.event.type.input.KeyEvent;
import com.mday.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Responsible for handling the arrow keys.
 */
public class ArrowKeyAction implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrowKeyAction.class);

    @Nonnull
    private final EventQueue eventQueue;

    /**
     * Create an instance of this event consumer.
     *
     * @param eventQueue the event queue to notify when an arrow key is pressed
     */
    public ArrowKeyAction(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Nullable
    private Event toPanEvent(final int keyId) {
        switch (keyId) {
            case VK_UP:
                return new PanUpEvent();
            case VK_DOWN:
                return new PanDownEvent();
            case VK_LEFT:
                return new PanLeftEvent();
            case VK_RIGHT:
                return new PanRightEvent();
            default:
                return null;
        }
    }

    @Override
    public void accept(@Nonnull final Event event) {
        Stream.of(event)
                .filter(e -> e instanceof KeyEvent)
                .map(e -> (KeyEvent) e)
                .map(ke -> ke.getKeyEvent().getID())
                .map(this::toPanEvent)
                .filter(Objects::nonNull)
                .forEach(eventQueue::add);
    }
}
