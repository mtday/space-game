package com.mday.game.movement;

import static java.util.Collections.singleton;

import com.mday.model.Unit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

/**
 * Provides information about a group of units moving as a cohesive group in the same direction and speed.
 */
public class MovementGroup {
    @Nonnull
    private final Map<Unit, Boolean> traverseCompletionMap;

    /**
     * Create a movement group for a single unit.
     *
     * @param unit the unit within this group
     */
    public MovementGroup(@Nonnull final Unit unit) {
        this(singleton(unit));
    }

    /**
     * Create a movement group for the provided units.
     *
     * @param units the units within this group
     */
    public MovementGroup(@Nonnull final Collection<Unit> units) {
        traverseCompletionMap = new HashMap<>();
        units.forEach(unit -> traverseCompletionMap.put(unit, false));
    }

    /**
     * Update the movement information for the provided unit to indicate that it has completed traversing.
     *
     * @param unit the unit to update
     */
    public void setTraverseCompleted(@Nonnull final Unit unit) {
        traverseCompletionMap.put(unit, true);
    }

    /**
     * Retrieve whether all the units in this group have completed traversing.
     *
     * @return whether all the units in this group have completed traversing
     */
    public boolean allTraversesComplete() {
        return traverseCompletionMap.values().stream().allMatch(bool -> bool);
    }
}
