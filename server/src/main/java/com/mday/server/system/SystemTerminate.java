package com.mday.server.system;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.mday.common.message.server.Terminate;

import javax.annotation.Nonnull;

/**
 * This actor is responsible for handling system termination messages.
 */
public class SystemTerminate extends AbstractActor {
    private static final String NAME = SystemTerminate.class.getSimpleName();

    /**
     * Generate the actor for this class using the provided factory.
     *
     * @param actorRefFactory the factory to use when creating this actor
     * @return an {@link ActorRef} identifying the created actor
     */
    @Nonnull
    public static ActorRef create(@Nonnull final ActorRefFactory actorRefFactory) {
        return actorRefFactory.actorOf(Props.create(SystemTerminate.class, SystemTerminate::new), NAME);
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    @Nonnull
    public Receive createReceive() {
        return receiveBuilder().match(Terminate.class, terminate -> {
            log.info("Received terminate request");
            getContext().getSystem().terminate();
        }).build();
    }
}
