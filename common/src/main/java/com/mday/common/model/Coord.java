
package com.mday.common.model;

import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Represents a coordinate location within a hexagonal region. Based on: http://www.redblobgames.com/grids/hexagons/
 */
public class Coord {
    /**
     * The coordinate location on the X axis.
     */
    private final int x;

    /**
     * The coordinate location on the Y axis.
     */
    private final int y;

    /**
     * The coordinate location on the Z axis.
     */
    private final int z;

    /**
     * Default constructor.
     */
    public Coord() {
        this(0, 0);
    }

    /**
     * Constructor based on two coordinate locations. Since {@code x + y + z = 0}, we can calculate the {@code y} value.
     *
     * @param x the X axis location
     * @param z the Z axis location
     */
    public Coord(final int x, final int z) {
        this(x, -x - z, z);
    }

    /**
     * Parameter constructor.
     *
     * @param x the X axis location
     * @param y the Y axis location
     * @param z the Z axis location
     */
    public Coord(final int x, final int y, final int z) {
        if (x + y + z != 0) {
            throw new IllegalArgumentException("Invalid coordinate: " + x + ", " + y + ", " + z);
        }

        this.x = x;
        this.y = y;
        this.z = z;
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
     * Retrieve the Z axis location.
     *
     * @return the Z axis location
     */
    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(@CheckForNull final Object other) {
        if (!(other instanceof Coord)) {
            return false;
        }

        final Coord coord = (Coord) other;
        return x == coord.x && z == coord.z; // No need to include y.
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z); // No need to include y.
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("Coord[x=%d, y=%d, z=%d]", x, y, z);
    }
}

