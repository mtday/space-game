package com.mday.common.message.type;

import static com.mday.common.message.MessageType.LOGIN_REQUEST;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.mday.common.message.Message;
import com.mday.common.serde.SerDe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Nonnull;
import javax.xml.bind.DatatypeConverter;

/**
 * The message sent to the server to login.
 */
public class LoginRequest extends Message {
    @Nonnull
    private final String username;
    @Nonnull
    private final String password;

    /**
     * Attempt to perform a login with the server.
     *
     * @param username the user name to use when logging in with the server
     * @param password the password to use when logging in with the server
     */
    public LoginRequest(@Nonnull final String username, @Nonnull final String password) {
        super(LOGIN_REQUEST);
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieve the user name to use when logging in with the server.
     *
     * @return the user name to use when logging in with the server
     */
    @Nonnull
    public String getUsername() {
        return username;
    }

    /**
     * Retrieve the password to use when logging in with the server.
     *
     * @return the password to use when logging in with the server
     */
    @Nonnull
    public String getPassword() {
        return password;
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("LoginRequest[username=%s, password=%s]", getUsername(), getPassword());
    }

    /**
     * Provides support for serializing and deserializing login request messages.
     */
    public static class LoginRequestSerDe implements SerDe<LoginRequest> {
        @Nonnull
        private final MessageDigest messageDigest;

        /**
         * Create an instance of this class.
         */
        public LoginRequestSerDe() {
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (final NoSuchAlgorithmException invalidAlgorithm) {
                throw new RuntimeException("Unsupported message digest algorithm", invalidAlgorithm);
            }
        }

        @Nonnull
        @Override
        public Class<LoginRequest> getMessageClass() {
            return LoginRequest.class;
        }

        @Override
        public void serialize(@Nonnull final LoginRequest message, @Nonnull final DataOutputStream dataOutputStream)
                throws IOException {
            final byte[] digestedPassword = messageDigest.digest(message.getPassword().getBytes(UTF_8));

            dataOutputStream.writeByte(1); // The serialization version
            dataOutputStream.writeUTF(message.getUsername());
            dataOutputStream.writeUTF(DatatypeConverter.printHexBinary(digestedPassword));
        }

        @Nonnull
        @Override
        public LoginRequest deserialize(@Nonnull final DataInputStream dataInputStream) throws IOException {
            final int serializationVersion = dataInputStream.readByte();
            if (serializationVersion >= 1) {
                final String username = dataInputStream.readUTF();
                final String password = dataInputStream.readUTF();
                return new LoginRequest(username, password);
            } else {
                throw new IOException("Unsupported serialization version: " + serializationVersion);
            }
        }
    }
}
