package com.mday.client.event.type;

import static com.mday.client.event.EventType.QUIT;

import com.mday.client.event.Event;

/**
 * Represents the game should quit.
 */
public class QuitEvent extends Event {
    /**
     * Create an instance of this event.
     */
    public QuitEvent() {
        super(QUIT);
    }
}
