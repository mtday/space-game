package com.mday.common.serde;

import static java.util.Optional.ofNullable;

import com.mday.common.message.Message;
import com.mday.common.message.type.LoginRequest;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

/**
 * Manages all of the supported serialization and deserialization classes.
 */
public class SerDeFactory {
    @Nonnull
    private final ConcurrentHashMap<Class<?>, SerDe<?>> byClass = new ConcurrentHashMap<>();
    @Nonnull
    private final ConcurrentHashMap<Short, SerDe<?>> byMarker = new ConcurrentHashMap<>();

    /**
     * Create an instance of this class.
     */
    public SerDeFactory() {
        add(LoginRequest.class, new LoginRequest.LoginRequestSerDe());
    }

    private void add(@Nonnull final Class<? extends Message> clazz, @Nonnull final SerDe<?> serde) {
        byClass.put(clazz, serde);
        byMarker.put(getClassMarker(clazz), serde);
    }

    /**
     * Retrieve the class marker value for the provided message class.
     *
     * @param clazz the message class for which the marker should be returned
     * @return the class marker value for the provided message class
     */
    public short getClassMarker(@Nonnull final Class<? extends Message> clazz) {
        return (short) clazz.getName().hashCode();
    }

    /**
     * Retrieve the {@link SerDe} for the specified message.
     *
     * @param message the message for which a {@link SerDe} will be retrieved
     * @param <M> the message type
     * @return the requested {@link SerDe}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public <M extends Message> SerDe<M> getSerDe(@Nonnull final M message) {
        return (SerDe<M>) ofNullable(byClass.get(message.getClass())).orElseThrow(() ->
                new RuntimeException("No registered SerDe for class: " + message.getClass().getName()));
    }

    /**
     * Retrieve the {@link SerDe} for the specified class marker value.
     *
     * @param classMarker the class marker value
     * @return the requested {@link SerDe}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public SerDe<?> getSerDe(final short classMarker) {
        return ofNullable(byMarker.get(classMarker)).orElseThrow(() ->
                new RuntimeException("No registered SerDe for class marker: " + classMarker));
    }
}
