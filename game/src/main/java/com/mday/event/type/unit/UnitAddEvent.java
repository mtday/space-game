package com.mday.event.type.unit;

import static com.mday.event.EventType.UNIT_ADD;

import com.mday.event.Event;
import com.mday.model.Unit;

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
