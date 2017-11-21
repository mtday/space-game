package com.mday.ui.render.unit.ship;

import com.mday.ui.render.unit.AbstractShipRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Responsible for rendering the recon drone.
 */
public class RepairRenderer extends AbstractShipRenderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepairRenderer.class);

    /**
     * Create an instance of this renderer.
     *
     * @throws IOException if there is a problem loading the ship image
     */
    public RepairRenderer() throws IOException {
        super("ship07.png");
    }
}
