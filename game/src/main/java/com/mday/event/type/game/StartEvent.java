package com.mday.event.type.game;

import static com.mday.event.EventType.START;

import com.mday.event.Event;

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
