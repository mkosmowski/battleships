package battleships.model;

import lombok.Getter;

import java.util.ArrayList;

import static java.util.stream.IntStream.range;

public class Ship {
    private final ShipType type;
    @Getter
    private final ArrayList<ShipPart> shipParts = new ArrayList<>();
    @Getter
    private final Orientation orientation;
    private int length;

    Ship(ShipType shipType, Orientation orientation) {
        this.type = shipType;
        this.length = type.getLength();
        this.orientation = orientation;
        range(0, length).forEach(i -> shipParts.add(new ShipPart()));
    }

    public void restoreLength() {
        length = shipParts.size();
    }

    public ShipPart getNextPart() {
        return shipParts.get(--length);
    }
}
