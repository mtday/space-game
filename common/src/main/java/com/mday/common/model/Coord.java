
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
    public final int x;

    /**
     * The coordinate location on the Y axis.
     */
    public final int y;

    /**
     * The coordinate location on the Z axis.
     */
    public final int z;

    /**
     * Default constructor.
     */
    public Coord() {
        this(0, 0);
    }

    /**
     * Constructor based on two coordinate locations. Since {@code x + y + z = 0}, we can calculate the {@code z} value.
     *
     * @param x the X axis location
     * @param y the Y axis location
     */
    public Coord(final int x, final int y) {
        this(x, y, -x - y);
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

    @Override
    public boolean equals(@CheckForNull final Object other) {
        if (!(other instanceof Coord)) {
            return false;
        }

        final Coord coord = (Coord) other;
        return x == coord.x && y == coord.y; // No need to include z.
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y); // No need to include z.
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("Coord[x=%d, y=%d, z=%d]", x, y, z);
    }
}

