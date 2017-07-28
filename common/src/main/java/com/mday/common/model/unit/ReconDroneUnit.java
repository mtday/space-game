package com.mday.common.model.unit;

import static com.mday.common.model.UnitType.RECON_DRONE;

import com.mday.common.model.Location;
import com.mday.common.model.Unit;

import javax.annotation.Nonnull;

/**
 * Represents a recon drone unit.
 */
public class ReconDroneUnit extends Unit {
    /**
     * Create a new instance of this unit.
     *
     * @param id the unique id of this unit
     * @param owner the id of the owner of this unit
     * @param location the location of this unit
     */
    public ReconDroneUnit(@Nonnull final String id, @Nonnull final String owner, @Nonnull final Location location) {
        super(RECON_DRONE, id, owner, location);
    }
}
