package battleships.gui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    private final Board board = new Board();
    private PrintStream originalOut;
    private ByteArrayOutputStream outContent;

    @Before
    public void setupOutput() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreOutput() {
        System.setOut(originalOut);
    }

    @Test
    public void boardIsPrintedCorrectly() {
        // given
        String expectedBoard =
                        "┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                        "┃  ⎈  ┃  A   B   C   D   E   F   G   H   I   J  ┃\n" +
                        "┣━━━━━╋━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n" +
                        "┃   1 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┃   2 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┃   3 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┃   4 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┃   5 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┃   6 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┃   7 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┃   8 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┃   9 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┃  10 ┃ [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] ┃ \n" +
                        "┣━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n";

        // when
        board.printBoard();

        //then
        assertEquals(expectedBoard, outContent.toString());
    }

    @Test
    public void boardHas100Fields() {
        // given
        int expectedFieldsCount = 100;

        // when
        int actualFieldsCount = board.getFields().size();

        // then
        assertEquals(expectedFieldsCount, actualFieldsCount);
    }

    @Test
    public void initialShipCountIs3() {
        // given
        int expectedInitialShipCount = 3;

        // when
        int initialShipCount = board.countRemainingShips();

        // then
        assertEquals(expectedInitialShipCount, initialShipCount);
    }

    @Test
    public void boardHas10Columns() {
        // given
        int expectedColumnsCount = 10;

        // when
        int actualColumnsCount = board.getColumns().size();

        // then
        assertEquals(expectedColumnsCount, actualColumnsCount);
    }

    @Test
    public void boardHas10Rows() {
        // given
        int expectedRowsCount = 10;

        // when
        int actualRowsCount = board.getBOARD_ROWS();

        // then
        assertEquals(expectedRowsCount, actualRowsCount);
    }
}