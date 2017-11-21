package com.mday.event.type.coordinate;

import static com.mday.event.EventType.PAN;

import com.mday.event.Event;

/**
 * Event telling the display surface to pan based on a delta with the current position.
 */
public class PanEvent extends Event {
    private final double deltaX;
    private final double deltaY;

    /**
     * Create an instance of this event.
     *
     * @param deltaX the amount to pan the display in the X coordinate
     * @param deltaY the amount to pan the display in the Y coordinate
     */
    public PanEvent(final double deltaX, final double deltaY) {
        super(PAN);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Retrieve the delta for panning in the X coordinate.
     *
     * @return the delta for panning in the X coordinate
     */
    public double getDeltaX() {
        return deltaX;
    }

    /**
     * Retrieve the delta for panning in the Y coordinate.
     *
     * @return the delta for panning in the Y coordinate
     */
    public double getDeltaY() {
        return deltaY;
    }
}
