package com.mday.model;

import javax.annotation.Nonnull;

/**
 * Defines the different classes of ships.
 */
public enum ShipClass {
    /**
     * Performs scouting and reconnaissance.
     */
    SHIPYARD(new ShipConfiguration.Builder()
            .withRadius(25)
            .withMovementSpeed(4.0)
            .withAcceleration(1 / 60.0)
            .withTraverseSpeed(1.0 * Math.PI / 180 / 10)
            .withHitPoints(1000)
            .withEngines(2)
            .withThrusters(2)
            .withSensors(0)
            .withDefenses(0)
            .withWeapons(0)
            .withEnergy(4)
            .build()),

    /**
     * Performs scouting and reconnaissance.
     */
    RECON(new ShipConfiguration.Builder()
            .withRadius(6)
            .withMovementSpeed(38.0)
            .withAcceleration(1 / 100.0)
            .withTraverseSpeed(18.0 * Math.PI / 180 / 10)
            .withHitPoints(100)
            .withEngines(1)
            .withThrusters(2)
            .withSensors(3)
            .withDefenses(0)
            .withWeapons(0)
            .withEnergy(2)
            .build()),

    /**
     * Fast and maneuverable for skirmishes.
     */
    FIGHTER(new ShipConfiguration.Builder()
            .withRadius(8)
            .withMovementSpeed(32.0)
            .withAcceleration(1 / 40.0)
            .withTraverseSpeed(24.0 * Math.PI / 180 / 10)
            .withHitPoints(400)
            .withEngines(4)
            .withThrusters(4)
            .withSensors(0)
            .withDefenses(1)
            .withWeapons(2)
            .withEnergy(1)
            .build()),

    /**
     * Mixed-armament warship.
     */
    FRIGATE(new ShipConfiguration.Builder()
            .withRadius(11)
            .withMovementSpeed(24.0)
            .withAcceleration(1 / 100.0)
            .withTraverseSpeed(14.0 * Math.PI / 180 / 10)
            .withHitPoints(600)
            .withEngines(2)
            .withThrusters(2)
            .withSensors(1)
            .withDefenses(2)
            .withWeapons(1)
            .withEnergy(4)
            .build()),

    /**
     * Heavy firepower warship.
     */
    DESTROYER(new ShipConfiguration.Builder()
            .withRadius(15)
            .withMovementSpeed(18.0)
            .withAcceleration(1 / 60.0)
            .withTraverseSpeed(10.0 * Math.PI / 180 / 10)
            .withHitPoints(800)
            .withEngines(4)
            .withThrusters(4)
            .withSensors(2)
            .withDefenses(2)
            .withWeapons(2)
            .withEnergy(6)
            .build()),

    /**
     * Heavily armored warship with heavy firepower.
     */
    DREADNOUGHT(new ShipConfiguration.Builder()
            .withRadius(20)
            .withMovementSpeed(6.0)
            .withAcceleration(1 / 160.0)
            .withTraverseSpeed(3.0 * Math.PI / 180 / 10)
            .withHitPoints(1500)
            .withEngines(4)
            .withThrusters(4)
            .withSensors(0)
            .withDefenses(4)
            .withWeapons(4)
            .withEnergy(4)
            .build()),

    /**
     * Provides transportation.
     */
    TRANSPORT(new ShipConfiguration.Builder()
            .withRadius(15)
            .withMovementSpeed(14.0)
            .withAcceleration(1 / 60.0)
            .withTraverseSpeed(9.0 * Math.PI / 180 / 10)
            .withHitPoints(200)
            .withEngines(4)
            .withThrusters(4)
            .withSensors(0)
            .withDefenses(0)
            .withWeapons(0)
            .withEnergy(4)
            .build()),

    /**
     * Researches technologies.
     */
    RESEARCH(new ShipConfiguration.Builder()
            .withRadius(12)
            .withMovementSpeed(9.0)
            .withAcceleration(1 / 120.0)
            .withTraverseSpeed(4.0 * Math.PI / 180 / 10)
            .withHitPoints(100)
            .withEngines(1)
            .withThrusters(2)
            .withSensors(0)
            .withDefenses(0)
            .withWeapons(0)
            .withEnergy(4)
            .build()),

    /**
     * Performs repairs on ships.
     */
    REPAIR(new ShipConfiguration.Builder()
            .withRadius(10)
            .withMovementSpeed(12.0)
            .withAcceleration(1 / 60.0)
            .withTraverseSpeed(10.0 * Math.PI / 180 / 10)
            .withHitPoints(100)
            .withEngines(2)
            .withThrusters(2)
            .withSensors(0)
            .withDefenses(0)
            .withWeapons(0)
            .withEnergy(2)
            .build()),

    /**
     * Collects energy from a star.
     */
    COLLECTOR(new ShipConfiguration.Builder()
            .withRadius(8)
            .withMovementSpeed(4.0)
            .withAcceleration(1 / 240.0)
            .withTraverseSpeed(3.0 * Math.PI / 180 / 10)
            .withHitPoints(50)
            .withEngines(1)
            .withThrusters(1)
            .withSensors(0)
            .withDefenses(0)
            .withWeapons(0)
            .withEnergy(0)
            .build()),

    /**
     * Generates a local shield.
     */
    SHIELD_GENERATOR(new ShipConfiguration.Builder()
            .withRadius(6)
            .withMovementSpeed(4.0)
            .withAcceleration(1 / 90.0)
            .withTraverseSpeed(4.0 * Math.PI / 180 / 10)
            .withHitPoints(50)
            .withEngines(1)
            .withThrusters(1)
            .withSensors(0)
            .withDefenses(0)
            .withWeapons(0)
            .withEnergy(1)
            .build()),

    ;

    @Nonnull
    private final ShipConfiguration shipConfiguration;

    /**
     * Create a ship class.
     *
     * @param shipConfiguration the configuration associated with the ship class
     */
    ShipClass(@Nonnull final ShipConfiguration shipConfiguration) {
        this.shipConfiguration = shipConfiguration;
    }

    /**
     * Retrieve the configuration associated with the ship class.
     *
     * @return the configuration associated with the ship class
     */
    @Nonnull
    public ShipConfiguration getShipConfiguration() {
        return shipConfiguration;
    }
}
