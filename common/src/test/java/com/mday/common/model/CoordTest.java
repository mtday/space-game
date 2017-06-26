package com.mday.common.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Perform testing on the {@link Coord} class.
 */
public class CoordTest {
    /**
     * Make sure the default constructor sets the appropriate axis values.
     */
    @Test
    public void testDefaultConstructor() {
        final Coord c = new Coord();

        assertEquals(0, c.x);
        assertEquals(0, c.y);
        assertEquals(0, c.z);
    }

    /**
     * Make sure the two-parameter constructor sets the appropriate axis values.
     */
    @Test
    public void testTwoParameterConstructor() {
        final Coord c = new Coord(-2, -1);

        assertEquals(-2, c.x);
        assertEquals(-1, c.y);
        assertEquals(3, c.z);
    }

    /**
     * Make sure the three-parameter constructor sets the appropriate axis values.
     */
    @Test
    public void testThreeParameterConstructor() {
        final Coord c = new Coord(-2, -1, 3);

        assertEquals(-2, c.x);
        assertEquals(-1, c.y);
        assertEquals(3, c.z);
    }

    /**
     * Make sure an error is thrown when invalid parameters are used.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidParameters() {
        new Coord(1, 1, 1);
    }

    /**
     * Make sure equals works correctly.
     */
    @Test
    public void testEquals() {
        final Coord a = new Coord(0, 0, 0);
        final Coord b = new Coord(-1, 0, 1);
        final Coord c = new Coord(-1, 1, 0);

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
        assertEquals(961, new Coord().hashCode());
        assertEquals(930, new Coord(-1, 0, 1).hashCode());
    }

    /**
     * Make sure toString works correctly.
     */
    @Test
    public void testToString() {
        assertEquals("Coord[x=-1, y=-2, z=3]", new Coord(-1, -2, 3).toString());
    }
}
