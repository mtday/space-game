package com.mday.client.ui.render.unit;

import com.mday.client.ui.Surface;
import com.mday.common.model.Location;
import com.mday.common.model.Unit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

/**
 * Responsible for rendering the recon drone unit type.
 */
public class ReconDroneRenderer implements BiConsumer<Unit, Surface> {
    @Override
    public void accept(@Nonnull final Unit unit, @Nonnull final Surface surface) {
        final int zoom = surface.getZoom();
        final Location center = unit.getLocation().subtract(surface.getTopLeft());
        final Location topLeft = center.subtract(zoom / 2, zoom / 2);

        final Graphics2D graphics = surface.getDrawGraphics();

        graphics.setColor(new Color(33, 174, 75));
        graphics.drawOval(topLeft.getX(), topLeft.getY(), zoom, zoom);
    }
}
