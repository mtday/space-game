package com.mday.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.annotation.Nonnull;

/**
 * Provides the entry-point into this Spring Boot application.
 */
@SpringBootApplication
public class Application {
    /**
     * The entry-point into this Spring Boot application.
     *
     * @param args the command-line arguments
     */
    public static void main(@Nonnull final String... args) {
        new SpringApplicationBuilder().profiles("production").sources(Application.class).run(args);
    }

}
