package com.mday.client;

import com.mday.client.action.EscapeKeyAction;
import com.mday.client.event.type.QuitEvent;
import com.mday.client.game.EventQueue;
import com.mday.client.game.Runner;
import com.mday.client.ui.Display;

import javax.annotation.Nullable;

/**
 * Starts the game client.
 */
public class Client {
    private final EventQueue eventQueue;
    private final Runner runner;

    /**
     * Create an instance of the client.
     */
    public Client() {
        eventQueue = new EventQueue();
        runner = new Runner(eventQueue);
        runner.addEventConsumer(new Display(eventQueue));
        runner.addEventConsumer(new EscapeKeyAction(eventQueue));
    }

    /**
     * Run the client.
     */
    public void run() {
        try {
            runner.start();
            Thread.sleep(20000);
        } catch (final InterruptedException interrupted) {
            // Ignored.
        } finally {
            eventQueue.add(new QuitEvent());
        }
    }

    /**
     * The entry-point into the game client.
     *
     * @param args the command-line arguments
     */
    public static void main(@Nullable final String... args) {
        new Client().run();
    }
}
