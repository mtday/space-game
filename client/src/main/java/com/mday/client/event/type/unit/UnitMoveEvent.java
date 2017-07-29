package com.mday.client.event.type.unit;

import com.mday.client.event.Event;

import javax.annotation.Nonnull;
import java.awt.geom.Point2D;

import static com.mday.client.event.EventType.UNIT_MOVE;

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
