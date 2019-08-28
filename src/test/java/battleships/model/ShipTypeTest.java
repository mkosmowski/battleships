package battleships.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShipTypeTest {

    @Test
    public void battleshipHas5Fields() {
        assertEquals(5, ShipType.BATTLESHIP.getLength());
    }

    @Test
    public void destroyerHas4Fields() {
        assertEquals(4, ShipType.DESTROYER.getLength());
    }
}