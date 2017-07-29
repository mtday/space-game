package com.mday.client.action;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.*;
import com.mday.client.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

import static java.awt.Event.KEY_PRESS;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

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

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.getKeyEvent().getID() == KEY_PRESS) {
                if (keyEvent.getKeyEvent().getKeyCode() == VK_UP) {
                    eventQueue.add(new PanUpEvent());
                } else if (keyEvent.getKeyEvent().getKeyCode() == VK_DOWN) {
                    eventQueue.add(new PanDownEvent());
                } else if (keyEvent.getKeyEvent().getKeyCode() == VK_LEFT) {
                    eventQueue.add(new PanLeftEvent());
                } else if (keyEvent.getKeyEvent().getKeyCode() == VK_RIGHT) {
                    eventQueue.add(new PanRightEvent());
                }
            }
        }
    }
}
