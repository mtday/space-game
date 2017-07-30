package com.mday.client;

import com.mday.client.action.key.ArrowKeyAction;
import com.mday.client.action.key.EscapeKeyAction;
import com.mday.client.action.key.ZoomKeyAction;
import com.mday.client.action.mouse.MouseAction;
import com.mday.client.action.mouse.MouseZoomAction;
import com.mday.client.event.type.unit.UnitAddEvent;
import com.mday.client.game.CoordinateSystem;
import com.mday.client.game.EventQueue;
import com.mday.client.game.Runner;
import com.mday.client.game.UnitMover;
import com.mday.client.game.Units;
import com.mday.client.io.ServerConnector;
import com.mday.client.ui.Display;
import com.mday.client.ui.render.BackgroundRenderer;
import com.mday.client.ui.render.MouseSelectionRenderer;
import com.mday.client.ui.render.UnitRenderer;
import com.mday.client.ui.render.debug.GridRenderer;
import com.mday.client.ui.render.debug.MousePositionRenderer;
import com.mday.client.ui.render.debug.ScaleRenderer;
import com.mday.common.model.Location;
import com.mday.common.model.Ship;
import com.mday.common.model.ShipClass;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Starts the game client.
 */
public class Client {
    private final ServerConnector serverConnector;
    private final Runner runner;

    /**
     * Create an instance of the client.
     */
    public Client() {
        final EventQueue eventQueue = new EventQueue();
        serverConnector = new ServerConnector("localhost", 33445, eventQueue);

        final CoordinateSystem coordinateSystem = new CoordinateSystem();
        final UnitMover unitMover = new UnitMover();
        final Units units = new Units(coordinateSystem, unitMover);

        final MousePositionRenderer mousePositionRenderer = new MousePositionRenderer();
        final MouseSelectionRenderer mouseSelectionRenderer = new MouseSelectionRenderer();

        final Display display = new Display(eventQueue, coordinateSystem);
        display.addSurfaceConsumer(new BackgroundRenderer());
        display.addSurfaceConsumer(new UnitRenderer(units));
        display.addSurfaceConsumer(new ScaleRenderer());
        display.addSurfaceConsumer(new GridRenderer());
        display.addSurfaceConsumer(mousePositionRenderer);
        display.addSurfaceConsumer(mouseSelectionRenderer);

        runner = new Runner(eventQueue, display);
        runner.addClockTickObserver(coordinateSystem);
        runner.addClockTickObserver(unitMover);
        runner.addEventConsumer(coordinateSystem);
        runner.addEventConsumer(serverConnector);
        runner.addEventConsumer(display);
        runner.addEventConsumer(units);
        runner.addEventConsumer(new EscapeKeyAction(eventQueue));
        runner.addEventConsumer(new ZoomKeyAction(eventQueue));
        runner.addEventConsumer(new ArrowKeyAction(eventQueue));
        runner.addEventConsumer(mousePositionRenderer);
        runner.addEventConsumer(mouseSelectionRenderer);
        runner.addEventConsumer(new MouseAction(eventQueue, units));
        runner.addEventConsumer(new MouseZoomAction(eventQueue));

        int id = 0;
        final List<Ship> ships = Arrays.asList(
                new Ship(String.valueOf(++id), new Location(), ShipClass.SHIPYARD, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.RECON, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.FIGHTER, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.FRIGATE, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.DESTROYER, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.DREADNOUGHT, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.TRANSPORT, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.RESEARCH, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.REPAIR, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.COLLECTOR, "me"),
                new Ship(String.valueOf(++id), new Location(), ShipClass.SHIELD_GENERATOR, "me"));

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
    }

    /**
     * Run the client.
     */
    public void run() {
        runner.start();
        //serverConnector.run();
    }

    /**
     * The entry-point into the game client.
     *
     * @param args the command-line arguments
     */
    public static void main(@Nullable final String... args) {
        new Client().run();
    }
}
