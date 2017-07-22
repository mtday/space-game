package com.mday.client.event.type;

import com.mday.client.event.Event;
import com.mday.client.event.EventType;

/**
 * Represents the game is starting.
 */
public class StartEvent extends Event {
    /**
     * Create an instance of this event.
     */
    public StartEvent() {
        super(EventType.START);
    }
}
