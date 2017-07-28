package com.mday.client.event;

import javax.annotation.Nonnull;

/**
 * Defines the interface for consuming events.
 */
@FunctionalInterface
public interface EventConsumer {
    /**
     * Accept an event for processing.
     *
     * @param event the event to process
     */
    void accept(@Nonnull Event event);
}
