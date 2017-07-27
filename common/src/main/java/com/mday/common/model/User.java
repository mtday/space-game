package com.mday.common.model;

import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Represents a user account.
 */
public class User {
    @Nonnull
    private final String id;

    /**
     * Create a new user instance.
     *
     * @param id the unique id of this user
     */
    public User(@Nonnull final String id) {
        this.id = id;
    }

    /**
     * The unique id of this unit.
     *
     * @return the unique id of this unit
     */
    @Nonnull
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(@CheckForNull final Object other) {
        if (!(other instanceof User)) {
            return false;
        }

        final User user = (User) other;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("User[id=%s]", getId());
    }
}
