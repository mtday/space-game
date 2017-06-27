package com.mday.server;

import akka.actor.ActorSystem;

import javax.annotation.Nullable;

/**
 * Responsible for launching an Akka server instance.
 */
public final class Server {
    private static final String SYSTEM_NAME = "mday";

    /**
     * Create the server instance.
     *
     * @param args the command-line parameters
     */
    public static void main(@Nullable final String... args) {
        Actors.create(ActorSystem.create(SYSTEM_NAME));
    }
}
