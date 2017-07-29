package com.mday.client.event.type;

import com.mday.client.event.Event;

import static com.mday.client.event.EventType.PAN_UP;

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
