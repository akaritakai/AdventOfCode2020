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
        var seatIds = getPuzzleInput().lines().mapToInt(Puzzle05::seatId).summaryStatistics();
        long min = seatIds.getMin();
        long max = seatIds.getMax();
        return String.valueOf(max * (max + 1) / 2 - (min - 1) * min / 2 - seatIds.getSum());
    }

    private static int seatId(String instructions) {
        return Integer.parseInt(instructions.chars()
                .mapToObj(c -> c == 'B' || c == 'R' ? "1" : "0")
                .collect(Collectors.joining()), 2);
    }
}
