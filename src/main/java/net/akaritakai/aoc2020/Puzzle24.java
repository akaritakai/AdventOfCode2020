package net.akaritakai.aoc2020;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * In Day 24, we are given a problem that uses hexagonal tiling and then requires a cellular automata simulation like
 * Day 17.
 */
public class Puzzle24 extends AbstractPuzzle {
    public Puzzle24(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 24;
    }

    @Override
    public String solvePart1() {
        var blackTiles = new HashSet<HexagonalPoint>();
        for (var line : getPuzzleInput().split("\n")) {
            var directions = directions(line);
            var point = move(directions);
            if (!blackTiles.add(point)) {
                blackTiles.remove(point);
            }
        }
        return String.valueOf(blackTiles.size());
    }

    @Override
    public String solvePart2() {
        Set<HexagonalPoint> blackTiles = new HashSet<>();
        for (var line : getPuzzleInput().split("\n")) {
            var directions = directions(line);
            var point = move(directions);
            if (!blackTiles.add(point)) {
                blackTiles.remove(point);
            }
        }
        for (var i = 0; i < 100; i++) {
            blackTiles = day(blackTiles);
        }
        return String.valueOf(blackTiles.size());
    }

    private static Set<HexagonalPoint> day(Set<HexagonalPoint> current) {
        var set = new HashSet<HexagonalPoint>();

        // Black tile rules
        current.forEach(point -> {
            var adjacent = point.adjacent();
            var count = adjacent.stream().filter(current::contains).count();
            if (count == 1 || count == 2) {
                set.add(point);
            }
        });

        // White tile rules
        current.stream()
                .flatMap(p -> p.adjacent().stream())
                .filter(p -> !current.contains(p))
                .forEach(point -> {
                    var adjacent = point.adjacent();
                    var count = adjacent.stream().filter(current::contains).count();
                    if (count == 2) {
                        set.add(point);
                    }
                });

        return set;
    }

    private static HexagonalPoint move(List<HexagonalDirection> directions) {
        var point = new HexagonalPoint(0, 0);
        for (var direction : directions) {
            point = direction.move(point);
        }
        return point;
    }

    private static List<HexagonalDirection> directions(String s) {
        var directions = new ArrayList<HexagonalDirection>();
        for (var i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'e' -> directions.add(HexagonalDirection.EAST);
                case 'w' -> directions.add(HexagonalDirection.WEST);
                case 's' -> {
                    switch (s.charAt(++i)) {
                        case 'e' -> directions.add(HexagonalDirection.SOUTH_EAST);
                        case 'w' -> directions.add(HexagonalDirection.SOUTH_WEST);
                    }
                }
                case 'n' -> {
                    switch (s.charAt(++i)) {
                        case 'e' -> directions.add(HexagonalDirection.NORTH_EAST);
                        case 'w' -> directions.add(HexagonalDirection.NORTH_WEST);
                    }
                }
            }
        }
        return directions;
    }

    private record HexagonalPoint(long x, long y) {
        private Set<HexagonalPoint> adjacent() {
            return Set.of(
                    HexagonalDirection.EAST.move(this),
                    HexagonalDirection.SOUTH_EAST.move(this),
                    HexagonalDirection.SOUTH_WEST.move(this),
                    HexagonalDirection.WEST.move(this),
                    HexagonalDirection.NORTH_WEST.move(this),
                    HexagonalDirection.NORTH_EAST.move(this));
        }
    }

    private enum HexagonalDirection {
        EAST (1, -1),
        SOUTH_EAST (1, 0),
        SOUTH_WEST (0, 1),
        WEST (-1, 1),
        NORTH_WEST (-1, 0),
        NORTH_EAST (0, -1);

        private final long x;
        private final long y;

        HexagonalDirection(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public HexagonalPoint move(HexagonalPoint other) {
            return new HexagonalPoint(other.x + x, other.y + y);
        }
    }
}
