package com.mday.client.event.type.coordinate;

import com.mday.client.event.Event;

import javax.annotation.Nullable;
import java.awt.geom.Point2D;

import static com.mday.client.event.EventType.ZOOM_OUT;

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
