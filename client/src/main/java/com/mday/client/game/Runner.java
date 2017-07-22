package com.mday.client.game;

import com.mday.client.event.Event;
import com.mday.client.event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

/**
 * Responsible for managing the game event loop.
 */
public class Runner implements Runnable, Consumer<Event> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    @Nonnull
    private final EventQueue eventQueue;

    @Nonnull
    private final ExecutorService executorService;
    @Nonnull
    private final Queue<Consumer<Event>> eventConsumers;

    private volatile boolean running = false;

    /**
     * Create an instance of this class.
     *
     * @param eventQueue the event queue that has all of the events that need to be processed
     */
    public Runner(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
        this.executorService = Executors.newFixedThreadPool(3);
        this.eventConsumers = new ConcurrentLinkedQueue<>();
        this.eventConsumers.add(this);
    }

    /**
     * Add the provided event consumer.
     *
     * @param eventConsumer the event consumer that should receive game events
     */
    public void addEventConsumer(@Nonnull final Consumer<Event> eventConsumer) {
        this.eventConsumers.add(eventConsumer);
    }

    /**
     * Start processing events in this runner.
     */
    public void start() {
        executorService.execute(this);
    }

    /**
     * Stop this runner.
     */
    public void stop() {
        running = false;
        executorService.shutdown();
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            consumeEvents();
            updateDisplay();
        }
    }

    private void consumeEvents() {
        while (!eventQueue.isEmpty()) {
            final Event event = eventQueue.poll();
            if (event != null) {
                eventConsumers.forEach(consumer -> consumer.accept(event));
            }
        }
    }

    private void updateDisplay() {
        // TODO
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event.getType() == EventType.QUIT) {
            stop();
        }
    }
}
