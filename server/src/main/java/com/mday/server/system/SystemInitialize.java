package com.mday.server.system;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.mday.common.message.server.Initialize;

import javax.annotation.Nonnull;

/**
 * This actor is responsible for handling system initialization messages.
 */
public class SystemInitialize extends AbstractActor {
    private static final String NAME = SystemInitialize.class.getSimpleName();

    /**
     * Generate the actor for this class using the provided factory.
     *
     * @param actorRefFactory the factory to use when creating this actor
     * @return an {@link ActorRef} identifying the created actor
     */
    @Nonnull
    public static ActorRef create(@Nonnull final ActorRefFactory actorRefFactory) {
        return actorRefFactory.actorOf(Props.create(SystemInitialize.class, SystemInitialize::new), NAME);
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    @Nonnull
    public Receive createReceive() {
        return receiveBuilder().match(Initialize.class, initialize -> log.info("Received initialization request"))
                .build();
    }
}
