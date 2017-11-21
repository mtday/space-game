package com.mday.game;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import com.mday.event.ClockTickObserver;
import com.mday.event.Event;
import com.mday.event.EventConsumer;
import com.mday.event.EventType;
import com.mday.event.type.game.QuitEvent;
import com.mday.ui.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.Nonnull;

/**
 * Responsible for managing the game event loop.
 */
public class Runner implements Runnable, EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private static final int TARGET_FPS = 60;

    @Nonnull
    private final EventQueue eventQueue;
    @Nonnull
    private final Display display;

    @Nonnull
    private final ScheduledExecutorService scheduledExecutorService;
    @Nonnull
    private final Queue<EventConsumer> eventConsumers;
    @Nonnull
    private final Queue<ClockTickObserver> clockTickObservers;

    private ScheduledFuture<?> scheduledFuture = null;

    /**
     * Create an instance of this class.
     *
     * @param eventQueue the event queue that has all of the events that need to be processed
     * @param display the display on which the game will be drawn
     */
    public Runner(@Nonnull final EventQueue eventQueue, @Nonnull final Display display) {
        this.eventQueue = eventQueue;
        this.display = display;
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.eventConsumers = new ConcurrentLinkedQueue<>();
        this.eventConsumers.add(this);
        this.clockTickObservers = new ConcurrentLinkedQueue<>();
    }

    /**
     * Add the provided event consumer.
     *
     * @param eventConsumer the event consumer that should receive game events
     */
    public void addEventConsumer(@Nonnull final EventConsumer eventConsumer) {
        this.eventConsumers.add(eventConsumer);
    }

    /**
     * Add the provided clock tick observer.
     *
     * @param clockTickObserver the clock tick observer that should receive notification of each clock tick
     */
    public void addClockTickObserver(@Nonnull final ClockTickObserver clockTickObserver) {
        this.clockTickObservers.add(clockTickObserver);
    }

    /**
     * Start processing events in this runner.
     */
    public void start() {
        final long period = SECONDS.toNanos(1) / TARGET_FPS;
        scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(this, 0L, period, NANOSECONDS);
    }

    /**
     * Stop this runner.
     */
    public void stop() {
        scheduledFuture.cancel(false);
        scheduledExecutorService.shutdown();
    }

    @Override
    public void run() {
        try {
            notifyClockTickObservers();
            consumeEvents();
            updateDisplay();
        } catch (final Exception exception) {
            LOGGER.error("Error in main game loop", exception);
            eventQueue.clear();
            eventQueue.add(new QuitEvent());
            consumeEvents();
        }
    }

    private void notifyClockTickObservers() {
        clockTickObservers.forEach(ClockTickObserver::tick);
    }

    private void consumeEvents() {
        while (!eventQueue.isEmpty()) {
            Optional.ofNullable(eventQueue.poll())
                    .ifPresent(event -> eventConsumers.forEach(consumer -> consumer.accept(event)));
        }
    }

    private void updateDisplay() {
        display.render();
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event.getType() == EventType.QUIT) {
            stop();
        }
    }
}
