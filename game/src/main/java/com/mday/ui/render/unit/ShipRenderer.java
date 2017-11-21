package com.mday.ui.render.unit;

import static com.mday.model.ShipClass.COLLECTOR;
import static com.mday.model.ShipClass.DESTROYER;
import static com.mday.model.ShipClass.DREADNOUGHT;
import static com.mday.model.ShipClass.FIGHTER;
import static com.mday.model.ShipClass.FRIGATE;
import static com.mday.model.ShipClass.RECON;
import static com.mday.model.ShipClass.REPAIR;
import static com.mday.model.ShipClass.RESEARCH;
import static com.mday.model.ShipClass.SHIELD_GENERATOR;
import static com.mday.model.ShipClass.SHIPYARD;
import static com.mday.model.ShipClass.TRANSPORT;
import static java.util.Optional.ofNullable;

import com.mday.ui.Surface;
import com.mday.ui.render.unit.ship.CollectorRenderer;
import com.mday.ui.render.unit.ship.DestroyerRenderer;
import com.mday.ui.render.unit.ship.DreadnoughtRenderer;
import com.mday.ui.render.unit.ship.FighterRenderer;
import com.mday.ui.render.unit.ship.FrigateRenderer;
import com.mday.ui.render.unit.ship.ReconRenderer;
import com.mday.ui.render.unit.ship.RepairRenderer;
import com.mday.ui.render.unit.ship.ResearchRenderer;
import com.mday.ui.render.unit.ship.ShieldGeneratorRenderer;
import com.mday.ui.render.unit.ship.ShipyardRenderer;
import com.mday.ui.render.unit.ship.TransportRenderer;
import com.mday.model.Ship;
import com.mday.model.ShipClass;
import com.mday.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
     *
     * @throws IOException if any of the renderers are unable to load the ship images
     */
    public ShipRenderer() throws IOException {
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
