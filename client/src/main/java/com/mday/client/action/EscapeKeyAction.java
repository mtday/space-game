package com.mday.client.action;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.KeyEvent;
import com.mday.client.event.type.QuitEvent;
import com.mday.client.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

import static java.awt.Event.ESCAPE;

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
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.getKeyEvent().getKeyCode() == ESCAPE) {
                eventQueue.add(new QuitEvent());
            }
        }
    }
}
