package com.mday.event.type.unit;

import static com.mday.event.EventType.UNIT_MOVE;

import com.mday.event.Event;

import java.awt.geom.Point2D;

import javax.annotation.Nonnull;

/**
 * An event representing the mouse being used to request selected units be moved to a specific point.
 */
public class UnitMoveEvent extends Event {
    private final Point2D.Double destination;

    /**
     * Create an instance of this event class.
     *
     * @param destination the destination point on the map to which the selected units should move
     */
    public UnitMoveEvent(@Nonnull final Point2D.Double destination) {
        super(UNIT_MOVE);
        this.destination = destination;
    }

    /**
     * Retrieve the destination point on the map to which the selected units should move.
     *
     * @return the destination point on the map to which the selected units should move
     */
    @Nonnull
    public Point2D.Double getDestination() {
        return destination;
    }
}
