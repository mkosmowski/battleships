package battleships.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static battleships.model.State.DAMAGED;
import static battleships.model.State.OPERATIONAL;

@NoArgsConstructor
public class ShipPart implements Field {

    @Getter
    private State state = OPERATIONAL;
    private String symbol = " # ";
    @Getter
    private boolean visible = false;

    private void changeState() {
        if (OPERATIONAL.equals(state)) {
            state = DAMAGED;
        }
        flipSymbol();
    }

    private void flipSymbol() {
        if (" # ".equals(symbol)) {
            symbol = " X ";
        }
    }

    public String getSymbol() {
        return (visible) ? symbol : "[ ]";
    }

    public void hit() {
        changeState();
        visible = true;
    }
}