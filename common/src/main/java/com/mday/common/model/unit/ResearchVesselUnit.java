package com.mday.common.model.unit;

import static com.mday.common.model.ShipType.RESEARCH_VESSEL;

import com.mday.common.model.Location;
import com.mday.common.model.Ship;

import javax.annotation.Nonnull;

/**
 * Represents a research vessel unit.
 */
public class ResearchVesselUnit extends Ship {
    /**
     * Create a new instance of this unit.
     *
     * @param id the unique id of this unit
     * @param location the location of this unit
     * @param owner the id of the owner of this unit
     */
    public ResearchVesselUnit(@Nonnull final String id, @Nonnull final Location location, @Nonnull final String owner) {
        super(id, location, RESEARCH_VESSEL, owner);
    }
}
