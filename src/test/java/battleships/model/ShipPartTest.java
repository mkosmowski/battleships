package battleships.model;

import org.junit.Before;
import org.junit.Test;

import static battleships.model.State.DAMAGED;
import static battleships.model.State.OPERATIONAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShipPartTest {

    private ShipPart shipPartField;

    @Before
    public void setUp() {
        shipPartField = new ShipPart();
    }

    @Test
    public void shipPartFieldIsInitiallyInvisible() {
        // when
        String symbol = shipPartField.getSymbol();
        boolean isVisible = shipPartField.isVisible();

        // then
        assertEquals("[ ]", symbol);
        assertFalse(isVisible);
    }

    @Test
    public void shipPartFieldIsInitiallyOperational() {
        // when
        State state = shipPartField.getState();

        // then
        assertEquals(OPERATIONAL, state);
    }

    @Test
    public void shipPartFieldAfterHitIsVisible() {
        // when
        shipPartField.hit();
        String symbol = shipPartField.getSymbol();
        boolean isVisible = shipPartField.isVisible();

        // then
        assertEquals(" X ", symbol);
        assertTrue(isVisible);
    }

    @Test
    public void shipPartFieldAfterHitIsDamaged() {
        // when
        shipPartField.hit();
        State state = shipPartField.getState();

        // then
        assertEquals(DAMAGED, state);
    }
}