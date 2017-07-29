package com.mday.client.action;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.KeyEvent;
import com.mday.client.event.type.ZoomInEvent;
import com.mday.client.event.type.ZoomOutEvent;
import com.mday.client.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

import static java.awt.event.KeyEvent.KEY_TYPED;

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

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.getKeyEvent().getID() == KEY_TYPED) {
                final char keychar = keyEvent.getKeyEvent().getKeyChar();
                if (keychar == '+' || keychar == '=') {
                    eventQueue.add(new ZoomInEvent());
                } else if (keychar == '-' || keychar == '_') {
                    eventQueue.add(new ZoomOutEvent());
                }
            }
        }
    }
}
