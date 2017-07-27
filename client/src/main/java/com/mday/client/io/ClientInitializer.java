package com.mday.client.io;

import com.mday.client.game.EventQueue;
import com.mday.common.serde.MessageDecoder;
import com.mday.common.serde.MessageEncoder;
import com.mday.common.serde.SerDeFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.annotation.Nonnull;

/**
 * Initializes the client and binds the client handler.
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    @Nonnull
    private final EventLoopGroup serverGroup;
    @Nonnull
    private final EventQueue eventQueue;

    @Nonnull
    private final SerDeFactory serDeFactory;

    /**
     * Parameter constructor.
     *
     * @param serverGroup the thread group used to communicate with the game server
     * @param eventQueue the event queue onto which server messages will be placed for processing
     */
    public ClientInitializer(@Nonnull final EventLoopGroup serverGroup, @Nonnull final EventQueue eventQueue) {
        this.serverGroup = serverGroup;
        this.eventQueue = eventQueue;
        serDeFactory = new SerDeFactory();
    }

    /**
     * Create the bootstrap instance.
     *
     * @return the configured {@link Bootstrap}
     */
    @Nonnull
    public Bootstrap createBootstrap() {
        // @formatter:off
        return new Bootstrap()
                .group(serverGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(this);
        // @formatter:on
    }

    @Override
    protected void initChannel(@Nonnull final SocketChannel socketChannel) throws Exception {
        final MessageEncoder messageEncoder = new MessageEncoder(serDeFactory);
        final MessageDecoder messageDecoder = new MessageDecoder(serDeFactory);
        final ClientHandler clientHandler = new ClientHandler(eventQueue);
        socketChannel.pipeline().addLast(messageEncoder, messageDecoder, clientHandler);
    }
}
