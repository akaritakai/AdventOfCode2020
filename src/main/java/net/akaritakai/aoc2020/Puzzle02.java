package net.akaritakai.aoc2020;

import java.util.function.Predicate;

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
        var matching = getPuzzleInput().lines().map(PasswordAndPolicy::parse).filter(matchesPolicy).count();
        return String.valueOf(matching);
    }

    @Override
    public String solvePart2() {
        Predicate<PasswordAndPolicy> matchesPolicy = data -> {
            boolean lower = data.lower - 1 < data.password.length() && data.password.charAt(data.lower - 1) == data.letter;
            boolean upper = data.upper - 1 < data.password.length() && data.password.charAt(data.upper - 1) == data.letter;
            return lower ^ upper;
        };
        var matching = getPuzzleInput().lines().map(PasswordAndPolicy::parse).filter(matchesPolicy).count();
        return String.valueOf(matching);
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
