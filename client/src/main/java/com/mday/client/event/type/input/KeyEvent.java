package com.mday.client.event.type.input;

import static com.mday.client.event.EventType.KEY;

import com.mday.client.event.Event;

import javax.annotation.Nonnull;

/**
 * An event representing a key action.
 */
public class KeyEvent extends Event {
    @Nonnull
    private final java.awt.event.KeyEvent keyEvent;

    /**
     * Create an instance of this event class.
     *
     * @param keyEvent the key event that occurred
     */
    public KeyEvent(@Nonnull final java.awt.event.KeyEvent keyEvent) {
        super(KEY);
        this.keyEvent = keyEvent;
    }

    /**
     * Retrieve the key event.
     *
     * @return the key event
     */
    @Nonnull
    public java.awt.event.KeyEvent getKeyEvent() {
        return keyEvent;
    }
}
