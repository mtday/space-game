package com.mday.client.ui.render.unit.ship;

import com.mday.client.ui.render.unit.AbstractShipRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Responsible for rendering the recon drone.
 */
public class ReconRenderer extends AbstractShipRenderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReconRenderer.class);

    /**
     * Create an instance of this renderer.
     *
     * @throws IOException if there is a problem loading the ship image
     */
    public ReconRenderer() throws IOException {
        super("ship06.png");
    }
}
