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
     * Move the selected units to a destination point.
     */
    UNIT_MOVE,

    /**
     * Zoom in on the display surface.
     */
    ZOOM_IN,

    /**
     * Zoom out from the display surface.
     */
    ZOOM_OUT,

    /**
     * Pan the display based on coordinate deltas.
     */
    PAN,

    /**
     * Pan the display surface up.
     */
    PAN_UP,

    /**
     * Pan the display surface down.
     */
    PAN_DOWN,

    /**
     * Pan the display surface left.
     */
    PAN_LEFT,

    /**
     * Pan the display surface right.
     */
    PAN_RIGHT,

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
