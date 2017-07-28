package com.mday.client.io;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.EventType;
import com.mday.client.game.EventQueue;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

/**
 * Initializes the netty client to connect to the game server.
 */
public class ServerConnector implements EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerConnector.class);

    @Nonnull
    private final String host;
    private final int port;

    @Nonnull
    private final EventQueue eventQueue;

    @Nonnull
    private final EventLoopGroup serverGroup;

    /**
     * Create the connector.
     *
     * @param host the game server host to which we will connect
     * @param port the game server port on which we will connect
     * @param eventQueue the event queue onto which server messages will be placed for processing
     */
    public ServerConnector(@Nonnull final String host, final int port, @Nonnull final EventQueue eventQueue) {
        this.host = host;
        this.port = port;
        this.eventQueue = eventQueue;
        serverGroup = new NioEventLoopGroup();
    }

    /**
     * Run the client and communicate with the server.
     *
     * @throws InterruptedException if the server is interrupted
     */
    public void run() throws InterruptedException {
        try {
            LOGGER.info("Connecting to game server {}:{}", host, port);
            final ClientInitializer clientInitializer = new ClientInitializer(serverGroup, eventQueue);
            final Bootstrap bootstrap = clientInitializer.createBootstrap();
            final ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            LOGGER.info("Shutting down server connection.");
            serverGroup.shutdownGracefully();
        }
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event.getType() == EventType.QUIT) {
            LOGGER.info("Received quit event");
            serverGroup.shutdownGracefully();
        }
    }
}
