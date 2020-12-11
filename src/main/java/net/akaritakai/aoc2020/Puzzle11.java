package net.akaritakai.aoc2020;

import java.util.Optional;

/**
 * In Day 11, we are tasked with simulating a cellular automaton and recording the results.
 */
public class Puzzle11 extends AbstractPuzzle {
    public Puzzle11(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 11;
    }

    @Override
    public String solvePart1() {
        var ferry = new Ferry(getPuzzleInput());
        Optional<Long> count;
        do {
            count = ferry.round1();
        } while (count.isEmpty());
        return String.valueOf(count.get());
    }

    @Override
    public String solvePart2() {
        var ferry = new Ferry(getPuzzleInput());
        Optional<Long> count;
        do {
            count = ferry.round2();
        } while (count.isEmpty());
        return String.valueOf(count.get());
    }

    private static class Ferry {
        private State[][] seats;
        private final int width;
        private final int height;

        public Ferry(String input) {
            var lines = input.split("\n");
            height = lines.length;
            width = lines[0].length();
            seats = new State[width][height];
            for (var y = 0; y < height; y++) {
                var line = lines[y];
                for (var x = 0; x < width; x++) {
                    var c = line.charAt(x);
                    seats[x][y] = switch (c) {
                        case '.' -> State.FLOOR;
                        case 'L' -> State.EMPTY_SEAT;
                        case '#' -> State.OCCUPIED_SEAT;
                        default -> null;
                    };
                }
            }
        }

        public Optional<Long> round1() {
            var temp = new State[width][height];
            var numChanges = 0;
            for (var x = 0; x < width; x++) {
                for (var y = 0; y < height; y++) {
                    temp[x][y] = switch (seats[x][y]) {
                        case FLOOR -> State.FLOOR;
                        case EMPTY_SEAT -> adjacentOccupiedCount(x, y) == 0 ? State.OCCUPIED_SEAT : State.EMPTY_SEAT;
                        case OCCUPIED_SEAT -> adjacentOccupiedCount(x, y) >= 4 ? State.EMPTY_SEAT : State.OCCUPIED_SEAT;
                    };
                    if (temp[x][y] != seats[x][y]) numChanges++;
                }
            }
            seats = temp;
            return numChanges == 0 ? Optional.of(numOccupiedSeats()) : Optional.empty();
        }

        public Optional<Long> round2() {
            var temp = new State[width][height];
            var numChanges = 0;
            for (var x = 0; x < width; x++) {
                for (var y = 0; y < height; y++) {
                    temp[x][y] = switch (seats[x][y]) {
                        case FLOOR -> State.FLOOR;
                        case EMPTY_SEAT -> visibleOccupiedCount(x, y) == 0 ? State.OCCUPIED_SEAT : State.EMPTY_SEAT;
                        case OCCUPIED_SEAT -> visibleOccupiedCount(x, y) >= 5 ? State.EMPTY_SEAT : State.OCCUPIED_SEAT;
                    };
                    if (temp[x][y] != seats[x][y]) numChanges++;
                }
            }
            seats = temp;
            return numChanges == 0 ? Optional.of(numOccupiedSeats()) : Optional.empty();
        }

        private long numOccupiedSeats() {
            var count = 0;
            for (var x = 0; x < width; x++) {
                for (var y = 0; y < height; y++) {
                    if (seats[x][y] == State.OCCUPIED_SEAT) count++;
                }
            }
            return count;
        }

        private long adjacentOccupiedCount(int x, int y) {
            var count = 0;
            for (var dx = -1; dx <= 1; dx++) {
                for (var dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue;
                    if (hasOccupiedAdjacentSeat(x, y, dx, dy)) count++;
                }
            }
            return count;
        }

        private long visibleOccupiedCount(int x, int y) {
            var count = 0;
            for (var dx = -1; dx <= 1; dx++) {
                for (var dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue;
                    if (hasOccupiedVisibleSeat(x, y, dx, dy)) count++;
                }
            }
            return count;
        }

        boolean hasOccupiedAdjacentSeat(int x, int y, int dx, int dy) {
            x += dx;
            y += dy;
            return inBounds(x, y) && seats[x][y] == State.OCCUPIED_SEAT;
        }

        boolean hasOccupiedVisibleSeat(int x, int y, int dx, int dy) {
            x += dx;
            y += dy;
            while (inBounds(x, y) && seats[x][y] == State.FLOOR) {
                x += dx;
                y += dy;
            }
            return inBounds(x, y) && seats[x][y] == State.OCCUPIED_SEAT;
        }

        private boolean inBounds(int x, int y) {
            return x >= 0 && x < width && y >= 0 && y < height;
        }
    }

    private enum State {
        FLOOR,
        EMPTY_SEAT,
        OCCUPIED_SEAT
    }
}
