package com.mday.client;

import com.mday.client.action.EscapeKeyAction;
import com.mday.client.action.MouseSelectionAction;
import com.mday.client.action.ZoomInAction;
import com.mday.client.action.ZoomOutAction;
import com.mday.client.event.type.UnitAddEvent;
import com.mday.client.game.EventQueue;
import com.mday.client.game.Runner;
import com.mday.client.game.Units;
import com.mday.client.io.ServerConnector;
import com.mday.client.ui.CoordinateSystem;
import com.mday.client.ui.Display;
import com.mday.client.ui.render.BackgroundRenderer;
import com.mday.client.ui.render.GridRenderer;
import com.mday.client.ui.render.MousePositionRenderer;
import com.mday.client.ui.render.MouseSelectionRenderer;
import com.mday.client.ui.render.ScaleRenderer;
import com.mday.client.ui.render.UnitRenderer;
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
        final Units units = new Units(coordinateSystem);

        final MousePositionRenderer mousePositionRenderer = new MousePositionRenderer();
        final MouseSelectionRenderer mouseSelectionRenderer = new MouseSelectionRenderer();

        final Display display = new Display(eventQueue, coordinateSystem);
        display.addSurfaceConsumer(coordinateSystem);
        display.addSurfaceConsumer(new BackgroundRenderer());
        display.addSurfaceConsumer(new UnitRenderer(units));
        display.addSurfaceConsumer(new ScaleRenderer());
        display.addSurfaceConsumer(new GridRenderer());
        display.addSurfaceConsumer(mousePositionRenderer);
        display.addSurfaceConsumer(mouseSelectionRenderer);

        runner = new Runner(eventQueue, display);
        runner.addEventConsumer(coordinateSystem);
        runner.addEventConsumer(serverConnector);
        runner.addEventConsumer(display);
        runner.addEventConsumer(units);
        runner.addEventConsumer(new EscapeKeyAction(eventQueue));
        runner.addEventConsumer(new ZoomInAction(eventQueue));
        runner.addEventConsumer(new ZoomOutAction(eventQueue));
        runner.addEventConsumer(mousePositionRenderer);
        runner.addEventConsumer(mouseSelectionRenderer);
        runner.addEventConsumer(new MouseSelectionAction(eventQueue));

        eventQueue.add(new UnitAddEvent(new ReconDroneUnit("recon", "me", new Location(-100, -50))));
        eventQueue.add(new UnitAddEvent(new ShipyardUnit("shipyard", "me", new Location(0, 0))));
        eventQueue.add(new UnitAddEvent(new ResearchVesselUnit("research", "me", new Location(100, 50))));
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
