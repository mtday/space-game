package com.mday.server.cluster;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.Props;

import javax.annotation.Nonnull;

/**
 * This actor is the parent for all of the other cluster actors.
 */
public final class ClusterActors extends AbstractActor {
    private static final String NAME = ClusterActors.class.getSimpleName();

    /**
     * Generate the actor for this class using the provided factory.
     *
     * @param actorRefFactory the factory to use when creating this actor
     * @return an {@link ActorRef} identifying the created actor
     */
    @Nonnull
    public static ActorRef create(@Nonnull final ActorRefFactory actorRefFactory) {
        return actorRefFactory.actorOf(Props.create(ClusterActors.class, ClusterActors::new), NAME);
    }

    private ClusterActors() {
        ClusterListener.create(getContext());
    }

    @Override
    @Nonnull
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
