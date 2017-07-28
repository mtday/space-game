package com.mday.client.event.type;

import static com.mday.client.event.EventType.START;

import com.mday.client.event.Event;

/**
 * Represents the game is starting.
 */
public class StartEvent extends Event {
    /**
     * Create an instance of this event.
     */
    public StartEvent() {
        super(START);
    }
}
