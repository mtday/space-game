
package com.mday.common.model;

import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Represents a location in coordinate space.
 */
public class Location {
    /**
     * The coordinate location on the X axis.
     */
    private final int x;

    /**
     * The coordinate location on the Y axis.
     */
    private final int y;

    /**
     * Default constructor.
     */
    public Location() {
        this(0, 0);
    }

    /**
     * Parameter constructor.
     *
     * @param x the X axis location
     * @param y the Y axis location
     */
    public Location(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieve the X axis location.
     *
     * @return the X axis location
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieve the Y axis location.
     *
     * @return the Y axis location
     */
    public int getY() {
        return y;
    }

    /**
     * Add the coordinate location provided to this location.
     *
     * @param other the location to add
     * @return a new location with the added coordinate values
     */
    @Nonnull
    public Location add(@Nonnull final Location other) {
        return new Location(getX() + other.getX(), getY() + other.getY());
    }

    /**
     * Add the coordinate location provided to this location.
     *
     * @param x the X coordinate location to add
     * @param y the Y coordinate location to add
     * @return a new location with the added coordinate values
     */
    @Nonnull
    public Location add(final int x, final int y) {
        return new Location(getX() + x, getY() + y);
    }

    /**
     * Subtract the coordinate location provided from this location.
     *
     * @param other the location to subtract
     * @return a new location with the subtracted coordinate values
     */
    @Nonnull
    public Location subtract(@Nonnull final Location other) {
        return new Location(getX() - other.getX(), getY() - other.getY());
    }

    /**
     * Subtract the coordinate location provided from this location.
     *
     * @param x the X coordinate location to subtract
     * @param y the Y coordinate location to subtract
     * @return a new location with the subtracted coordinate values
     */
    @Nonnull
    public Location subtract(final int x, final int y) {
        return new Location(getX() - x, getY() - y);
    }

    @Override
    public boolean equals(@CheckForNull final Object other) {
        if (!(other instanceof Location)) {
            return false;
        }

        final Location location = (Location) other;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("Location[x=%d, y=%d]", x, y);
    }
}

