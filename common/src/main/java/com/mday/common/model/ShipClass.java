package com.mday.common.model;

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
            .withWeight(120)
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
            .withWeight(10)
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
            .withWeight(25)
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
            .withWeight(35)
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
            .withWeight(40)
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
            .withWeight(100)
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
            .withWeight(40)
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
            .withWeight(40)
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
            .withWeight(20)
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
            .withWeight(5)
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
            .withWeight(5)
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
