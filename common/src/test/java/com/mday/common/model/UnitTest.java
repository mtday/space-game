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
     * Make sure the parameter constructor sets the correct values.
     */
    @Test
    public void testParameterConstructor() {
        final Unit u = new Unit("id", "owner", RECON_DRONE, new Coord(-2, -1));

        assertEquals("id", u.getId());
        assertEquals("owner", u.getOwner());
        assertEquals(RECON_DRONE, u.getType());
        assertEquals(new Coord(-2, -1), u.getCoord());
    }

    /**
     * Make sure equals works correctly.
     */
    @Test
    public void testEquals() {
        final Unit a = new Unit("id1", "owner1", SHIPYARD, new Coord(0, 0, 0));
        final Unit b = new Unit("id1", "owner1", SHIPYARD, new Coord(-1, 0, 1));
        final Unit c = new Unit("id1", "owner1", RECON_DRONE, new Coord(0, 0, 0));
        final Unit d = new Unit("id1", "owner2", SHIPYARD, new Coord(0, 0, 0));
        final Unit e = new Unit("id2", "owner1", SHIPYARD, new Coord(0, 0, 0));

        assertNotEquals(a, null);
        assertEquals(a, a);
        assertNotEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(a, d);
        assertNotEquals(a, e);
        assertNotEquals(b, a);
        assertEquals(b, b);
        assertNotEquals(b, c);
        assertNotEquals(b, d);
        assertNotEquals(b, e);
        assertNotEquals(c, a);
        assertNotEquals(c, b);
        assertEquals(c, c);
        assertNotEquals(c, d);
        assertNotEquals(c, e);
        assertNotEquals(d, a);
        assertNotEquals(d, b);
        assertNotEquals(d, c);
        assertEquals(d, d);
        assertNotEquals(d, e);
        assertNotEquals(e, a);
        assertNotEquals(e, b);
        assertNotEquals(e, c);
        assertNotEquals(e, d);
        assertEquals(e, e);
    }

    /**
     * Make sure hashCode works correctly.
     */
    @Test
    public void testHashCode() {
        assertEquals(-950779140, new Unit("id", "owner", SHIPYARD, new Coord(0, 0, 0)).hashCode());
    }

    /**
     * Make sure toString works correctly.
     */
    @Test
    public void testToString() {
        assertEquals("Unit[id=id, owner=owner, type=SHIPYARD, coord=Coord[x=1, y=2, z=-3], direction=0]",
                new Unit("id", "owner", SHIPYARD, new Coord(1, 2, -3)).toString());
    }
}
