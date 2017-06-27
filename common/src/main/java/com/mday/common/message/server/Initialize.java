package com.mday.common.message.server;

import com.mday.common.Serializable;

import javax.annotation.Nonnull;

/**
 * The message used to tell the server to terminate.
 */
public class Initialize implements Serializable {
    @Override
    @Nonnull
    public String toString() {
        return getClass().getSimpleName();
    }
}
