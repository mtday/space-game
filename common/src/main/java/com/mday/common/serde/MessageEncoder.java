package com.mday.common.serde;

import com.mday.common.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * Responsible for serializing messages over netty.
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {
    @Nonnull
    private final SerDeFactory serDeFactory;

    /**
     * Create an instance of this class.
     *
     * @param serDeFactory the factory that is managing {@link SerDe} implementations
     */
    public MessageEncoder(@Nonnull final SerDeFactory serDeFactory) {
        this.serDeFactory = serDeFactory;
    }

    @Override
    protected void encode(
            @Nonnull final ChannelHandlerContext ctx, @Nonnull final Message message, @Nonnull final ByteBuf byteBuf)
            throws Exception {
        final byte[] serialized = serialize(message);
        byteBuf.writeShort(serialized.length);
        byteBuf.writeBytes(serialized);
    }

    @Nonnull
    private byte[] serialize(@Nonnull final Message message) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (final DataOutputStream dos = new DataOutputStream(baos)) {
            final short classMarker = serDeFactory.getClassMarker(message.getClass());
            dos.writeShort(classMarker);
            serDeFactory.getSerDe(message).serialize(message, dos);
        }
        return baos.toByteArray();
    }
}
