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
     * Select units based on a rectangular region of the screen.
     */
    UNIT_SELECT,

    /**
     * Deselect units.
     */
    UNIT_DESELECT,

    /**
     * Zoom in on the display surface.
     */
    ZOOM_IN,

    /**
     * Zoom out from the display surface.
     */
    ZOOM_OUT,

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
