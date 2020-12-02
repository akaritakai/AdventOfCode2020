package net.akaritakai.aoc2020;

import java.util.function.Predicate;
import java.util.regex.Pattern;

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
        var matching = getPuzzleInput().lines().map(Puzzle02::parse).filter(matchesPolicy).count();
        return String.valueOf(matching);
    }

    @Override
    public String solvePart2() {
        Predicate<PasswordAndPolicy> matchesPolicy = data -> {
            boolean lower = data.lower - 1 < data.password.length() && data.password.charAt(data.lower - 1) == data.letter;
            boolean upper = data.upper - 1 < data.password.length() && data.password.charAt(data.upper - 1) == data.letter;
            return lower ^ upper;
        };
        var matching = getPuzzleInput().lines().map(Puzzle02::parse).filter(matchesPolicy).count();
        return String.valueOf(matching);
    }

    private static final Pattern LINE_PATTEN = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");

    private static PasswordAndPolicy parse(String line) {
        var matcher = LINE_PATTEN.matcher(line);
        if (matcher.find()) {
            var lower = Integer.parseUnsignedInt(matcher.group(1));
            var upper = Integer.parseUnsignedInt(matcher.group(2));
            var letter = matcher.group(3).charAt(0);
            var password = matcher.group(4);
            return new PasswordAndPolicy(lower, upper, letter, password);
        }
        throw new IllegalArgumentException("Unable to parse line: " + line);
    }

    private record PasswordAndPolicy(int lower, int upper, char letter, String password) {
    }
}
