
package com.mday.model;

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
    private final double x;

    /**
     * The coordinate location on the Y axis.
     */
    private final double y;

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
    public Location(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieve the X axis location.
     *
     * @return the X axis location
     */
    public double getX() {
        return x;
    }

    /**
     * Retrieve the Y axis location.
     *
     * @return the Y axis location
     */
    public double getY() {
        return y;
    }

    /**
     * Retrieve the length of the location point from the origin.
     *
     * @return the length of the location point from the origin
     */
    public double getLength() {
        return Math.sqrt((getX() * getX()) + (getY() * getY()));
    }

    /**
     * Retrieve the distance between this location and the one provided.
     *
     * @param location the location to calculate distance with
     * @return the distance between this location and the one provided
     */
    public double getDistanceTo(@Nonnull final Location location) {
        return subtract(location).getLength();
    }

    /**
     * Retrieve the normalized location.
     *
     * @return the normalized location
     */
    @Nonnull
    public Location getNormalized() {
        final double length = getLength();
        return new Location(getX() / length, getY() / length);
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
    public Location add(final double x, final double y) {
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
    public Location subtract(final double x, final double y) {
        return new Location(getX() - x, getY() - y);
    }

    /**
     * Multiply the coordinate location by the provided scalar.
     *
     * @param scalar the multiplier
     * @return a new location with the coordinates multiplied by the provided scalar
     */
    @Nonnull
    public Location multiply(final double scalar) {
        return new Location(getX() * scalar, getY() * scalar);
    }

    /**
     * Determine if this location is contained within the provided bounding box.
     *
     * @param topLeft     the top-left location of the bounding box
     * @param bottomRight the bottom-right location of the bounding box
     * @return whether this location is inside the bounding box
     */
    public boolean isInside(@Nonnull final Location topLeft, @Nonnull final Location bottomRight) {
        return topLeft.getX() <= getX() && topLeft.getY() <= getY()
                && bottomRight.getX() >= getX() && bottomRight.getY() >= getY();
    }

    /**
     * Determine if this location is contained within the provided bounding box, including any area within radius of
     * this location.
     *
     * @param topLeft     the top-left location of the bounding box
     * @param bottomRight the bottom-right location of the bounding box
     * @param radius      the radius indicating the size of the object at this location
     * @return whether this location is inside the bounding box
     */
    public boolean isInside(@Nonnull final Location topLeft, @Nonnull final Location bottomRight, final double radius) {
        return isInside(topLeft.subtract(radius, radius), bottomRight.add(radius, radius));
    }

    @Override
    public boolean equals(@CheckForNull final Object other) {
        if (!(other instanceof Location)) {
            return false;
        }

        final Location location = (Location) other;
        return Math.abs(x - location.x) < 0.00001 && Math.abs(y - location.y) < 0.00001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("Location[x=%.5f, y=%.5f]", x, y);
    }
}

