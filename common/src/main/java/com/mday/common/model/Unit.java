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
    private final UnitType unitType;
    @Nonnull
    private final String id;
    @Nonnull
    private Location location;
    private double radius = 10.0;
    private boolean selected = false;
    private boolean movable = false;
    private double movementSpeed = 10.0;
    private double traverseSpeed = 10.0 * Math.PI / 180;
    private double direction = 0;

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
    public boolean isMovable() {
        return movable;
    }

    /**
     * Set whether this unit can be moved.
     *
     * @param movable the new value indicating whether this unit can be moved
     */
    public void setMovable(final boolean movable) {
        this.movable = movable;
    }

    /**
     * Retrieve the movement speed for this unit.
     *
     * @return the movement speed for this unit
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Set the movement speed for this unit.
     *
     * @param movementSpeed the new value indicating the movement speed for this unit
     */
    public void setMovementSpeed(final double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * Retrieve the traverse speed for this unit.
     *
     * @return the traverse speed for this unit
     */
    public double getTraverseSpeed() {
        return traverseSpeed;
    }

    /**
     * Set the traverse speed for this unit.
     *
     * @param traverseSpeed the new value indicating the traverse speed for this unit
     */
    public void setTraverseSpeed(final double traverseSpeed) {
        this.traverseSpeed = traverseSpeed;
    }

    /**
     * Retrieve the angular direction of this unit in radians.
     *
     * @return the angular direction of this unit in radians
     */
    public double getDirection() {
        return direction;
    }

    /**
     * Set the angular direction of this unit in radians.
     *
     * @param direction the new angular direction of this unit in radians
     */
    public void setDirection(final double direction) {
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
        return String.format("Unit[unitType=%s, id=%s, location=%s]", getUnitType().name(), getId(), getLocation());
    }
}
