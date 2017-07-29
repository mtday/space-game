package com.mday.common.model;

import static com.mday.common.model.UnitType.PLANET;
import static com.mday.common.model.UnitType.SHIP;
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
        final Unit unit = new Unit(SHIP, "id", new Location(-2, -1));

        assertEquals(SHIP, unit.getUnitType());
        assertEquals("id", unit.getId());
        assertEquals(new Location(-2, -1), unit.getLocation());
    }

    /**
     * Make sure equals works correctly.
     */
    @Test
    public void testEquals() {
        final Unit a = new Unit(PLANET, "id1", new Location(1, 1));
        final Unit b = new Unit(PLANET, "id1", new Location(1, 2));
        final Unit c = new Unit(PLANET, "id2", new Location(1, 1));
        final Unit d = new Unit(SHIP, "id1", new Location(1, 1));

        assertNotEquals(a, null);
        assertEquals(a, a);
        assertEquals(a, b);
        assertNotEquals(a, c);
        assertEquals(a, d);
        assertEquals(b, a);
        assertEquals(b, b);
        assertNotEquals(b, c);
        assertEquals(b, d);
        assertNotEquals(c, a);
        assertNotEquals(c, b);
        assertEquals(c, c);
        assertNotEquals(c, d);
        assertEquals(d, a);
        assertEquals(d, b);
        assertNotEquals(d, c);
        assertEquals(d, d);
    }

    /**
     * Make sure hashCode works correctly.
     */
    @Test
    public void testHashCode() {
        assertEquals(3386, new Unit(PLANET, "id", new Location(1, 1)).hashCode());
    }

    /**
     * Make sure toString works correctly.
     */
    @Test
    public void testToString() {
        assertEquals("Unit[unitType=PLANET, id=id, location=Location[x=1.00, y=2.00], selected=false]",
                new Unit(PLANET, "id", new Location(1, 2)).toString());
    }
}
