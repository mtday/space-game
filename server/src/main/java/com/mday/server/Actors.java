package com.mday.server;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.Props;
import com.mday.server.cluster.ClusterActors;
import com.mday.server.system.SystemActors;

import javax.annotation.Nonnull;

/**
 * This actor is the supervisor for all of the other actors in this system.
 */
public final class Actors extends AbstractActor {
    private static final String NAME = Actors.class.getSimpleName();

    /**
     * Generate the actor for this class using the provided factory.
     *
     * @param actorRefFactory the factory to use when creating this actor
     * @return an {@link ActorRef} identifying the created actor
     */
    @Nonnull
    public static ActorRef create(@Nonnull final ActorRefFactory actorRefFactory) {
        return actorRefFactory.actorOf(Props.create(Actors.class, Actors::new), NAME);
    }

    private Actors() {
        SystemActors.create(getContext());
        ClusterActors.create(getContext());
    }

    @Override
    @Nonnull
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
