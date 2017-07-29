package com.mday.client;

import com.mday.client.action.key.ArrowKeyAction;
import com.mday.client.action.key.EscapeKeyAction;
import com.mday.client.action.mouse.MouseSelectionAction;
import com.mday.client.action.key.ZoomKeyAction;
import com.mday.client.event.type.unit.UnitAddEvent;
import com.mday.client.game.EventQueue;
import com.mday.client.game.Runner;
import com.mday.client.game.UnitMover;
import com.mday.client.game.Units;
import com.mday.client.io.ServerConnector;
import com.mday.client.ui.CoordinateSystem;
import com.mday.client.ui.Display;
import com.mday.client.ui.render.*;
import com.mday.client.ui.render.debug.GridRenderer;
import com.mday.client.ui.render.debug.MousePositionRenderer;
import com.mday.client.ui.render.debug.ScaleRenderer;
import com.mday.common.model.Location;
import com.mday.common.model.unit.ReconDroneUnit;
import com.mday.common.model.unit.ResearchVesselUnit;
import com.mday.common.model.unit.ShipyardUnit;

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
        runner.addEventConsumer(new MouseSelectionAction(eventQueue, units));

        eventQueue.add(new UnitAddEvent(new ReconDroneUnit("recon", new Location(-100, -50), "me")));
        eventQueue.add(new UnitAddEvent(new ShipyardUnit("shipyard", new Location(0, 0), "me")));
        eventQueue.add(new UnitAddEvent(new ResearchVesselUnit("research", new Location(100, 50), "me")));
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
