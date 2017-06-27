package com.mday.server.system;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.Props;

import javax.annotation.Nonnull;

/**
 * This actor is the parent for all of the other system actors.
 */
public final class SystemActors extends AbstractActor {
    private static final String NAME = SystemActors.class.getSimpleName();

    /**
     * Generate the actor for this class using the provided factory.
     *
     * @param actorRefFactory the factory to use when creating this actor
     * @return an {@link ActorRef} identifying the created actor
     */
    @Nonnull
    public static ActorRef create(@Nonnull final ActorRefFactory actorRefFactory) {
        return actorRefFactory.actorOf(Props.create(SystemActors.class, SystemActors::new), NAME);
    }

    private SystemActors() {
        SystemInitialize.create(getContext());
        SystemTerminate.create(getContext());
    }

    @Override
    @Nonnull
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
