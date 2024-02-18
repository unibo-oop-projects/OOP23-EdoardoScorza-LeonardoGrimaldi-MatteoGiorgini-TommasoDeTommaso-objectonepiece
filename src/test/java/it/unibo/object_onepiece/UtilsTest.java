/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unibo.object_onepiece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unibo.object_onepiece.model.Utils.CardinalDirection;
import it.unibo.object_onepiece.model.Utils.*;

public class UtilsTest {

    private Position p = new Position(4, 2);
    private Bound b = new Bound(15, 15);

    @Test void testMoveTowards() throws ClassNotFoundException {
        assertEquals(new Position(4, 3), p.moveTowards(CardinalDirection.EAST));
        assertEquals(new Position(4, 1), p.moveTowards(CardinalDirection.WEST));
        assertEquals(new Position(3, 2), p.moveTowards(CardinalDirection.NORTH));
        assertEquals(new Position(5, 2), p.moveTowards(CardinalDirection.SOUTH));
    }

    @Test void testSum() throws ClassNotFoundException {
        assertEquals(new Position(5, 3), p.sum(new Position(1, 1)));
        assertEquals(new Position(7, 6), p.sum(new Position(3, 4)));
        assertEquals(new Position(5, 0), p.sum(new Position(1, -2)));
        assertEquals(new Position(10, 7), p.sum(new Position(6, 5)));
    }

    @Test void testDistanceFrom() throws ClassNotFoundException {
        assertEquals(3, p.distanceFrom(new Position(4, 5)));
        assertEquals(5, p.distanceFrom(new Position(9, 2)));
        assertNotEquals(3, p.distanceFrom(new Position(4, 4)));
        assertNotEquals(5, p.distanceFrom(new Position(7, 9)));
    }

    @Test void testVersorOf() throws ClassNotFoundException {
        assertEquals(new Position(1, 1), p.versorOf(new Position(5, 5)));
        assertEquals(new Position(0, 1), p.versorOf(new Position(4, 5)));
        assertEquals(new Position(-1, 0), p.versorOf(new Position(2, 2)));
        assertEquals(new Position(0, 0), p.versorOf(new Position(4, 2)));
    }

    @Test void testIsInlineWith() throws ClassNotFoundException {
        assertEquals(true, p.isInlineWith(new Position(4, 5), CardinalDirection.NORTH));
        assertEquals(false, p.isInlineWith(new Position(5, 5), CardinalDirection.NORTH));
        assertEquals(true, p.isInlineWith(new Position(4, 5), CardinalDirection.SOUTH));
        assertEquals(true, p.isInlineWith(new Position(7, 2), CardinalDirection.EAST));
        assertEquals(true, p.isInlineWith(new Position(7, 2), CardinalDirection.WEST));
        assertEquals(false, p.isInlineWith(new Position(6, 5), CardinalDirection.WEST));
    }

    @Test void testWhereTo() throws ClassNotFoundException {
        assertEquals(CardinalDirection.SOUTH, p.whereTo(new Position(5, 5)));
        assertEquals(CardinalDirection.EAST, p.whereTo(new Position(4, 5)));
        assertEquals(CardinalDirection.NORTH, p.whereTo(new Position(3, 2)));
        assertEquals(CardinalDirection.WEST, p.whereTo(new Position(4, 1)));
    }

    @Test void testOpposite() throws ClassNotFoundException {
        Position p1 = new Position(1, 5);
        Position p2 = new Position(5, 1);
        assertEquals(new Position(13, 5), p1.opposite(CardinalDirection.NORTH, new Bound(15, 15)));
        assertEquals(new Position(5, 13), p2.opposite(CardinalDirection.WEST, new Bound(15, 15)));
    }

    @Test void testIsInside() throws ClassNotFoundException {
        assertEquals(true, b.isInside(new Position(5, 3)));
        assertEquals(false, b.isInside(new Position(14, 14)));
        assertEquals(false, b.isInside(new Position(5, 14)));
        assertEquals(true, b.isInside(new Position(9, 10)));
    }
}
