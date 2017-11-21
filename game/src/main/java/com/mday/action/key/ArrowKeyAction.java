package com.mday.action.key;

import static java.awt.Event.KEY_PRESS;
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

import javax.annotation.Nonnull;

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
