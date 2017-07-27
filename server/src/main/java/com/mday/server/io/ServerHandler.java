package com.mday.server.io;

import com.mday.common.message.Message;
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
        try {
            if (msg instanceof Message) {
                LOGGER.info("Received message: {}", msg);
            } else {
                LOGGER.warn("Unrecognized message: {}", msg.getClass().getName());
            }
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
