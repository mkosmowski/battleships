package battleships.model;

import org.junit.Test;

import static battleships.model.ShipType.BATTLESHIP;
import static battleships.model.ShipType.DESTROYER;
import static org.junit.Assert.assertEquals;

public class ShipFactoryTest {

    @Test
    public void buildBattleship() {
        // when
        Ship battleship = ShipFactory.buildShip(BATTLESHIP);
        int partsCount = battleship.getShipParts().size();

        // then
        assertEquals(BATTLESHIP.getLength(), partsCount);
    }

    @Test
    public void buildDestroyer() {
        // when
        Ship destroyer = ShipFactory.buildShip(DESTROYER);
        int partsCount = destroyer.getShipParts().size();

        // then
        assertEquals(DESTROYER.getLength(), partsCount);
    }
}