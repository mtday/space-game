package com.mday.common.message;

import javax.annotation.Nonnull;

/**
 * The base class for message objects that can be sent and received between client and server.
 */
public abstract class Message {
    @Nonnull
    private final MessageType type;

    /**
     * Create a new message instance.
     *
     * @param type the type of this message
     */
    public Message(@Nonnull final MessageType type) {
        this.type = type;
    }

    /**
     * Retrieve the type of this message.
     *
     * @return the type of this message
     */
    @Nonnull
    public MessageType getType() {
        return type;
    }
}
