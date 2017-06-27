package com.mday.server;

import akka.actor.ActorSystem;
import com.mday.server.http.Routes;

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
        final ActorSystem actorSystem = ActorSystem.create(SYSTEM_NAME);
        Actors.create(actorSystem);
        Routes.create(actorSystem);
    }
}
