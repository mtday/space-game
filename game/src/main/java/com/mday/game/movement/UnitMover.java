package com.mday.game.movement;

import com.mday.event.ClockTickObserver;
import com.mday.model.Location;
import com.mday.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
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
    private static final double TWO_PI = 2 * Math.PI;
    private static final double TWENTY_DEGREES = 20 * Math.PI / 180;

    @Override
    public void tick() {
        moving.forEach(this::move);
    }

    private void updateUnitDirection(@Nonnull final Unit unit, @Nonnull final Movement movement) {
        final Location direction = movement.getDestination().subtract(unit.getLocation()).getNormalized();
        final double radians = (Math.PI / 2) + Math.atan2(direction.getY(), direction.getX());
        double deltaRadians = unit.getDirection() - radians;
        if (Math.abs(deltaRadians) > Math.PI && deltaRadians < 0) {
            deltaRadians += TWO_PI;
        } else if (Math.abs(deltaRadians) > Math.PI && deltaRadians > 0) {
            deltaRadians -= TWO_PI;
        }

        if (Math.abs(deltaRadians) > TWENTY_DEGREES) {
            if (deltaRadians < 0) {
                unit.setDirection(unit.getDirection() + unit.getTraverseSpeed());
            } else {
                unit.setDirection(unit.getDirection() - unit.getTraverseSpeed());
            }
        } else if (Math.abs(deltaRadians) < unit.getTraverseSpeed()) {
            unit.setDirection(radians % TWO_PI);
            movement.getMovementGroup().setTraverseCompleted(unit);
        } else if (deltaRadians < 0) {
            unit.setDirection(unit.getDirection() + unit.getTraverseSpeed());
        } else {
            unit.setDirection(unit.getDirection() - unit.getTraverseSpeed());
        }
    }

    private void updateMovementAcceleration(@Nonnull final Unit unit, @Nonnull final Movement movement) {
        if (movement.isAccelerating()
                && Math.abs(movement.getCurrentMovementSpeed() - movement.getTargetMovementSpeed()) > 0.05) {
            // Speed up until the unit hits top speed.
            movement.setCurrentMovementSpeed(movement.getCurrentMovementSpeed()
                    + movement.getTargetMovementSpeed() * movement.getAcceleration());
            movement.setAccelerationDistance(movement.getStart().getDistanceTo(unit.getLocation()));
            if (movement.getCurrentMovementSpeed() >= movement.getTargetMovementSpeed()) {
                movement.setCurrentMovementSpeed(movement.getTargetMovementSpeed());
                movement.setAccelerating(false);
            }
        }
        if (movement.isDecelerating() && movement.getCurrentMovementSpeed() > 0) {
            // Slow down further.
            movement.setCurrentMovementSpeed(movement.getCurrentMovementSpeed()
                    - movement.getTargetMovementSpeed() * movement.getAcceleration());
        }

        // When the unit gets close enough to the destination, start decelerating.
        final double distance = unit.getLocation().getDistanceTo(movement.getDestination());
        if (!movement.isDecelerating() && distance < movement.getAccelerationDistance()) {
            movement.setDecelerating(true);
            movement.setAccelerating(false);
        }
    }

    private void updateUnitLocation(@Nonnull final Unit unit, @Nonnull final Movement movement) {
        updateMovementAcceleration(unit, movement);

        final double distance = unit.getLocation().getDistanceTo(movement.getDestination());
        final Location direction = movement.getDestination().subtract(unit.getLocation()).getNormalized();
        final Location scaledDirection =
                direction.multiply(movement.getCurrentMovementSpeed()).multiply(MOVEMENT_FACTOR);
        final Location updatedUnitLocation = unit.getLocation().add(scaledDirection);

        final double updatedDistance = unit.getLocation().getDistanceTo(updatedUnitLocation);
        if (updatedDistance > distance) {
            unit.setLocation(movement.getDestination());
            moving.remove(unit);
        } else {
            unit.setLocation(updatedUnitLocation);
        }
    }

    private void move(@Nonnull final Unit unit, @Nonnull final Movement movement) {
        if (unit.getLocation().equals(movement.getDestination())) {
            moving.remove(unit);
        } else {
            updateUnitDirection(unit, movement);
            if (movement.getMovementGroup().allTraversesComplete()) {
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
        units.forEach(moving::remove);

        if (isInsideBoundingBox(units, destination)) {
            // The destination is inside the group of units, so move the units individually instead of as a group.
            units.stream()
                    .filter(Unit::isMovable)
                    .map(unit -> new Movement(unit, destination))
                    .forEach(movement -> moving.put(movement.getUnit(), movement));
        } else {
            // The destination is outside of the center of mass of the unit group. Move the group as a cohesive unit,
            // keeping the current formation.
            final Location centerOfMass = getCenterOfMass(units);
            final MovementGroup movementGroup = new MovementGroup(units);
            units.stream()
                    .filter(Unit::isMovable)
                    .map(unit -> {
                        // Calculate the delta the unit is from the center of mass.
                        final Location delta = centerOfMass.subtract(unit.getLocation());

                        // Calculate the unit destination using the delta relative to the group destination.
                        final Location unitDestination = destination.subtract(delta);

                        // Calculate the slowest movement speed of all units, since they are going to stay in formation.
                        final double movementSpeed = units.stream().mapToDouble(Unit::getMovementSpeed).min().orElse(0);

                        // Calculate the slowest acceleration of all units, since they are going to stay in formation.
                        final double acceleration = units.stream().mapToDouble(Unit::getAcceleration).min().orElse(0);

                        // Add the unit to the moving map using the relative location of the unit compared to the
                        // center of mass of all the units that need to move.
                        return new Movement(unit, movementGroup, unit.getLocation(), unitDestination,
                                movementSpeed, acceleration);
                    })
                    .forEach(movement -> moving.put(movement.getUnit(), movement));
        }
    }

    @Nonnull
    private Location getCenterOfMass(@Nonnull final Collection<Unit> units) {
        final double meanX = units.stream().map(Unit::getLocation).mapToDouble(Location::getX).average().orElse(0);
        final double meanY = units.stream().map(Unit::getLocation).mapToDouble(Location::getY).average().orElse(0);
        return new Location(meanX, meanY);
    }

    private boolean isInsideBoundingBox(@Nonnull final Collection<Unit> units, @Nonnull final Location destination) {
        final double minX = units.stream().map(Unit::getLocation).mapToDouble(Location::getX).min().orElse(0);
        final double minY = units.stream().map(Unit::getLocation).mapToDouble(Location::getY).min().orElse(0);
        final double maxX = units.stream().map(Unit::getLocation).mapToDouble(Location::getX).max().orElse(0);
        final double maxY = units.stream().map(Unit::getLocation).mapToDouble(Location::getY).max().orElse(0);

        return minX <= destination.getX() && maxX >= destination.getX() && minY <= destination.getY()
                && maxY >= destination.getY();
    }
}
