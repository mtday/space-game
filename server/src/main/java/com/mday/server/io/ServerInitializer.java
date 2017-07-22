package com.mday.server.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.annotation.Nonnull;

/**
 * Initializes the server and binds the server handler.
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    private final ServerHandler serverHandler;
    private final EventLoopGroup parentGroup;
    private final EventLoopGroup childGroup;

    /**
     * Parameter constructor.
     *
     * @param parentGroup the thread group used to receive connections
     * @param childGroup  the thread group used to process connections
     */
    public ServerInitializer(@Nonnull final EventLoopGroup parentGroup, @Nonnull final EventLoopGroup childGroup) {
        serverHandler = new ServerHandler();
        this.parentGroup = parentGroup;
        this.childGroup = childGroup;
    }

    /**
     * Create the server bootstrap instance.
     *
     * @return the configured {@link ServerBootstrap}
     */
    @Nonnull
    public ServerBootstrap createServerBootstrap() {
        // @formatter:off
        return new ServerBootstrap()
                .group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(this)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        // @formatter:on
    }

    @Override
    protected void initChannel(@Nonnull final SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(serverHandler);
    }
}
