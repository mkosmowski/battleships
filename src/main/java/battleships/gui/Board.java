package battleships.gui;

import battleships.model.*;
import lombok.Getter;

import java.util.*;

import static battleships.model.Orientation.VERTICAL;
import static battleships.model.ShipFactory.buildShip;
import static battleships.model.ShipType.BATTLESHIP;
import static battleships.model.ShipType.DESTROYER;
import static battleships.model.State.DAMAGED;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

public class Board {
    @Getter
    private final HashMap<String, Field> fields = new HashMap<>();
    @Getter
    private final List<String> columns = asList("A B C D E F G H I J".split(" "));
    private final int BOARD_COLUMNS = columns.size();
    @Getter
    private final int BOARD_ROWS = 10;
    private final List<Ship> ships = new ArrayList<>();

    public Board() {
        prepareBoard();
    }

    private void prepareBoard() {
        fillBoardWithWater();
        arrangeShips();
    }

    private void fillBoardWithWater() {
        for (int i = 1; i <= BOARD_ROWS; ++i) {
            for (int j = 0; j < BOARD_COLUMNS; ++j) {
                fields.put(getMapCoordinatesEntry(j, i), new Water());
            }
        }
    }

    private void arrangeShips() {
        while (ships.size() < 1) {
            placeShip(BATTLESHIP);
        }

        while (ships.size() < 3) {
            placeShip(DESTROYER);
        }
    }

    private void placeShip(ShipType shipType) {
        boolean okToPlaceShip;
        List<Field> adjacentFields = new ArrayList<>();
        List<Field> shipFields = new ArrayList<>();

        Ship ship = buildShip(shipType);
        Pair<HashSet<Pair>> coordinates = getRandomShipCoordinates(ship);
        try {
            coordinates.getE1().forEach(
                    p -> shipFields.add(fields.get(getMapCoordinatesEntry((Integer) p.getE2(), (Integer) p.getE1())))
            );
            coordinates.getE2().forEach(
                    p -> adjacentFields.add(fields.get(getMapCoordinatesEntry((Integer) p.getE2(), (Integer) p.getE1())))
            );
            okToPlaceShip = validateCoordinates(shipFields, adjacentFields);
            if (okToPlaceShip) {
                coordinates.getE1().forEach(
                        p -> fields.put(getMapCoordinatesEntry((Integer) p.getE2(), (Integer) p.getE1()), ship.getNextPart())
                );
                ship.restoreLength();
                ships.add(ship);
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            placeShip(shipType);
        }
    }

    private String getMapCoordinatesEntry(int column, int row) {
        return (columns.get(column) + row);
    }

    private boolean validateCoordinates(List<Field> shipFields, List<Field> adjacentFields) {
        boolean allShipFieldsAreWater = shipFields.stream()
                .allMatch(p -> p.getClass().getSimpleName().equals("Water"));
        boolean allAdjacentFieldsAreEmpty = adjacentFields.stream()
                .noneMatch(p -> p.getClass().getSimpleName().equals("ShipPart"));
        return (allShipFieldsAreWater && allAdjacentFieldsAreEmpty);
    }

    public int countRemainingShips() {
        removeSunkenShips();
        return ships.size();
    }

    private void removeSunkenShips() {
        ships.removeIf(ship -> ship.getShipParts().stream()
                .allMatch(p -> p.getState().equals(DAMAGED)));
    }

    public void printBoard() {
        out.print("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        printColumnLetters();
        out.print("┣━━━━━╋━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n");
        printChart();
        out.print("┣━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n");
    }

    private void printColumnLetters() {
        out.print("\n┃  ⎈  ┃ ");
        rangeClosed(0, BOARD_COLUMNS - 1).forEach(i ->
                out.print(" " + columns.get(i) + "  ")
        );
        out.print("┃\n");
    }

    private void printChart() {
        for (int i = 1; i <= BOARD_ROWS; ++i) {
            out.print(format("┃  %2d ┃ ", i));
            for (int j = 0; j < BOARD_COLUMNS; ++j) {
                out.print(fields.get(getMapCoordinatesEntry(j, i)).getSymbol() + " ");
            }
            out.print("┃ \n");
        }
    }

    private Pair<HashSet<Pair>> getRandomShipCoordinates(Ship ship) {
        HashSet<Pair> shipFields = new HashSet<>();
        HashSet<Pair> adjacentFields = new HashSet<>();
        int shipLength = ship.getShipParts().size();
        Orientation shipOrientation = ship.getOrientation();

        generateAdjacentAndShipFields(shipFields, adjacentFields, shipLength, shipOrientation);

        return new Pair<>(shipFields, adjacentFields);
    }

    private void generateAdjacentAndShipFields(HashSet<Pair> shipFields, HashSet<Pair> adjacentFields, int shipLength, Orientation shipOrientation) {
        Random generator = new Random();
        int x = generator.nextInt(BOARD_ROWS) + 1;
        int y = generator.nextInt(BOARD_COLUMNS) + 1;

        if (VERTICAL.equals(shipOrientation)) {
            getFieldsForVerticalShip(shipFields, adjacentFields, shipLength, x, y);
        } else {
            getFieldsForHorizontalShip(shipFields, adjacentFields, shipLength, x, y);
        }
    }

    private void getFieldsForVerticalShip(HashSet<Pair> shipFields, HashSet<Pair> adjacentFields, int shipLength, int x, int y) {
        List<Pair> adjacentFieldsPairs = asList(new Pair<>(x - 1, y - 1), new Pair<>(x - 1, y), new Pair<>(x - 1, y + 1),
                new Pair<>(x + shipLength, y - 1), new Pair<>(x + shipLength, y), new Pair<>(x + shipLength, y + 1));
        adjacentFields.addAll(adjacentFieldsPairs);
        range(0, shipLength).forEach(i -> {
            adjacentFields.add(new Pair<>(x + i, y - 1));
            adjacentFields.add(new Pair<>(x + i, y + 1));
            shipFields.add(new Pair<>(x + i, y));
        });
    }

    private void getFieldsForHorizontalShip(HashSet<Pair> shipFields, HashSet<Pair> adjacentFields, int shipLength, int x, int y) {
        List<Pair> adjacentFieldsPairs = asList(new Pair<>(x - 1, y - 1), new Pair<>(x, y - 1), new Pair<>(x + 1, y - 1),
                new Pair<>(x - 1, y + shipLength), new Pair<>(x, y + shipLength), new Pair<>(x + 1, y + shipLength));
        adjacentFields.addAll(adjacentFieldsPairs);
        range(0, shipLength).forEach(i -> {
            adjacentFields.add(new Pair<>(x - 1, y + i));
            adjacentFields.add(new Pair<>(x + 1, y + i));
            shipFields.add(new Pair<>(x, y + i));
        });
    }
}
