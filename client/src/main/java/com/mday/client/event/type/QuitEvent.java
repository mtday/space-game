package com.mday.client.event.type;

import com.mday.client.event.Event;
import com.mday.client.event.EventType;

/**
 * Represents the game should quit.
 */
public class QuitEvent extends Event {
    /**
     * Create an instance of this event.
     */
    public QuitEvent() {
        super(EventType.QUIT);
    }
}
