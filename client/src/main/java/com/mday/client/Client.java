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
import com.mday.client.ui.render.debug.SelectedShipRenderer;
import com.mday.common.model.Location;
import com.mday.common.model.Ship;
import com.mday.common.model.ShipClass;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * The game client.
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
        final ServerConnector serverConnector = new ServerConnector("localhost", 33445, eventQueue);

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

        final List<Ship> ships = new LinkedList<>();
        int r = 0; int c = 0;
        for (final ShipClass shipClass : ShipClass.values()) {
            Location place = new Location(-210 + c++ /* Get it? Ha. */ * 100, -140 + r * 90);
            final Ship ship = new Ship(shipClass.name(), place, shipClass, "owner");
            eventQueue.add(new UnitAddEvent(ship));
            
            if(c > 4){
                c = 0;
                r++;
            }
        }

        runner.start();
        //serverConnector.run();
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
