package com.mday.common.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Perform testing on the {@link Location} class.
 */
public class LocationTest {
    /**
     * Make sure the default constructor sets the appropriate axis values.
     */
    @Test
    public void testDefaultConstructor() {
        final Location location = new Location();

        assertEquals(0, location.getX());
        assertEquals(0, location.getY());
    }

    /**
     * Make sure the two-parameter constructor sets the appropriate axis values.
     */
    @Test
    public void testParameterConstructor() {
        final Location location = new Location(-2, -1);

        assertEquals(-2, location.getX());
        assertEquals(-1, location.getY());
    }

    /**
     * Make sure equals works correctly.
     */
    @Test
    public void testEquals() {
        final Location a = new Location(0, 0);
        final Location b = new Location(0, 1);
        final Location c = new Location(1, 0);

        assertNotEquals(a, null);
        assertEquals(a, a);
        assertNotEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(b, a);
        assertEquals(b, b);
        assertNotEquals(b, c);
        assertNotEquals(c, a);
        assertNotEquals(c, b);
        assertEquals(c, c);
    }

    /**
     * Make sure hashCode works correctly.
     */
    @Test
    public void testHashCode() {
        assertEquals(961, new Location().hashCode());
        assertEquals(993, new Location(1, 1).hashCode());
    }

    /**
     * Make sure toString works correctly.
     */
    @Test
    public void testToString() {
        assertEquals("Location[x=-1, y=-2]", new Location(-1, -2).toString());
    }
}
