package com.mday.client.ui.render.unit;

import static com.mday.common.model.ShipClass.COLLECTOR;
import static com.mday.common.model.ShipClass.DESTROYER;
import static com.mday.common.model.ShipClass.DREADNOUGHT;
import static com.mday.common.model.ShipClass.FIGHTER;
import static com.mday.common.model.ShipClass.FRIGATE;
import static com.mday.common.model.ShipClass.RECON;
import static com.mday.common.model.ShipClass.REPAIR;
import static com.mday.common.model.ShipClass.RESEARCH;
import static com.mday.common.model.ShipClass.SHIELD_GENERATOR;
import static com.mday.common.model.ShipClass.SHIPYARD;
import static com.mday.common.model.ShipClass.TRANSPORT;
import static java.util.Optional.ofNullable;

import com.mday.client.ui.Surface;
import com.mday.client.ui.render.unit.ship.CollectorRenderer;
import com.mday.client.ui.render.unit.ship.DestroyerRenderer;
import com.mday.client.ui.render.unit.ship.DreadnoughtRenderer;
import com.mday.client.ui.render.unit.ship.FighterRenderer;
import com.mday.client.ui.render.unit.ship.FrigateRenderer;
import com.mday.client.ui.render.unit.ship.ReconRenderer;
import com.mday.client.ui.render.unit.ship.RepairRenderer;
import com.mday.client.ui.render.unit.ship.ResearchRenderer;
import com.mday.client.ui.render.unit.ship.ShieldGeneratorRenderer;
import com.mday.client.ui.render.unit.ship.ShipyardRenderer;
import com.mday.client.ui.render.unit.ship.TransportRenderer;
import com.mday.common.model.Ship;
import com.mday.common.model.ShipClass;
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
    private final ConcurrentHashMap<ShipClass, BiConsumer<Ship, Surface>> shipRenderers = new ConcurrentHashMap<>();

    /**
     * Create an instance of this class.
     */
    public ShipRenderer() {
        shipRenderers.put(SHIPYARD, new ShipyardRenderer());
        shipRenderers.put(RECON, new ReconRenderer());
        shipRenderers.put(FIGHTER, new FighterRenderer());
        shipRenderers.put(FRIGATE, new FrigateRenderer());
        shipRenderers.put(DESTROYER, new DestroyerRenderer());
        shipRenderers.put(DREADNOUGHT, new DreadnoughtRenderer());
        shipRenderers.put(TRANSPORT, new TransportRenderer());
        shipRenderers.put(RESEARCH, new ResearchRenderer());
        shipRenderers.put(REPAIR, new RepairRenderer());
        shipRenderers.put(COLLECTOR, new CollectorRenderer());
        shipRenderers.put(SHIELD_GENERATOR, new ShieldGeneratorRenderer());
    }

    @Override
    public void accept(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        super.accept(unit, surface);

        if (unit instanceof Ship) {
            final Ship ship = (Ship) unit;
            ofNullable(shipRenderers.get(ship.getShipClass())).ifPresent(consumer -> consumer.accept(ship, surface));
        }
    }
}
