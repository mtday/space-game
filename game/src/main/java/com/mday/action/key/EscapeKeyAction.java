package com.mday.action.key;

import static java.awt.Event.ESCAPE;

import com.mday.event.Event;
import com.mday.event.EventConsumer;
import com.mday.event.type.game.QuitEvent;
import com.mday.event.type.input.KeyEvent;
import com.mday.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import javax.annotation.Nonnull;

/**
 * Responsible for handling the escape key.
 */
public class EscapeKeyAction implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EscapeKeyAction.class);

    @Nonnull
    private final EventQueue eventQueue;

    /**
     * Create an instance of this event consumer.
     *
     * @param eventQueue the event queue to notify when the escape key is pressed
     */
    public EscapeKeyAction(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void accept(@Nonnull final Event event) {
        Stream.of(event)
                .filter(e -> e instanceof KeyEvent)
                .map(e -> (KeyEvent) e)
                .map(ke -> ke.getKeyEvent().getKeyCode())
                .filter(keyCode -> keyCode == ESCAPE)
                .map(keyCode -> new QuitEvent())
                .forEach(eventQueue::add);
    }
}
