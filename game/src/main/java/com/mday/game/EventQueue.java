package com.mday.game;

import com.mday.event.Event;
import com.mday.event.type.game.StartEvent;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Holds all of the events that need to be processed.
 */
public class EventQueue extends LinkedBlockingQueue<Event> {
    private static final long serialVersionUID = 1L;

    /**
     * Create an instance of this event queue.
     */
    public EventQueue() {
        // Add a start event so we immediately have something to process.
        add(new StartEvent());
    }
}
