package com.mday.common.serde;

import com.mday.common.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Responsible for deserializing messages over netty.
 */
public class MessageDecoder extends ByteToMessageDecoder {
    @Nonnull
    private final SerDeFactory serDeFactory;

    /**
     * Create an instance of this class.
     *
     * @param serDeFactory the factory that is managing {@link SerDe} implementations
     */
    public MessageDecoder(@Nonnull final SerDeFactory serDeFactory) {
        this.serDeFactory = serDeFactory;
    }

    @Override
    protected void decode(
            @Nonnull final ChannelHandlerContext channelHandlerContext, @Nonnull final ByteBuf byteBuf,
            @Nonnull final List<Object> list) throws Exception {
        final int readableBytes = byteBuf.readableBytes();
        if (readableBytes > 2) {
            byteBuf.markReaderIndex();
            final int len = byteBuf.readShort();
            if (readableBytes >= len + 2) {
                final byte[] bytes = new byte[len];
                byteBuf.readBytes(bytes);
                list.add(deserialize(bytes));
            } else {
                // Not enough bytes available yet.
                byteBuf.resetReaderIndex();
            }
        }
    }

    @Nonnull
    private Message deserialize(@Nonnull final byte[] data) throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try (final DataInputStream dis = new DataInputStream(bais)) {
            final short classMarker = dis.readShort();
            return serDeFactory.getSerDe(classMarker).deserialize(dis);
        }
    }
}
