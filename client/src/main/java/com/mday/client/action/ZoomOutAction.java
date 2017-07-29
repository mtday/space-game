package com.mday.client.action;

import static java.awt.event.KeyEvent.KEY_TYPED;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.KeyEvent;
import com.mday.client.event.type.ZoomOutEvent;
import com.mday.client.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

/**
 * Responsible for handling the plus key.
 */
public class ZoomOutAction implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZoomOutAction.class);

    @Nonnull
    private final EventQueue eventQueue;

    /**
     * Create an instance of this event consumer.
     *
     * @param eventQueue the event queue to notify when the plus key is pressed
     */
    public ZoomOutAction(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if ((keyEvent.getKeyEvent().getKeyChar() == '-' || keyEvent.getKeyEvent().getKeyChar() == '_')
                    && keyEvent.getKeyEvent().getID() == KEY_TYPED) {
                eventQueue.add(new ZoomOutEvent());
            }
        }
    }
}
