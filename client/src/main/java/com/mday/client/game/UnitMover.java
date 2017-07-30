package com.mday.client.game;

import com.mday.client.event.ClockTickObserver;
import com.mday.common.model.Location;
import com.mday.common.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

/**
 * This class is responsible for moving units.
 */
public class UnitMover implements ClockTickObserver {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnitMover.class);

    @Nonnull
    private final ConcurrentHashMap<Unit, Movement> moving = new ConcurrentHashMap<>();

    private static final double MOVEMENT_FACTOR = 1.0 / 30;

    @Override
    public void tick() {
        moving.forEach(this::move);
    }

    private void updateUnitDirection(@Nonnull final Unit unit, @Nonnull final Movement movement) {
        final Location direction = movement.destination.subtract(unit.getLocation()).getNormalized();
        final double radians = (Math.PI / 2) + Math.atan2(direction.getY(), direction.getX());
        double deltaRadians = unit.getDirection() - radians;
        if (Math.abs(deltaRadians) > Math.PI && deltaRadians < 0) {
            deltaRadians += 2 * Math.PI;
        } else if (Math.abs(deltaRadians) > Math.PI && deltaRadians > 0) {
            deltaRadians -= 2 * Math.PI;
        }

        if (Math.abs(deltaRadians) > 20 * Math.PI / 180) {
            if (deltaRadians < 0) {
                unit.setDirection(unit.getDirection() + unit.getTraverseSpeed());
            } else {
                unit.setDirection(unit.getDirection() - unit.getTraverseSpeed());
            }
        } else if (Math.abs(deltaRadians) < unit.getTraverseSpeed()) {
            unit.setDirection(radians % (Math.PI * 2));
            movement.movementGroup.setTraverseCompleted(unit);
        } else if (deltaRadians < 0) {
            unit.setDirection(unit.getDirection() + unit.getTraverseSpeed());
        } else {
            unit.setDirection(unit.getDirection() - unit.getTraverseSpeed());
        }
    }

    private void updateMovementAcceleration(@Nonnull final Unit unit, @Nonnull final Movement movement) {
        final double distance = unit.getLocation().getDistanceTo(movement.destination);
        
        if (movement.accelerating) {
            if(movement.currentMovementSpeed != movement.targetMovementSpeed){
                movement.currentMovementSpeed += movement.targetMovementSpeed * movement.acceleration;
                movement.accelerationDistance = movement.start.getDistanceTo(unit.getLocation());
                if (movement.currentMovementSpeed >= movement.targetMovementSpeed) {
                    movement.currentMovementSpeed = movement.targetMovementSpeed;
                    movement.accelerating = false;
                }
            } else if (movement.accelerating && distance < movement.accelerationDistance) {
                movement.accelerating = false;
            }
        } else {
            movement.currentMovementSpeed -= movement.targetMovementSpeed * movement.acceleration;
        }        
    }

    private void updateUnitLocation(@Nonnull final Unit unit, @Nonnull final Movement movement) {
        updateMovementAcceleration(unit, movement);

        final double distance = unit.getLocation().getDistanceTo(movement.destination);
        final Location direction = movement.destination.subtract(unit.getLocation()).getNormalized();
        final Location scaledDirection = direction.multiply(movement.currentMovementSpeed).multiply(MOVEMENT_FACTOR);
        final Location updatedUnitLocation = unit.getLocation().add(scaledDirection);

        final double updatedDistance = unit.getLocation().getDistanceTo(updatedUnitLocation);
        if (updatedDistance > distance) {
            unit.setLocation(movement.destination);
            moving.remove(unit);
        } else {
            unit.setLocation(updatedUnitLocation);
        }
    }

    private void move(@Nonnull final Unit unit, @Nonnull final Movement movement) {
        if (unit.getLocation().equals(movement.destination)) {
            moving.remove(unit);
        } else {
            updateUnitDirection(unit, movement);
            if (movement.movementGroup.allTraversesComplete()) {
                updateUnitLocation(unit, movement);
            }
        }
    }

    /**
     * Add units to be moved to a specific destination location.
     *
     * @param units the units to move
     * @param destination the destination location to which the units should move
     */
    public void add(@Nonnull final Collection<Unit> units, @Nonnull final Location destination) {
        // If the unit is already moving, cancel that movement.
        for (final Unit unit : units) {
            moving.remove(unit);
        }

        if (isInsideBoundingBox(units, destination)) {
            // The destination is inside the group of units, so move the units individually instead of as a group.
            for (final Unit unit : units) {
                if (unit.isMovable()) {
                    moving.put(unit, new Movement(new MovementGroup(unit), unit.getLocation(), destination,
                            unit.getMovementSpeed(), unit.getAcceleration()));
                }
            }
        } else {
            // The destination is outside of the center of mass of the unit group. Move the group as a cohesive unit,
            // keeping the current formation.
            final Location centerOfMass = getCenterOfMass(units);
            final MovementGroup movementGroup = new MovementGroup(units);
            for (final Unit unit : units) {
                if (!unit.isMovable()) {
                    continue;
                }

                // Calculate the delta the unit is from the center of mass.
                final Location delta = centerOfMass.subtract(unit.getLocation());

                // Calculate the unit destination using the delta relative to the group destination.
                final Location unitDestination = destination.subtract(delta);

                // Calculate the slowest movement speed of all units, since they are going to stay in formation.
                final double movementSpeed = units.stream().mapToDouble(Unit::getMovementSpeed).min().orElse(0);

                // Calculate the slowest acceleration of all units, since they are going to stay in formation.
                final double acceleration = units.stream().mapToDouble(Unit::getAcceleration).min().orElse(0);

                // Add the unit to the moving map using the relative location of the unit compared to the center of mass
                // of all the units that need to move.
                moving.put(unit,
                        new Movement(movementGroup, unit.getLocation(), unitDestination, movementSpeed, acceleration));
            }
        }
    }

    @Nonnull
    private Location getCenterOfMass(@Nonnull final Collection<Unit> units) {
        final double meanX = units.stream().mapToDouble(unit -> unit.getLocation().getX()).average().orElse(0);
        final double meanY = units.stream().mapToDouble(unit -> unit.getLocation().getY()).average().orElse(0);
        return new Location(meanX, meanY);
    }

    private boolean isInsideBoundingBox(@Nonnull final Collection<Unit> units, @Nonnull final Location destination) {
        final double minX = units.stream().mapToDouble(unit -> unit.getLocation().getX()).min().orElse(0);
        final double minY = units.stream().mapToDouble(unit -> unit.getLocation().getY()).min().orElse(0);
        final double maxX = units.stream().mapToDouble(unit -> unit.getLocation().getX()).max().orElse(0);
        final double maxY = units.stream().mapToDouble(unit -> unit.getLocation().getY()).max().orElse(0);

        return minX <= destination.getX() && maxX >= destination.getX() && minY <= destination.getY()
                && maxY >= destination.getY();
    }

    private static class MovementGroup {
        @Nonnull
        private final Map<String, Boolean> traverseCompletionMap;

        MovementGroup(final Unit unit) {
            traverseCompletionMap = new HashMap<>();
            traverseCompletionMap.put(unit.getId(), false);
        }

        MovementGroup(final Collection<Unit> units) {
            traverseCompletionMap = new HashMap<>();
            units.forEach(unit -> traverseCompletionMap.put(unit.getId(), false));
        }

        void setTraverseCompleted(@Nonnull final Unit unit) {
            traverseCompletionMap.put(unit.getId(), true);
        }

        boolean allTraversesComplete() {
            return traverseCompletionMap.values().stream().allMatch(bool -> bool);
        }
    }

    private static class Movement {
        @Nonnull
        private final MovementGroup movementGroup;
        @Nonnull
        private final Location start;
        @Nonnull
        private final Location destination;
        private final double targetMovementSpeed;
        private final double acceleration;

        private boolean accelerating = true;

        private double currentMovementSpeed = 0;
        private double accelerationDistance = 0;

        Movement(
                @Nonnull final MovementGroup movementGroup, @Nonnull final Location start,
                @Nonnull final Location destination, final double targetMovementSpeed, final double acceleration) {
            this.movementGroup = movementGroup;
            this.start = start;
            this.destination = destination;
            this.targetMovementSpeed = targetMovementSpeed;
            this.acceleration = acceleration;
        }
    }
}
