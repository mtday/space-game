package com.mday.common.model;

import static com.mday.common.model.UnitType.SHIP;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.annotation.Nonnull;

/**
 * The base class for ships.
 */
@SuppressFBWarnings("EQ_DOESNT_OVERRIDE_EQUALS")
public class Ship extends Unit {
    @Nonnull
    private final ShipClass shipClass;
    @Nonnull
    private final String owner;

    /**
     * Create a new ship instance.
     *
     * @param id the unique id of this ship
     * @param location the location of this ship
     * @param shipClass the type of this ship
     * @param owner the id of the owner of this ship
     */
    public Ship(@Nonnull final String id, @Nonnull final Location location, @Nonnull final ShipClass shipClass,
            @Nonnull final String owner) {
        super(SHIP, id, location);
        this.shipClass = shipClass;
        this.owner = owner;
        setRadius(shipClass.getShipConfiguration().getRadius());
        setMovable(true);
    }

    /**
     * Retrieve the class of this ship.
     *
     * @return the class of this ship
     */
    @Nonnull
    public ShipClass getShipClass() {
        return shipClass;
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

    @Override
    public double getMovementSpeed() {
        // TODO: Use engines in this algorithm.
        final int maxWeight = 140;
        final double multiplier = 1.5;
        final double weight = getShipClass().getShipConfiguration().getWeight();
        final int engines = getShipClass().getShipConfiguration().getEngines();
        return (maxWeight - weight) / engines * multiplier;
    }

    @Override
    public double getTraverseSpeed() {
        // TODO: Use thrusters in this algorithm.
        final int maxWeight = 140;
        final double multiplier = 0.05;
        final double weight = getShipClass().getShipConfiguration().getWeight();
        final int thrusters = getShipClass().getShipConfiguration().getThrusters();
        return (maxWeight - weight) / thrusters * multiplier * Math.PI / 180;
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format("Ship[unit=%s, shipClass=%s, owner=%s]",
                super.toString(), getShipClass().name(), getOwner());
    }
}
