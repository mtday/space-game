package com.mday.event.type.unit;

import static com.mday.event.EventType.UNIT_DESELECT;

import com.mday.event.Event;

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
