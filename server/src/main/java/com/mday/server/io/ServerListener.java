package com.mday.server.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * Initializes the netty server to listen for connections.
 */
public class ServerListener {
    private final int port;

    /**
     * Create the listener.
     *
     * @param port the port on which to listen for incoming connections
     */
    public ServerListener(final int port) {
        this.port = port;
    }

    /**
     * Run the server and listen for connections.
     *
     * @throws InterruptedException if the server is interrupted
     */
    public void run() throws InterruptedException {
        final EventLoopGroup parentGroup = new NioEventLoopGroup();
        final EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            final ServerInitializer serverInitializer = new ServerInitializer(parentGroup, childGroup);
            final ServerBootstrap serverBootstrap = serverInitializer.createServerBootstrap();
            final ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }
}
