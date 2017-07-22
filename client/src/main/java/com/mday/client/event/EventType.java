package com.mday.client.event;

/**
 * Defines the possible types of events.
 */
public enum EventType {
    /**
     * Indicates that the game is starting.
     */
    START,

    /**
     * Indicates that the game should quit.
     */
    QUIT,

    /**
     * A key input event.
     */
    KEY,

    /**
     * A mouse input event.
     */
    MOUSE,

    /**
     * A mouse wheel event.
     */
    MOUSE_WHEEL,
}
