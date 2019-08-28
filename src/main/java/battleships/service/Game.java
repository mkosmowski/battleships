package battleships.service;

import battleships.gui.Board;
import battleships.model.Cannon;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;
import static java.lang.System.*;

@NoArgsConstructor
public class Game {
    private final Board board = new Board();
    private final Cannon cannon = new Cannon();
    private final Scanner scanner = new Scanner(in);

    private void clearScreen() {
        try {
            String osName = getProperty("os.name").toLowerCase();
            if (osName.matches("windows\\s\\w+")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (osName.matches("linux")) {
                new ProcessBuilder("sh", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ignored) {
        }
    }

    private void printGameStatus() {
        out.print(format("┃ %02d cannonball(s) fired    ", cannon.getCannonballsShot()));
        out.print(format("%d ship(s) destroyed ", (3 - board.countRemainingShips())));
        out.print("┃\n┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }

    private void printPrompt() {
        out.print("   ~(_]===o\n");
        out.print("_/(@)/_      > ");
    }

    public void play() {
        while (true) {
            clearScreen();
            board.printBoard();
            printGameStatus();
            if (board.countRemainingShips() <= 0) {
                endGame();
                break;
            }
            printPrompt();
            boolean shot = cannon.shoot(board, scanner.nextLine().toUpperCase());
            if (!shot) {
                play();
            }
        }
    }

    private void endGame() {
        clearScreen();
        showResult();
    }

    private void showResult() {
        out.println("No more ships to shoot, you have won!");
    }
}