package net.akaritakai.aoc2020;

import java.util.function.Predicate;

/**
 * In Day 2, we're given a list of predicate/value tuples and are asked how many values match their tuples.
 *
 * Since every predicate/value tuple must be checked, there are no solutions faster than O(n) for either part.
 */
public class Puzzle02 extends AbstractPuzzle {
    public Puzzle02(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public String solvePart1() {
        Predicate<PasswordAndPolicy> matchesPolicy = data -> {
            var count = data.password.chars().filter(i -> i == data.letter).count();
            return count >= data.lower && count <= data.upper;
        };
        return String.valueOf(getPuzzleInput().lines().map(PasswordAndPolicy::parse).filter(matchesPolicy).count());
    }

    @Override
    public String solvePart2() {
        Predicate<PasswordAndPolicy> matchesPolicy = data -> {
            boolean lower = data.lower - 1 < data.password.length() && data.password.charAt(data.lower - 1) == data.letter;
            boolean upper = data.upper - 1 < data.password.length() && data.password.charAt(data.upper - 1) == data.letter;
            return lower ^ upper;
        };
        return String.valueOf(getPuzzleInput().lines().map(PasswordAndPolicy::parse).filter(matchesPolicy).count());
    }

    private record PasswordAndPolicy(int lower, int upper, char letter, String password) {
        private static PasswordAndPolicy parse(String line) {
            var tokens = line.split("\\W+");
            return new PasswordAndPolicy(
                    Integer.parseUnsignedInt(tokens[0]),
                    Integer.parseUnsignedInt(tokens[1]),
                    tokens[2].charAt(0),
                    tokens[3]);
        }
    }
}
