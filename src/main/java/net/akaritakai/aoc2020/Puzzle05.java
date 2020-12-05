package net.akaritakai.aoc2020;

import java.util.stream.Collectors;

public class Puzzle05 extends AbstractPuzzle {
    public Puzzle05(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public String solvePart1() {
        var seatId = getPuzzleInput().lines()
                .mapToInt(Puzzle05::seatId)
                .max()
                .orElseThrow();
        return String.valueOf(seatId);
    }

    @Override
    public String solvePart2() {
        var seatIds = getPuzzleInput().lines()
                .map(Puzzle05::seatId)
                .sorted()
                .collect(Collectors.toList());
        for (var i = 0; i < seatIds.size() - 1; i++) {
            var seatId1 = seatIds.get(i);
            var seatId2 = seatIds.get(i + 1);
            if (seatId1 + 1 != seatId2) {
                return String.valueOf(seatId1 + 1);
            }
        }
        throw new IllegalStateException("Unable to find the solution");
    }

    private static int seatId(String instructions) {
        var minRow = 0;
        var maxRow = 127;
        var minCol = 0;
        var maxCol = 7;
        for (var c : instructions.toCharArray()) {
            switch (c) {
                case 'F' -> maxRow -= (maxRow - minRow) / 2 + 1;
                case 'B' -> minRow += (maxRow - minRow) / 2 + 1;
                case 'L' -> maxCol -= (maxCol - minCol) / 2 + 1;
                case 'R' -> minCol += (maxCol - minCol) / 2 + 1;
            }
        }
        return minRow * 8 + minCol;
    }
}
