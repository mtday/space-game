package com.mday.client.event.type.unit;

import static com.mday.client.event.EventType.UNIT_ADD;

import com.mday.client.event.Event;
import com.mday.common.model.Unit;

import javax.annotation.Nonnull;

/**
 * An event indicating that a unit should be added.
 */
public class UnitAddEvent extends Event {
    @Nonnull
    private final Unit unit;

    /**
     * Create an instance of this class.
     *
     * @param unit the unit to be added
     */
    public UnitAddEvent(@Nonnull final Unit unit) {
        super(UNIT_ADD);
        this.unit = unit;
    }

    /**
     * Retrieve the unit to be added.
     *
     * @return the unit to be added
     */
    @Nonnull
    public Unit getUnit() {
        return unit;
    }
}
