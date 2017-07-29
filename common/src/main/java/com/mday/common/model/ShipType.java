package com.mday.common.model;

/**
 * Defines the possible types of ships.
 */
public enum ShipType {
    /**
     * A ship yard is where additional ships can be created.
     */
    SHIPYARD(22.0),

    /**
     * A recon drone is used to perform scouting reconnaissance.
     */
    RECON_DRONE(8.0),

    /**
     * A research vessel is responsible for technology research.
     */
    RESEARCH_VESSEL(13.0);

    private final double size;

    /**
     * Create a unit type.
     *
     * @param size the size of the unit
     */
    ShipType(final double size) {
        this.size = size;
    }

    /**
     * Retrieve the size of the unit.
     *
     * @return the size of the unit
     */
    public double getSize() {
        return size;
    }
}
