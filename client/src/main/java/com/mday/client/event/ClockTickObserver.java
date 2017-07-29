package com.mday.client.event;

/**
 * Defines the interface for handling game clock ticks.
 */
@FunctionalInterface
public interface ClockTickObserver {
    /**
     * Handle notification of the game clock ticking.
     */
    void tick();
}
