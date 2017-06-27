package com.mday.server.http;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.util.concurrent.CompletionStage;

import javax.annotation.Nonnull;

/**
 * This class is responsible for initializing the HTTP infrastructure of this system.
 */
public final class Routes extends AllDirectives {
    private static final String NAME = Routes.class.getSimpleName();

    /**
     * Create an instance of this class.
     *
     * @param actorSystem the actor system to use when creating the http infrastructure
     * @return an instance of this class
     */
    @Nonnull
    public static Routes create(@Nonnull final ActorSystem actorSystem) {
        return new Routes(actorSystem);
    }

    private final CompletionStage<ServerBinding> serverBinding;

    private Routes(@Nonnull final ActorSystem actorSystem) {
        final Http http = Http.get(actorSystem);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = createRoute().flow(actorSystem, actorMaterializer);
        serverBinding = http.bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), actorMaterializer);
    }

    @Nonnull
    private Route createRoute() {
        return route(path("hello", () -> get(() -> complete("<h1>Say hello to akka-http</h1>"))));
    }
}
