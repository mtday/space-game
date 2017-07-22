package com.mday.server;

import com.mday.server.io.ServerListener;

import javax.annotation.Nullable;

/**
 * Responsible for launching the game server.
 */
public final class Server {
    private final ServerListener serverListener;

    /**
     * Create the server instance.
     *
     * @param port the port on which to listen for incoming connections
     */
    public Server(final int port) {
        serverListener = new ServerListener(port);
    }

    /**
     * Run the server.
     *
     * @throws InterruptedException if the server is interrupted
     */
    public void run() throws InterruptedException {
        serverListener.run();
    }

    /**
     * The entry-point into this server.
     *
     * @param args the command-line arguments
     * @throws InterruptedException if the server is interrupted
     */
    public static void main(@Nullable final String... args) throws InterruptedException {
        new Server(33445).run();
    }
}
