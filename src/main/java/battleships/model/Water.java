package battleships.model;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class Water implements Field {
    private final Map<Boolean, String> symbol = ImmutableMap.of(false, "[ ]", true, " ~ ");
    @Getter
    private boolean visible = false;

    public String getSymbol() {
        return symbol.get(visible);
    }

    public void hit() {
        visible = true;
    }
}