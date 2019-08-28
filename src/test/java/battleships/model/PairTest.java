package battleships.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PairTest {
    @Test
    public void shouldPairKeepBothElements() {
        // given
        Integer expectedElement1 = 5;
        Integer expectedElement2 = 10;
        Pair<Integer> pair = new Pair<>(expectedElement1, expectedElement2);

        // when
        Integer element1 = pair.getE1();
        Integer element2 = pair.getE2();

        // then
        assertEquals(expectedElement1, element1);
        assertEquals(expectedElement2, element2);
    }
}