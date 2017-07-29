package com.mday.client.event.type;

import com.mday.client.event.Event;

import static com.mday.client.event.EventType.PAN_LEFT;

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
