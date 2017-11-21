package com.mday.event.type.coordinate;

import static com.mday.event.EventType.PAN_DOWN;

import com.mday.event.Event;

/**
 * Event telling the display surface to pan down.
 */
public class PanDownEvent extends Event {
    /**
     * Create an instance of this event.
     */
    public PanDownEvent() {
        super(PAN_DOWN);
    }
}
