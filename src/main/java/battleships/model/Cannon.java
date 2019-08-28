package battleships.model;

import battleships.gui.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

@NoArgsConstructor
public class Cannon {
    private static final Pattern shootCoordinatesPattern = Pattern.compile("^([A-J])(\\d{1,2})$");
    @Getter
    private int cannonballsShot = 0;

    private static boolean coordinatesAreValid(Board board, String hitCoordinates) {
        Matcher coords = shootCoordinatesPattern.matcher(hitCoordinates);
        return (coords.find() && coords.groupCount() == 2
                && board.getColumns().contains(coords.group(1))
                && (parseInt(coords.group(2)) >= 0 && parseInt(coords.group(2)) <= board.getBOARD_ROWS()));
    }

    public boolean shoot(Board board, String coordinates) {
        if (coordinatesAreValid(board, coordinates)) {
            if (board.getFields().get(coordinates).isVisible()) {
                return false;
            } else {
                board.getFields().get(coordinates).hit();
                cannonballsShot++;
                return true;
            }
        } else {
            return false;
        }
    }
}