package com.mday.common.model;

/**
 * Defines the possible types of ships.
 */
public enum ShipType {
    /**
     * A ship yard is where additional ships can be created.
     */
    SHIPYARD(25.0, 2.0),

    /**
     * A recon drone is used to perform scouting reconnaissance.
     */
    RECON_DRONE(8.0, 40.0),

    /**
     * A research vessel is responsible for technology research.
     */
    RESEARCH_VESSEL(13.0, 5.0);

    private final double radius;
    private final double speed;

    /**
     * Create a ship type.
     *
     * @param radius the radius indicating the size of the ship
     * @param speed  the base movement speed for this ship type
     */
    ShipType(final double radius, final double speed) {
        this.radius = radius;
        this.speed = speed;
    }

    /**
     * Retrieve the radius indicating the size of the ship.
     *
     * @return the radius indicating the size of the ship
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Retrieve the base movement speed for this ship type.
     *
     * @return the base movement speed for this ship type
     */
    public double getSpeed() {
        return speed;
    }
}
