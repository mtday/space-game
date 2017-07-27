package com.mday.common.serde;

import com.mday.common.message.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * Responsible for performing serialization and deserialization of messages.
 *
 * @param <M> the type of message for which serialization and deserialization is supported
 */
public interface SerDe<M extends Message> {
    /**
     * Retrieve the message class that is serialized and deserialized by this class.
     *
     * @return the message class that is serialized and deserialized by this class
     */
    @Nonnull
    Class<M> getMessageClass();

    /**
     * Serialize the provided message into the data output stream.
     *
     * @param message the message to be serialized
     * @param dataOutputStream the output stream into which the message will be written
     * @throws IOException if there is a problem serializing the message
     */
    void serialize(@Nonnull M message, @Nonnull DataOutputStream dataOutputStream) throws IOException;

    /**
     * Deserialize the data from the provided input stream back into a message.
     *
     * @param dataInputStream the input stream from which the message will be read
     * @return the deserialized message
     * @throws IOException if there is a problem deserializing the data back into a message
     */
    @Nonnull
    M deserialize(@Nonnull DataInputStream dataInputStream) throws IOException;
}
