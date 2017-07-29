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
        final Unit unit = new Unit(RECON_DRONE, "id", "owner", new Location(-2, -1));

        assertEquals(RECON_DRONE, unit.getType());
        assertEquals("id", unit.getId());
        assertEquals("owner", unit.getOwner());
        assertEquals(new Location(-2, -1), unit.getLocation());
    }

    /**
     * Make sure equals works correctly.
     */
    @Test
    public void testEquals() {
        final Unit a = new Unit(SHIPYARD, "id1", "owner1", new Location(1, 1));
        final Unit b = new Unit(SHIPYARD, "id1", "owner1", new Location(1, 2));
        final Unit c = new Unit(SHIPYARD, "id1", "owner2", new Location(1, 1));
        final Unit d = new Unit(SHIPYARD, "id2", "owner1", new Location(1, 1));
        final Unit e = new Unit(RECON_DRONE, "id1", "owner1", new Location(1, 1));

        assertNotEquals(a, null);
        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(a, c);
        assertNotEquals(a, d);
        assertEquals(a, e);
        assertEquals(b, a);
        assertEquals(b, b);
        assertEquals(b, c);
        assertNotEquals(b, d);
        assertEquals(b, e);
        assertEquals(c, a);
        assertEquals(c, b);
        assertEquals(c, c);
        assertNotEquals(c, d);
        assertEquals(c, e);
        assertNotEquals(d, a);
        assertNotEquals(d, b);
        assertNotEquals(d, c);
        assertEquals(d, d);
        assertNotEquals(d, e);
        assertEquals(e, a);
        assertEquals(e, b);
        assertEquals(e, c);
        assertNotEquals(e, d);
        assertEquals(e, e);
    }

    /**
     * Make sure hashCode works correctly.
     */
    @Test
    public void testHashCode() {
        assertEquals(3386, new Unit(SHIPYARD, "id", "owner", new Location(1, 1)).hashCode());
    }

    /**
     * Make sure toString works correctly.
     */
    @Test
    public void testToString() {
        assertEquals("Unit[type=SHIPYARD, id=id, owner=owner, location=Location[x=1.00, y=2.00], "
                + "direction=0, selected=false]", new Unit(SHIPYARD, "id", "owner", new Location(1, 2)).toString());
    }
}
