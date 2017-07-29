package com.mday.common.model;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * The base class for units.
 */
public class Unit implements Comparable<Unit> {
    @Nonnull
    private final UnitType unitType;
    @Nonnull
    private final String id;
    @Nonnull
    private Location location;
    private double radius = 10.0;
    private boolean selected = false;
    private boolean moveable = false;
    private double speed = 10.0;

    /**
     * Create a new unit instance.
     *
     * @param unitType the type of this unit
     * @param id the unique id of this unit
     * @param location the location of this unit
     */
    public Unit(@Nonnull final UnitType unitType, @Nonnull final String id, @Nonnull final Location location) {
        this.unitType = unitType;
        this.id = id;
        this.location = location;
    }

    /**
     * Retrieve the type of this unit.
     *
     * @return the type of this unit
     */
    @Nonnull
    public UnitType getUnitType() {
        return unitType;
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
     * Retrieve the radius indicating the size of this unit.
     *
     * @return the radius indicating the size of this unit
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set the new radius value indicating the size of this unit.
     *
     * @param radius the new radius value indicating the size of this unit
     */
    public void setRadius(final double radius) {
        this.radius = radius;
    }

    /**
     * Whether this unit is currently selected.
     *
     * @return this unit is currently selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set whether this unit is currently selected.
     *
     * @param selected the new value indicating whether this unit is currently selected
     */
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }

    /**
     * Retrieve whether this unit can be moved.
     *
     * @return whether this unit can be moved
     */
    public boolean isMoveable() {
        return moveable;
    }

    /**
     * Set whether this unit can be moved.
     *
     * @param moveable the new value indicating whether this unit can be moved
     */
    public void setMoveable(final boolean moveable) {
        this.moveable = moveable;
    }

    /**
     * Retrieve the movement speed for this unit.
     *
     * @return the movement speed for this unit
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Set the movement speed for this unit.
     *
     * @param speed the new value indicating the movement speed for this unit
     */
    public void setSpeed(final double speed) {
        this.speed = speed;
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
        return String.format("Unit[unitType=%s, id=%s, location=%s]", getUnitType().name(), getId(), getLocation());
    }
}
