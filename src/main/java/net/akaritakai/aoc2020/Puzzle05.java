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
        return String.valueOf(getPuzzleInput().lines().mapToInt(Puzzle05::seatId).max().orElseThrow());
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
        var seat_id = 0;
        for (var c : instructions.toCharArray()) {
            seat_id <<= 1;
            if (c == 'B' || c == 'R') {
                seat_id |= 1;
            }
        }
        return seat_id;
    }
}
