package net.akaritakai.aoc2020;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Puzzle06 extends AbstractPuzzle {
    public Puzzle06(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 6;
    }

    @Override
    public String solvePart1() {
        var count = Arrays.stream(getPuzzleInput().split("\n\n"))
                .mapToInt(group -> group.lines()
                        .map(person -> person.chars().boxed().collect(Collectors.toSet()))
                        .reduce(Sets::union)
                        .orElse(Set.of())
                        .size())
                .sum();
        return String.valueOf(count);
    }

    @Override
    public String solvePart2() {
        var count = Arrays.stream(getPuzzleInput().split("\n\n"))
                .mapToInt(group -> group.lines()
                        .map(person -> person.chars().boxed().collect(Collectors.toSet()))
                        .reduce(Sets::intersection)
                        .orElse(Set.of())
                        .size())
                .sum();
        return String.valueOf(count);
    }
}
