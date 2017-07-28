package com.mday.client.event.type;

import static com.mday.client.event.EventType.UNIT_REMOVE;

import com.mday.client.event.Event;
import com.mday.common.model.Unit;

import javax.annotation.Nonnull;

/**
 * An event indicating that a unit should be removed.
 */
public class UnitRemoveEvent extends Event {
    @Nonnull
    private final Unit unit;

    /**
     * Create an instance of this class.
     *
     * @param unit the unit to be removed
     */
    public UnitRemoveEvent(@Nonnull final Unit unit) {
        super(UNIT_REMOVE);
        this.unit = unit;
    }

    /**
     * Retrieve the unit to be removed.
     *
     * @return the unit to be removed
     */
    @Nonnull
    public Unit getUnit() {
        return unit;
    }
}
