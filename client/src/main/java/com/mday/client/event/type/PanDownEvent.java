package com.mday.client.event.type;

import com.mday.client.event.Event;

import static com.mday.client.event.EventType.PAN_DOWN;

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
