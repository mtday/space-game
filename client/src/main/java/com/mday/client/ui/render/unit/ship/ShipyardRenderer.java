package com.mday.client.ui.render.unit.ship;

import com.mday.client.ui.render.unit.AbstractShipRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Responsible for rendering the shipyard.
 */
public class ShipyardRenderer extends AbstractShipRenderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShipyardRenderer.class);

    /**
     * Create an instance of this renderer.
     *
     * @throws IOException if there is a problem loading the ship image
     */
    public ShipyardRenderer() throws IOException {
        super("ship01.png");
    }
}
