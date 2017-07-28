package com.mday.common.model;

import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * The base class for units.
 */
public class Unit implements Comparable<Unit> {
    @Nonnull
    private final UnitType type;
    @Nonnull
    private final String id;
    @Nonnull
    private final String owner;
    @Nonnull
    private Location location;
    private int direction = 0;

    /**
     * Create a new unit instance.
     *
     * @param type the type of this unit
     * @param id the unique id of this unit
     * @param owner the id of the owner of this unit
     * @param location the location of this unit
     */
    public Unit(@Nonnull final UnitType type, @Nonnull final String id, @Nonnull final String owner,
            @Nonnull final Location location) {
        this.type = type;
        this.id = id;
        this.owner = owner;
        this.location = location;
    }

    /**
     * Retrieve the type of this unit.
     *
     * @return the type of this unit
     */
    @Nonnull
    public UnitType getType() {
        return type;
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

    /**
     * Retrieve the id of the owner of this unit.
     *
     * @return the id of the owner of this unit
     */
    @Nonnull
    public String getOwner() {
        return owner;
    }

    /**
     * Retrieve the location of this unit.
     *
     * @return the location of this unit
     */
    @Nonnull
    public Location getLocation() {
        return location;
    }

    /**
     * Set the location of this unit.
     *
     * @param location the new location of this unit
     */
    public void setLocation(@Nonnull final Location location) {
        this.location = location;
    }

    /**
     * Retrieve the angular direction of this unit in degrees.
     *
     * @return the angular direction of this unit in degrees
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Set the angular direction of this unit in degrees.
     *
     * @param direction the new angular direction of this unit in degrees
     */
    public void setDirection(final int direction) {
        this.direction = direction;
    }

    @Override
    public int compareTo(@Nullable final Unit unit) {
        if (unit == null) {
            return 1;
        }

        return getId().compareTo(unit.getId());
    }

    @Override
    public boolean equals(@CheckForNull final Object other) {
        return other instanceof Unit && compareTo((Unit) other) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("Unit[type=%s, id=%s, owner=%s, location=%s, direction=%d]",
                getType().name(), getId(), getOwner(), getLocation(), getDirection());
    }
}
