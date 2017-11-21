package com.mday.event.type.coordinate;

import static com.mday.event.EventType.ZOOM_OUT;

import com.mday.event.Event;

import java.awt.geom.Point2D;

import javax.annotation.Nullable;

/**
 * Event telling the display surface to zoom out.
 */
public class ZoomOutEvent extends Event {
    @Nullable
    private final Point2D.Double point;

    /**
     * Create an instance of this event.
     */
    public ZoomOutEvent() {
        this(null);
    }

    /**
     * Create an instance of this event.
     *
     * @param point the mouse point to use as the focus when zooming out
     */
    public ZoomOutEvent(@Nullable final Point2D.Double point) {
        super(ZOOM_OUT);
        this.point = point;
    }

    /**
     * Retrieve the mouse point to use as the focus when zooming out.
     *
     * @return the mouse point to use as the focus when zooming out
     */
    @Nullable
    public Point2D.Double getPoint() {
        return point;
    }
}
