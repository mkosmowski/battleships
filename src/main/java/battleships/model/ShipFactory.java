package battleships.model;

import java.util.Random;

public class ShipFactory {

    public static Ship buildShip(ShipType shipType) {
        return new Ship(shipType, randomOrientation());
    }

    private static Orientation randomOrientation() {
        return Orientation.values()[new Random().nextInt(Orientation.values().length)];
    }
}
