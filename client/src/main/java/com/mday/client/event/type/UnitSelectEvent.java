package com.mday.client.event.type;

import static com.mday.client.event.EventType.UNIT_SELECT;

import com.mday.client.event.Event;

import java.awt.geom.Point2D;

import javax.annotation.Nonnull;

/**
 * An event representing the mouse being used to select a rectangular area on the display surface.
 */
public class UnitSelectEvent extends Event {
    private final Point2D.Double topLeft;
    private final Point2D.Double bottomRight;

    /**
     * Create an instance of this event class.
     *
     * @param topLeft the top-left point of the selection rectangle
     * @param bottomRight bottom-right point of the selection rectangle
     */
    public UnitSelectEvent(@Nonnull final Point2D.Double topLeft, @Nonnull final Point2D.Double bottomRight) {
        super(UNIT_SELECT);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * Retrieve the top-left point of the selection rectangle.
     *
     * @return the top-left point of the selection rectangle
     */
    @Nonnull
    public Point2D.Double getTopLeft() {
        return topLeft;
    }

    /**
     * Retrieve the bottom-right point of the selection rectangle.
     *
     * @return the bottom-right point of the selection rectangle
     */
    @Nonnull
    public Point2D.Double getBottomRight() {
        return bottomRight;
    }
}
