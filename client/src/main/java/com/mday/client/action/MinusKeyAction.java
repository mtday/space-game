package com.mday.client.action;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.KeyEvent;
import com.mday.client.event.type.MouseEvent;
import com.mday.client.event.type.ZoomInEvent;
import com.mday.client.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

/**
 * Responsible for handling the plus key.
 */
public class MinusKeyAction implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinusKeyAction.class);

    @Nonnull
    private final EventQueue eventQueue;

    private int x = 0;
    private int y = 0;

    /**
     * Create an instance of this event consumer.
     *
     * @param eventQueue the event queue to notify when the plus key is pressed
     */
    public MinusKeyAction(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.getKeyEvent().getKeyChar() == '-') {
                eventQueue.add(new ZoomInEvent(x, y));
            }
        } else if (event instanceof MouseEvent) {
            final MouseEvent mouseEvent = (MouseEvent) event;
            x = mouseEvent.getMouseEvent().getX();
            y = mouseEvent.getMouseEvent().getY();
        }
    }
}
