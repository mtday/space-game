package com.mday.client.event.type.coordinate;

import com.mday.client.event.Event;

import static com.mday.client.event.EventType.PAN_RIGHT;

/**
 * Event telling the display surface to pan right.
 */
public class PanRightEvent extends Event {
    /**
     * Create an instance of this event.
     */
    public PanRightEvent() {
        super(PAN_RIGHT);
    }
}
