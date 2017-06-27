package com.mday.server.cluster;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.Member;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import javax.annotation.Nonnull;

/**
 * This actor listens for cluster change messages and logs them.
 */
public class ClusterListener extends AbstractActor {
    private static final String NAME = ClusterListener.class.getSimpleName();

    /**
     * Generate the actor for this class using the provided factory.
     *
     * @param actorRefFactory the factory to use when creating this actor
     * @return an {@link ActorRef} identifying the created actor
     */
    @Nonnull
    public static ActorRef create(@Nonnull final ActorRefFactory actorRefFactory) {
        return actorRefFactory.actorOf(Props.create(ClusterListener.class, ClusterListener::new), NAME);
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final Cluster cluster = Cluster.get(getContext().getSystem());

    @Override
    public void preStart() {
        log.info("Subscribing to cluster events");
        // @formatter:off
        cluster.subscribe(getSelf(), ClusterEvent.initialStateAsSnapshot(),
                ClusterEvent.MemberUp.class,
                ClusterEvent.MemberRemoved.class,
                ClusterEvent.UnreachableMember.class,
                ClusterEvent.MemberEvent.class);
        // @formatter:on
    }

    @Override
    public void postStop() {
        log.info("Unsubscribing from cluster events");
        cluster.unsubscribe(getSelf());
    }

    private void handleClusterState(@Nonnull final ClusterEvent.CurrentClusterState clusterState) {
        log.info("Cluster state:");
        for (final Member member : clusterState.getMembers()) {
            log.info("  {}", member);
        }
    }

    private void handleMemberUp(@Nonnull final ClusterEvent.MemberUp memberUp) {
        log.info("Cluster member up: {}", memberUp.member());
    }

    private void handleMemberUnreachable(@Nonnull final ClusterEvent.UnreachableMember memberUnreachable) {
        log.info("Cluster member detected as unreachable: {}", memberUnreachable.member());
    }

    private void handleMemberRemoved(@Nonnull final ClusterEvent.MemberRemoved memberRemoved) {
        log.info("Cluster member removed: {}", memberRemoved.member());
    }

    private void handleMemberEvent(@Nonnull final ClusterEvent.MemberEvent memberEvent) {
        // Ignored.
    }

    @Override
    @Nonnull
    public Receive createReceive() {
        // @formatter:off
        return receiveBuilder()
                .match(ClusterEvent.CurrentClusterState.class, this::handleClusterState)
                .match(ClusterEvent.MemberUp.class, this::handleMemberUp)
                .match(ClusterEvent.UnreachableMember.class, this::handleMemberUnreachable)
                .match(ClusterEvent.MemberRemoved.class, this::handleMemberRemoved)
                .match(ClusterEvent.MemberEvent.class, this::handleMemberEvent)
                .build();
        // @formatter:on
    }
}
