package com.mday.server.controller;

import com.mday.common.model.Coord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.Nonnull;

/**
 * Used to test web sockets.
 */
@Controller
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    /**
     * Perform a move.
     *
     * @param coord the coordinate received
     * @return the updated coordinate
     */
    @MessageMapping("/move")
    @SendTo("/topic")
    @Nonnull
    public Coord move(@Nonnull final Coord coord) {
        LOGGER.info("Received move: {}", coord);
        return coord;
    }
}
