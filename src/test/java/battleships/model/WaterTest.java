package battleships.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WaterTest {

    private Water waterField;

    @Before
    public void setUp() {
        waterField = new Water();
    }

    @Test
    public void waterFieldIsInitiallyInvisible() {
        // when
        String symbol = waterField.getSymbol();
        boolean isVisible = waterField.isVisible();

        // then
        assertEquals("[ ]", symbol);
        assertFalse(isVisible);
    }

    @Test
    public void waterFieldAfterHitIsVisible() {
        // when
        waterField.hit();
        String symbol = waterField.getSymbol();
        boolean isVisible = waterField.isVisible();

        // then
        assertEquals(" ~ ", symbol);
        assertTrue(isVisible);
    }
}