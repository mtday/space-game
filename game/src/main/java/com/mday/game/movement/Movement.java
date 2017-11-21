package com.mday.game.movement;

import com.mday.model.Location;
import com.mday.model.Unit;

import javax.annotation.Nonnull;

/**
 * Used to manage the movement of a unit from one location to another.
 */
public class Movement {
    @Nonnull
    private final Unit unit;
    @Nonnull
    private final MovementGroup movementGroup;
    @Nonnull
    private final Location start;
    @Nonnull
    private final Location destination;
    private final double targetMovementSpeed;
    private final double acceleration;

    private boolean accelerating = true;
    private boolean decelerating = false;

    private double currentMovementSpeed = 0;
    private double accelerationDistance = 0;

    /**
     * Create an instance of this class.
     *
     * @param unit        the unit to move
     * @param destination the destination location to which the unit should be moved
     */
    public Movement(@Nonnull final Unit unit, @Nonnull final Location destination) {
        this(unit, new MovementGroup(unit), unit.getLocation(), destination,
                unit.getMovementSpeed(), unit.getAcceleration());
    }

    /**
     * Create an instance of this class.
     *
     * @param unit                the unit to move
     * @param movementGroup       the group information tracking the movement of a collection of units
     * @param start               the starting point of the unit prior to movement
     * @param destination         the destination location to which the unit should be moved
     * @param targetMovementSpeed the maximum speed to use when performing the movement
     * @param acceleration        the rate of acceleration to use when moving
     */
    public Movement(
            @Nonnull final Unit unit,
            @Nonnull final MovementGroup movementGroup,
            @Nonnull final Location start,
            @Nonnull final Location destination,
            final double targetMovementSpeed,
            final double acceleration) {
        this.unit = unit;
        this.movementGroup = movementGroup;
        this.start = start;
        this.destination = destination;
        this.targetMovementSpeed = targetMovementSpeed;
        this.acceleration = acceleration;
    }

    /**
     * Retrieve the unit that is being moved.
     *
     * @return the unit that is being moved
     */
    @Nonnull
    public Unit getUnit() {
        return unit;
    }

    /**
     * Retrieve the group of units that are moving together.
     *
     * @return the group of units that are moving together
     */
    @Nonnull
    public MovementGroup getMovementGroup() {
        return movementGroup;
    }

    /**
     * Retrieve the starting point of the unit prior to movement.
     *
     * @return the starting point of the unit prior to movement
     */
    @Nonnull
    public Location getStart() {
        return start;
    }

    /**
     * Retrieve the destination location to which the unit is moving.
     *
     * @return the destination location to which the unit is moving
     */
    @Nonnull
    public Location getDestination() {
        return destination;
    }

    /**
     * Retrieve the maximum speed to reach when moving.
     *
     * @return the maximum speed to reach when moving
     */
    public double getTargetMovementSpeed() {
        return targetMovementSpeed;
    }

    /**
     * Retrieve the rate of acceleration to use when moving.
     *
     * @return the rate of acceleration to use when moving
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * Retrieve whether the movement is currently in an acceleration phase.
     *
     * @return whether the movement is currently in an acceleration phase
     */
    public boolean isAccelerating() {
        return accelerating;
    }

    /**
     * Update whether the movement is currently in an acceleration phase.
     *
     * @param accelerating the new value indicating whether the movement is currently in an acceleration phase
     */
    public void setAccelerating(final boolean accelerating) {
        this.accelerating = accelerating;
    }

    /**
     * Retrieve whether the movement is currently in a deceleration phase.
     *
     * @return whether the movement is currently in a deceleration phase
     */
    public boolean isDecelerating() {
        return decelerating;
    }

    /**
     * Update whether the movement is currently in a deceleration phase.
     *
     * @param decelerating the new value indicating whether the movement is currently in a deceleration phase
     */
    public void setDecelerating(final boolean decelerating) {
        this.decelerating = decelerating;
    }

    /**
     * Retrieve the current movement speed of the unit.
     *
     * @return the current movement speed of the unit
     */
    public double getCurrentMovementSpeed() {
        return currentMovementSpeed;
    }

    /**
     * Update the current movement speed of the unit.
     *
     * @param currentMovementSpeed the new value indicating the current movement speed of the unit
     */
    public void setCurrentMovementSpeed(final double currentMovementSpeed) {
        this.currentMovementSpeed = currentMovementSpeed;
    }

    /**
     * Retrieve the distance over which the unit accelerated to the current speed.
     *
     * @return the distance over which the unit accelerated to the current speed
     */
    public double getAccelerationDistance() {
        return accelerationDistance;
    }

    /**
     * Update the distance over which the unit accelerated to the current speed.
     *
     * @param accelerationDistance the new value indicating the distance over which the unit accelerated to the
     * current speed.
     */
    public void setAccelerationDistance(final double accelerationDistance) {
        this.accelerationDistance = accelerationDistance;
    }
}
