package com.mday.event.type.coordinate;

import static com.mday.event.EventType.PAN_RIGHT;

import com.mday.event.Event;

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
