package com.mday.client.ui;

import com.mday.client.event.Event;
import com.mday.client.event.EventType;
import com.mday.client.game.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Canvas;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.swing.JFrame;

/**
 * Provides the game display.
 */
public class Display extends Canvas
        implements Consumer<Event>, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Display.class);
    private static final long serialVersionUID = 1L;

    @Nonnull
    private final EventQueue eventQueue;
    @Nonnull
    private final transient GraphicsDevice graphicsDevice;
    @Nonnull
    private final JFrame frame;

    /**
     * Create an instance of the game display.
     *
     * @param eventQueue the event queue to which key and mouse events will be sent
     */
    public Display(@Nonnull final EventQueue eventQueue) {
        this.eventQueue = eventQueue;
        graphicsDevice = getGraphicsDevice();
        frame = createFrame(graphicsDevice);

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    @Nonnull
    private GraphicsDevice getGraphicsDevice() {
        final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return graphicsEnvironment.getDefaultScreenDevice();
    }

    @Nonnull
    private JFrame createFrame(@Nonnull final GraphicsDevice graphicsDevice) {
        final JFrame frame = new JFrame(graphicsDevice.getDefaultConfiguration());
        frame.add(this);
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        return frame;
    }

    /**
     * Display the window.
     */
    public void showWindow() {
        frame.setVisible(true);
        graphicsDevice.setFullScreenWindow(frame);
    }

    /**
     * Hide the window.
     */
    public void hideWindow() {
        frame.setVisible(false);
        graphicsDevice.setFullScreenWindow(null);
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event.getType() == EventType.START) {
            showWindow();
        } else if (event.getType() == EventType.QUIT) {
            hideWindow();
        }
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
