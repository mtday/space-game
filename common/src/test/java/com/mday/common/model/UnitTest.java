package com.mday.common.model;

import static com.mday.common.model.UnitType.RECON_DRONE;
import static com.mday.common.model.UnitType.SHIPYARD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Perform testing on the {@link Unit} class.
 */
public class UnitTest {
    /**
     * Make sure the default constructor sets the coordinate location.
     */
    @Test
    public void testDefaultConstructor() {
        final Unit u = new Unit();

        assertEquals(SHIPYARD, u.type);
        assertEquals(new Coord(0, 0, 0), u.coord);
    }

    /**
     * Make sure the parameter constructor sets the coordinate value.
     */
    @Test
    public void testTwoParameterConstructor() {
        final Unit u = new Unit(RECON_DRONE, new Coord(-2, -1));

        assertEquals(RECON_DRONE, u.type);
        assertEquals(new Coord(-2, -1, 3), u.coord);
    }

    /**
     * Make sure equals works correctly.
     */
    @Test
    public void testEquals() {
        final Unit a = new Unit(SHIPYARD, new Coord(0, 0, 0));
        final Unit b = new Unit(RECON_DRONE, new Coord(-1, 0, 1));
        final Unit c = new Unit(RECON_DRONE, new Coord(0, 0, 0));

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
        assertEquals(-323735860, new Unit(SHIPYARD, new Coord(0, 0, 0)).hashCode());
        assertEquals(161471323, new Unit(RECON_DRONE, new Coord(-1, 0, 1)).hashCode());
    }

    /**
     * Make sure toString works correctly.
     */
    @Test
    public void testToString() {
        assertEquals("Unit[type=SHIPYARD, coord=Coord[x=1, y=2, z=-3]]",
                new Unit(SHIPYARD, new Coord(1, 2, -3)).toString());
    }
}
