package com.mday.event.type.coordinate;

import static com.mday.event.EventType.PAN_UP;

import com.mday.event.Event;

/**
 * Event telling the display surface to pan up.
 */
public class PanUpEvent extends Event {
    /**
     * Create an instance of this event.
     */
    public PanUpEvent() {
        super(PAN_UP);
    }
}
