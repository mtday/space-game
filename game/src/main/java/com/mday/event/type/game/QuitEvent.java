package com.mday.event.type.game;

import static com.mday.event.EventType.QUIT;

import com.mday.event.Event;

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
