package com.mday.common.model;

import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * The base class for units.
 */
public class Unit {
    @Nonnull
    private final String id;
    @Nonnull
    private final String owner;
    @Nonnull
    private final UnitType type;
    @Nonnull
    private Coord coord;
    private int direction = 0;

    /**
     * Create a new unit instance.
     *
     * @param id the unique id of this unit
     * @param owner the id of the owner of this unit
     * @param type the type of this unit
     * @param coord the location of this unit
     */
    public Unit(@Nonnull final String id, @Nonnull final String owner, @Nonnull final UnitType type,
            @Nonnull final Coord coord) {
        this.id = id;
        this.owner = owner;
        this.type = type;
        this.coord = coord;
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
     * Retrieve the type of this unit.
     *
     * @return the type of this unit
     */
    @Nonnull
    public UnitType getType() {
        return type;
    }

    /**
     * Retrieve the location of this unit.
     *
     * @return the location of this unit
     */
    @Nonnull
    public Coord getCoord() {
        return coord;
    }

    /**
     * Set the location of this unit.
     *
     * @param coord the new location of this unit
     */
    public void setCoord(@Nonnull final Coord coord) {
        this.coord = coord;
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
    public boolean equals(@CheckForNull final Object other) {
        if (!(other instanceof Unit)) {
            return false;
        }

        final Unit unit = (Unit) other;
        // @formatter:off
        return Objects.equals(getId(), unit.getId())
                && Objects.equals(getOwner(), unit.getOwner())
                && Objects.equals(getType(), unit.getType())
                && Objects.equals(getCoord(), unit.getCoord())
                && Objects.equals(getDirection(), unit.getDirection());
        // @formatter:on
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOwner(), getType().name(), getCoord(), getDirection());
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("Unit[id=%s, owner=%s, type=%s, coord=%s, direction=%d]",
                getId(), getOwner(), getType().name(), getCoord(), getDirection());
    }
}
