package com.mday;

import com.mday.action.key.ArrowKeyAction;
import com.mday.action.key.EscapeKeyAction;
import com.mday.action.key.ZoomKeyAction;
import com.mday.action.mouse.MouseAction;
import com.mday.action.mouse.MouseZoomAction;
import com.mday.event.type.unit.UnitAddEvent;
import com.mday.game.CoordinateSystem;
import com.mday.game.EventQueue;
import com.mday.game.Runner;
import com.mday.game.UnitMover;
import com.mday.game.Units;
import com.mday.ui.Display;
import com.mday.ui.render.BackgroundRenderer;
import com.mday.ui.render.MouseSelectionRenderer;
import com.mday.ui.render.UnitRenderer;
import com.mday.ui.render.debug.GridRenderer;
import com.mday.ui.render.debug.MousePositionRenderer;
import com.mday.ui.render.debug.ScaleRenderer;
import com.mday.ui.render.debug.SelectedShipRenderer;
import com.mday.model.Location;
import com.mday.model.Ship;
import com.mday.model.ShipClass;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Starts the game client.
 */
public class Client {
    private static final boolean FULL_SCREEN = true;

    /**
     * Run the client.
     *
     * @throws IOException if there is a problem loading game resources.
     */
    public static void run() throws IOException {
        final EventQueue eventQueue = new EventQueue();

        final CoordinateSystem coordinateSystem = new CoordinateSystem();
        final UnitMover unitMover = new UnitMover();
        final Units units = new Units(coordinateSystem, unitMover);

        final MousePositionRenderer mousePositionRenderer = new MousePositionRenderer();
        final MouseSelectionRenderer mouseSelectionRenderer = new MouseSelectionRenderer();

        final Display display = new Display(FULL_SCREEN, eventQueue, coordinateSystem);
        display.addSurfaceConsumer(new BackgroundRenderer());
        display.addSurfaceConsumer(new UnitRenderer(units));
        display.addSurfaceConsumer(new ScaleRenderer());
        display.addSurfaceConsumer(new GridRenderer());
        display.addSurfaceConsumer(new SelectedShipRenderer(units));
        display.addSurfaceConsumer(mousePositionRenderer);
        display.addSurfaceConsumer(mouseSelectionRenderer);

        final Runner runner = new Runner(eventQueue, display);
        runner.addClockTickObserver(coordinateSystem);
        runner.addClockTickObserver(unitMover);
        runner.addEventConsumer(coordinateSystem);
        runner.addEventConsumer(display);
        runner.addEventConsumer(units);
        runner.addEventConsumer(new EscapeKeyAction(eventQueue));
        runner.addEventConsumer(new ZoomKeyAction(eventQueue));
        runner.addEventConsumer(new ArrowKeyAction(eventQueue));
        runner.addEventConsumer(mousePositionRenderer);
        runner.addEventConsumer(mouseSelectionRenderer);
        runner.addEventConsumer(new MouseAction(eventQueue, units));
        runner.addEventConsumer(new MouseZoomAction(eventQueue));

        final List<Ship> ships = new LinkedList<>();
        for (final ShipClass shipClass : ShipClass.values()) {
            ships.add(new Ship(shipClass.name(), new Location(), shipClass, "owner"));
        }

        final Iterator<Ship> shipIterator = ships.iterator();
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (shipIterator.hasNext()) {
                    final Ship ship = shipIterator.next();
                    ship.setLocation(new Location(-210 + c * 100, -140 + r * 90));
                    eventQueue.add(new UnitAddEvent(ship));
                }
            }
        }

        runner.start();
    }

    /**
     * The entry-point into the game client.
     *
     * @param args the command-line arguments
     * @throws IOException if there is a problem loading game resources
     */
    public static void main(@Nullable final String... args) throws IOException {
        Client.run();
    }
}
