package com.mday.common.model;

import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * The base class for units.
 */
public class Unit {
    /**
     * Defines the type of this unit.
     */
    @Nonnull
    public final UnitType type;

    /**
     * The coordinate indicating the location of this unit.
     */
    @Nonnull
    public final Coord coord;

    /**
     * Default constructor.
     */
    public Unit() {
        this(UnitType.SHIPYARD, new Coord());
    }

    /**
     * Parameter constructor.
     *
     * @param type the type of this unit
     * @param coord the coordinate indicating the location of this unit
     */
    public Unit(@Nonnull final UnitType type, @Nonnull final Coord coord) {
        this.type = type;
        this.coord = coord;
    }

    @Override
    public boolean equals(@CheckForNull final Object other) {
        if (!(other instanceof Unit)) {
            return false;
        }

        final Unit unit = (Unit) other;
        return Objects.equals(type, unit.type) && Objects.equals(coord, unit.coord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type.name(), coord);
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("Unit[type=%s, coord=%s]", type.name(), coord);
    }
}
