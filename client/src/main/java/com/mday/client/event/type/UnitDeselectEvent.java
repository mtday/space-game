package com.mday.client.event.type;

import static com.mday.client.event.EventType.UNIT_DESELECT;

import com.mday.client.event.Event;

/**
 * An event representing the mouse being used to deselect units.
 */
public class UnitDeselectEvent extends Event {
    /**
     * Create an instance of this event class.
     */
    public UnitDeselectEvent() {
        super(UNIT_DESELECT);
    }
}
