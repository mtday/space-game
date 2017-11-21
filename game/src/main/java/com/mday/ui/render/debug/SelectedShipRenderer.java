package com.mday.ui.render.debug;

import static com.mday.model.UnitType.SHIP;
import static java.util.stream.Collectors.toSet;

import com.mday.game.Units;
import com.mday.ui.Surface;
import com.mday.ui.SurfaceConsumer;
import com.mday.model.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing information about the currently selected ships.
 */
public class SelectedShipRenderer implements SurfaceConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectedShipRenderer.class);

    @Nonnull
    private final Units units;

    /**
     * Create an instance of this renderer.
     *
     * @param units manages the units available
     */
    public SelectedShipRenderer(@Nonnull final Units units) {
        this.units = units;
    }

    @Override
    public void accept(@Nonnull final Surface surface) {
        final Graphics2D graphics = surface.getDrawGraphics();
        graphics.setColor(new Color(200, 200, 200));

        final Set<String> selectedShipClasses = new TreeSet<>(
                units.getSelected().stream().filter(unit -> unit.getUnitType() == SHIP)
                        .map(unit -> (Ship) unit).map(ship -> ship.getShipClass().name()).collect(toSet()));

        final FontMetrics fontMetrics = graphics.getFontMetrics();
        //final int textHeight = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        final int textWidth = selectedShipClasses.stream().mapToInt(fontMetrics::stringWidth).max().orElse(0);

        int displayed = 0;
        for (final String shipClass : selectedShipClasses) {
            final int x = surface.getWidth() - textWidth - 15;
            final int y = 15 + (displayed * (fontMetrics.getHeight() + 5));
            graphics.drawString(shipClass, x, y);
            displayed++;
        }
    }
}
