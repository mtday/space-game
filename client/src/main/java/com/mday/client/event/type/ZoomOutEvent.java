package com.mday.client.event.type;

import static com.mday.client.event.EventType.ZOOM_IN;

import com.mday.client.event.Event;

/**
 * Represents the display surface should zoom out.
 */
public class ZoomOutEvent extends Event {
    private final int x;
    private final int y;

    /**
     * Create an instance of this event.
     *
     * @param x the X coordinate location to zoom out of
     * @param y the Y coordinate location to zoom out of
     */
    public ZoomOutEvent(final int x, final int y) {
        super(ZOOM_IN);
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieve the X coordinate location to zoom out of.
     *
     * @return the X coordinate location to zoom out of
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieve the Y coordinate location to zoom out of.
     *
     * @return the Y coordinate location to zoom out of
     */
    public int getY() {
        return y;
    }
}
