package com.mday.server.io;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

/**
 * Responsible for handling messages sent to this server.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelRead(@Nonnull final ChannelHandlerContext ctx, @Nonnull final Object msg) throws Exception {
        final ByteBuf byteBuf = (ByteBuf) msg;
        try {
            LOGGER.info("Received message: {}", byteBuf.toString(UTF_8));
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
