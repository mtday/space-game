package com.mday.client.ui;

import com.mday.client.event.Event;
import com.mday.client.event.EventType;
import com.mday.client.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Provides the game display.
 */
public class Display implements Consumer<Event>, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(Display.class);

    private static final boolean FULL_SCREEN = false;

    @Nonnull
    private final EventQueue eventQueue;
    @Nonnull
    private final transient GraphicsDevice graphicsDevice;

    private JFrame frame;
    private Surface surface;
    private final List<Consumer<Surface>> surfaceConsumers = new LinkedList<>();

    /**
     * Create an instance of the game display.
     *
     * @param eventQueue the event queue to which key and mouse events will be sent
     */
    public Display(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
        this.graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }

    /**
     * Add the specified surface consumer to the display.
     *
     * @param surfaceConsumer the surface consumer that will support drawing the game
     */
    public void addSurfaceConsumer(@Nonnull final Consumer<Surface> surfaceConsumer) {
        surfaceConsumers.add(surfaceConsumer);
    }

    @Nonnull
    private Surface createSurface() {
        final GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
        final int width = (int) graphicsConfiguration.getBounds().getWidth();
        final int height = (int) graphicsConfiguration.getBounds().getHeight();
        final Surface surface = new Surface(width, height);
        surface.addKeyListener(this);
        surface.addMouseListener(this);
        surface.addMouseMotionListener(this);
        surface.addMouseWheelListener(this);
        return surface;
    }

    @Nonnull
    private JFrame createFrame(@Nonnull final Surface surface) {
        final JFrame frame = new JFrame(graphicsDevice.getDefaultConfiguration());
        frame.setIgnoreRepaint(true);
        frame.add(surface);
        if (FULL_SCREEN) {
            frame.setUndecorated(true);
        } else {
            frame.setSize((int) (540 * 1.6), 540);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.requestFocus();
        }

        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
        frame.addMouseWheelListener(this);
        return frame;
    }

    private void showWindow() {
        surface = createSurface();
        frame = createFrame(surface);
        frame.setVisible(true);
        frame.setIgnoreRepaint(true);

        if (FULL_SCREEN) {
            graphicsDevice.setFullScreenWindow(frame);
        }
        surface.createBufferStrategy(3);
    }

    private void hideWindow() {
        frame.setVisible(false);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        frame.dispose();
        if (FULL_SCREEN) {
            graphicsDevice.setFullScreenWindow(null);
        }
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event.getType() == EventType.START) {
            showWindow();
        } else if (event.getType() == EventType.QUIT) {
            hideWindow();
        }
    }

    /**
     * Draw the game on the surface then render it.
     */
    public void render() {
        surfaceConsumers.forEach(consumer -> consumer.accept(surface));

        final BufferStrategy bufferStrategy = surface.getBufferStrategy();
        final Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(surface.getBufferedImage(), 0, 0, surface.getWidth(), surface.getHeight(), null);
        graphics.dispose();
        bufferStrategy.show();
    }

    @Override
    public void keyTyped(@Nonnull final KeyEvent keyEvent) {
        eventQueue.add(new com.mday.client.event.type.KeyEvent(keyEvent));
    }

    @Override
    public void keyPressed(@Nonnull final KeyEvent keyEvent) {
        eventQueue.add(new com.mday.client.event.type.KeyEvent(keyEvent));
    }

    @Override
    public void keyReleased(@Nonnull final KeyEvent keyEvent) {
        eventQueue.add(new com.mday.client.event.type.KeyEvent(keyEvent));
    }

    @Override
    public void mouseClicked(@Nonnull final MouseEvent mouseEvent) {
        eventQueue.add(new com.mday.client.event.type.MouseEvent(mouseEvent));
    }

    @Override
    public void mousePressed(@Nonnull final MouseEvent mouseEvent) {
        eventQueue.add(new com.mday.client.event.type.MouseEvent(mouseEvent));
    }

    @Override
    public void mouseReleased(@Nonnull final MouseEvent mouseEvent) {
        eventQueue.add(new com.mday.client.event.type.MouseEvent(mouseEvent));
    }

    @Override
    public void mouseEntered(@Nonnull final MouseEvent mouseEvent) {
        eventQueue.add(new com.mday.client.event.type.MouseEvent(mouseEvent));
    }

    @Override
    public void mouseExited(@Nonnull final MouseEvent mouseEvent) {
        eventQueue.add(new com.mday.client.event.type.MouseEvent(mouseEvent));
    }

    @Override
    public void mouseDragged(@Nonnull final MouseEvent mouseEvent) {
        eventQueue.add(new com.mday.client.event.type.MouseEvent(mouseEvent));
    }

    @Override
    public void mouseMoved(@Nonnull final MouseEvent mouseEvent) {
        eventQueue.add(new com.mday.client.event.type.MouseEvent(mouseEvent));
    }

    @Override
    public void mouseWheelMoved(@Nonnull final MouseWheelEvent mouseWheelEvent) {
        eventQueue.add(new com.mday.client.event.type.MouseWheelEvent(mouseWheelEvent));
    }
}
