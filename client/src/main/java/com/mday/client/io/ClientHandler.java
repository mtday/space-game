package com.mday.client.io;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.mday.client.game.EventQueue;
import com.mday.common.message.type.LoginRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

/**
 * Responsible for handling messages from the game server.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    @Nonnull
    private final EventQueue eventQueue;

    /**
     * Create an instance of this handler.
     *
     * @param eventQueue the event queue onto which server messages will be added for processing
     */
    public ClientHandler(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void channelActive(@Nonnull final ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("Sending login");
        //final ByteBuf byteBuf = ctx.alloc().buffer("hello".length());
        //byteBuf.writeCharSequence("hello", StandardCharsets.UTF_8);
        ctx.channel().writeAndFlush(new LoginRequest("user", "pass"));
    }

    @Override
    public void channelRead(@Nonnull final ChannelHandlerContext ctx, @Nonnull final Object msg) throws Exception {
        final ByteBuf byteBuf = (ByteBuf) msg;
        try {
            LOGGER.info("Received message: {}", byteBuf.toString(UTF_8));
            // TODO: Add to event queue
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(@Nonnull final ChannelHandlerContext ctx, @Nonnull final Throwable cause) {
        // Close the connection when an exception is raised.
        LOGGER.error("Problem handling request", cause);
        ctx.close();
    }
}
