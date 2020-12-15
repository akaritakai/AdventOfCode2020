package net.akaritakai.aoc2020;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In Day 15, we are given a Van Eck's sequence with a given prefix and told to compute the nth value.
 *
 * I am not sure there are any speed-ups to be found here.
 */
public class Puzzle15 extends AbstractPuzzle {
    public Puzzle15(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 15;
    }

    @Override
    public String solvePart1() {
        return String.valueOf(playGame(input(), 2020));
    }

    @Override
    public String solvePart2() {
        return String.valueOf(playGame(input(), 30_000_000));
    }

    private static int playGame(List<Integer> input, int rounds) {
        var history = new Integer[rounds];
        var turn = 1;
        var lastNumber = 0;
        for (var n : input) {
            lastNumber = n;
            history[lastNumber] = turn++;
        }
        while (turn <= rounds) {
            var index = history[lastNumber];
            var n = index == null ? 0 : turn - index - 1;
            history[lastNumber] = turn++ - 1;
            lastNumber = n;
        }
        return lastNumber;
    }

    private List<Integer> input() {
        return Arrays.stream(getPuzzleInput().trim().split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
