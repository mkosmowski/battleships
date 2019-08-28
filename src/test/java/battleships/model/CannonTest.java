package battleships.model;

import battleships.gui.Board;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CannonTest {

    private Cannon cannon;
    private Board board;

    @Before
    public void setUp() {
        cannon = new Cannon();
        board = new Board();
    }

    @Test
    public void increaseShotCountAfterValidShot() {
        // given
        String coordinates = "D6";
        int noShotsCount = cannon.getCannonballsShot();

        // when
        cannon.shoot(board, coordinates);
        int afterOneShotCount = cannon.getCannonballsShot();

        // then
        assertEquals(1, afterOneShotCount - noShotsCount);
    }

    @Test
    public void doNotIncreaseShotCountAfterInvalidShot() {
        // given
        List<String> invalidCoordinates = Arrays.asList(
                "X6", "C15", "Y99", "A1B2", "AAABBBCCC$#@$@$@#", "  ", "Q-1", "Battleships rule!");

        // when
        int noShotsCount = cannon.getCannonballsShot();
        invalidCoordinates.forEach(c -> cannon.shoot(board, c));
        int afterAllInvalidShotsCount = cannon.getCannonballsShot();

        // then
        assertEquals(noShotsCount, afterAllInvalidShotsCount);
    }

    @Test
    public void doNotIncreaseShotCountAfterRepeatedShot() {
        // given
        String coordinates = "D6";

        // when
        cannon.shoot(board, coordinates);
        int afterFirstShotCount = cannon.getCannonballsShot();
        cannon.shoot(board, coordinates);
        cannon.shoot(board, coordinates);
        cannon.shoot(board, coordinates);
        int afterRepeatedShotCount = cannon.getCannonballsShot();

        // then
        assertEquals(afterFirstShotCount, afterRepeatedShotCount);
    }

}