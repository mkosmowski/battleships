package battleships.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShipType {
    BATTLESHIP(5),
    DESTROYER(4);

    private int length;
}