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
     * Indicates the creation of a new unit.
     */
    UNIT_ADD,

    /**
     * Indicates the removal of a unit.
     */
    UNIT_REMOVE,

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
