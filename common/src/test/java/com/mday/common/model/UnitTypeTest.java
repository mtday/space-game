package com.mday.common.model;

import static com.mday.common.model.UnitType.SHIPYARD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Perform testing on the {@link UnitType} enum.
 */
public class UnitTypeTest {
    /**
     * Just for 100% code coverage.
     */
    @Test
    public void test() {
        assertTrue(UnitType.values().length > 0);
        assertEquals(SHIPYARD, UnitType.valueOf(SHIPYARD.name()));
    }
}
