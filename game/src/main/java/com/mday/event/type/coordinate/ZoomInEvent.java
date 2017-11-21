package com.mday.event.type.coordinate;

import static com.mday.event.EventType.ZOOM_IN;

import com.mday.event.Event;

import java.awt.geom.Point2D;

import javax.annotation.Nullable;

/**
 * Event telling the display surface to zoom in.
 */
public class ZoomInEvent extends Event {
    @Nullable
    private final Point2D.Double point;

    /**
     * Create an instance of this event.
     */
    public ZoomInEvent() {
        this(null);
    }

    /**
     * Create an instance of this event.
     *
     * @param point the mouse point to use as the focus when zooming in
     */
    public ZoomInEvent(@Nullable final Point2D.Double point) {
        super(ZOOM_IN);
        this.point = point;
    }

    /**
     * Retrieve the mouse point to use as the focus when zooming in.
     *
     * @return the mouse point to use as the focus when zooming in
     */
    @Nullable
    public Point2D.Double getPoint() {
        return point;
    }
}
