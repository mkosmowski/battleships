package battleships.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class GameTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream outContent;

    private String createInputBruteForcePattern() {
        StringBuilder pattern = new StringBuilder();
        for (char c = 'A'; c <= 'J'; c++) {
            for (int r = 1; r <= 10; r++) {
                pattern.append(c).append(r).append("\n");
            }
        }
        return pattern.toString();
    }

    private void setupInputOutput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreInputOutput() {
        System.setOut(originalOut);
    }

    @Test
    public void gameShouldEndWithWinningMessage() {
        // given
        String testInput = createInputBruteForcePattern();
        setupInputOutput(testInput);
        String winningMessage = "No more ships to shoot, you have won!\n";
        Game game = new Game();

        // when
        game.play();

        // then
        Assert.assertTrue(outContent.toString().endsWith(winningMessage));
    }
}