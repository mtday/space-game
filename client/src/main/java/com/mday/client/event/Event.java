package com.mday.client.event;

import javax.annotation.Nonnull;

/**
 * The class representing game events.
 */
public abstract class Event {
    @Nonnull
    private final EventType type;

    /**
     * Create an instance of this class.
     *
     * @param type the type of event represented
     */
    public Event(@Nonnull final EventType type) {
        this.type = type;
    }

    /**
     * Retrieve the type of this event.
     *
     * @return the type of this event
     */
    @Nonnull
    public EventType getType() {
        return type;
    }
}
