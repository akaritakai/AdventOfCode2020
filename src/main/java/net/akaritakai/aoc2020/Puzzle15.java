package net.akaritakai.aoc2020;

import java.util.Arrays;
import java.util.HashMap;
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
        var history = new HashMap<Integer, Integer[]>();
        var turn = 1;
        var lastNumber = 0;
        for (var n : input) {
            lastNumber = n;
            updateIndexes(history.computeIfAbsent(lastNumber, s -> new Integer[2]), turn++);
        }
        while (turn <= rounds) {
            var indexes = history.computeIfAbsent(lastNumber, s -> new Integer[2]);
            lastNumber = indexes[0] == null || indexes[1] == null ? 0 : indexes[1] - indexes[0];
            updateIndexes(history.computeIfAbsent(lastNumber, s -> new Integer[2]), turn++);
        }
        return lastNumber;
    }

    private List<Integer> input() {
        return Arrays.stream(getPuzzleInput().trim().split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static void updateIndexes(Integer[] history, int value) {
        if (history[0] == null) {
            history[0] = value;
        } else if (history[1] == null) {
            history[1] = value;
        } else {
            history[0] = history[1];
            history[1] = value;
        }
    }
}
