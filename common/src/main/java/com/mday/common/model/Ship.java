package com.mday.common.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.annotation.Nonnull;

import static com.mday.common.model.UnitType.SHIP;

/**
 * The base class for ships.
 */
@SuppressFBWarnings("EQ_DOESNT_OVERRIDE_EQUALS")
public class Ship extends Unit {
    @Nonnull
    private final ShipType shipType;
    @Nonnull
    private final String owner;
    private int direction = 0;

    /**
     * Create a new ship instance.
     *
     * @param id the unique id of this ship
     * @param location the location of this ship
     * @param shipType the type of this ship
     * @param owner the id of the owner of this ship
     */
    public Ship(@Nonnull final String id, @Nonnull final Location location, @Nonnull final ShipType shipType,
            @Nonnull final String owner) {
        super(SHIP, id, location);
        this.shipType = shipType;
        this.owner = owner;
        setRadius(shipType.getRadius());
        setSpeed(shipType.getSpeed());
        setMoveable(true);
    }

    /**
     * Retrieve the type of this ship.
     *
     * @return the type of this ship
     */
    @Nonnull
    public ShipType getShipType() {
        return shipType;
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
    @Nonnull
    public String toString() {
        return String.format("Ship[unit=%s, shipType=%s, owner=%s]",
                super.toString(), getShipType().name(), getOwner());
    }
}
