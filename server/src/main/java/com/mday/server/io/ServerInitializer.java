package com.mday.server.io;

import com.mday.common.serde.MessageDecoder;
import com.mday.common.serde.MessageEncoder;
import com.mday.common.serde.SerDeFactory;
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
    @Nonnull
    private final EventLoopGroup parentGroup;
    @Nonnull
    private final EventLoopGroup childGroup;

    @Nonnull
    private final SerDeFactory serDeFactory;

    /**
     * Parameter constructor.
     *
     * @param parentGroup the thread group used to receive connections
     * @param childGroup  the thread group used to process connections
     */
    public ServerInitializer(@Nonnull final EventLoopGroup parentGroup, @Nonnull final EventLoopGroup childGroup) {
        this.parentGroup = parentGroup;
        this.childGroup = childGroup;
        serDeFactory = new SerDeFactory();
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
        final MessageEncoder messageEncoder = new MessageEncoder(serDeFactory);
        final MessageDecoder messageDecoder = new MessageDecoder(serDeFactory);
        final ServerHandler serverHandler = new ServerHandler();
        socketChannel.pipeline().addLast(messageEncoder, messageDecoder, serverHandler);
    }
}
