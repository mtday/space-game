package com.mday.event.type.coordinate;

import static com.mday.event.EventType.PAN_LEFT;

import com.mday.event.Event;

/**
 * Event telling the display surface to pan left.
 */
public class PanLeftEvent extends Event {
    /**
     * Create an instance of this event.
     */
    public PanLeftEvent() {
        super(PAN_LEFT);
    }
}
