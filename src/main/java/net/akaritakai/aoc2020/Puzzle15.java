package net.akaritakai.aoc2020;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In Day 15, we are given a Van Eck's sequence with a given prefix and told to compute the nth value.
 *
 * By storing the last seen index in a lookup table, we can find the solution in O(n).
 *
 * Unfortunately, there is no known closed solution to Van Eck's sequence, or other speed-ups that we could take
 * advantage of.
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
        var history = new int[rounds];
        var turn = 1;
        var last = 0;
        for (var n : input) {
            last = n;
            history[last] = turn++;
        }
        while (turn <= rounds) {
            var i = history[last];
            var n = i == 0 ? 0 : turn - i - 1;
            history[last] = turn++ - 1;
            last = n;
        }
        return last;
    }

    private List<Integer> input() {
        return Arrays.stream(getPuzzleInput().trim().split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
