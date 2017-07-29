package com.mday.client.ui.render.unit;

import static com.mday.common.model.ShipType.RECON_DRONE;
import static com.mday.common.model.ShipType.RESEARCH_VESSEL;
import static com.mday.common.model.ShipType.SHIPYARD;
import static java.util.Optional.ofNullable;

import com.mday.client.ui.Surface;
import com.mday.client.ui.render.unit.ship.ReconDroneRenderer;
import com.mday.client.ui.render.unit.ship.ResearchVesselRenderer;
import com.mday.client.ui.render.unit.ship.ShipyardRenderer;
import com.mday.common.model.Ship;
import com.mday.common.model.ShipType;
import com.mday.common.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing ships.
 */
public class ShipRenderer extends AbstractUnitRenderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShipRenderer.class);

    @Nonnull
    private final ConcurrentHashMap<ShipType, BiConsumer<Ship, Surface>> shipRenderers = new ConcurrentHashMap<>();

    /**
     * Create an instance of this class.
     */
    public ShipRenderer() {
        shipRenderers.put(SHIPYARD, new ShipyardRenderer());
        shipRenderers.put(RECON_DRONE, new ReconDroneRenderer());
        shipRenderers.put(RESEARCH_VESSEL, new ResearchVesselRenderer());
    }

    @Override
    public void accept(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        super.accept(unit, surface);

        if (unit instanceof Ship) {
            final Ship ship = (Ship) unit;
            ofNullable(shipRenderers.get(ship.getShipType()))
                    .ifPresent(consumer -> consumer.accept(ship, surface));
        }
    }
}
