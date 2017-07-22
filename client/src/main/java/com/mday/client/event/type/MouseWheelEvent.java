package com.mday.client.event.type;

import com.mday.client.event.Event;
import com.mday.client.event.EventType;

import javax.annotation.Nonnull;

/**
 * An event representing a mouse wheel action.
 */
public class MouseWheelEvent extends Event {
    @Nonnull
    private final java.awt.event.MouseWheelEvent mouseWheelEvent;

    /**
     * Create an instance of this event class.
     *
     * @param mouseWheelEvent the mouse wheel event that occurred
     */
    public MouseWheelEvent(@Nonnull final java.awt.event.MouseWheelEvent mouseWheelEvent) {
        super(EventType.MOUSE_WHEEL);
        this.mouseWheelEvent = mouseWheelEvent;
    }

    /**
     * Retrieve the mouse wheel event.
     *
     * @return the mouse wheel event
     */
    @Nonnull
    public java.awt.event.MouseWheelEvent getMouseWheelEvent() {
        return mouseWheelEvent;
    }
}
