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
    private boolean selected = false;

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
        return String.format("Unit[unitType=%s, id=%s, location=%s, selected=%b]",
                getUnitType().name(), getId(), getLocation(), isSelected());
    }
}
