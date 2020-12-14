package net.akaritakai.aoc2020;

import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("UnstableApiUsage")
public class Puzzle13 extends AbstractPuzzle {
    public Puzzle13(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 13;
    }

    @Override
    public String solvePart1() {
        var timestamp = Integer.parseInt(getPuzzleInput().split("\n")[0]);
        var busId = Arrays.stream(getPuzzleInput().split("\n")[1].split(","))
                .map(Ints::tryParse)
                .filter(Objects::nonNull)
                .min(Comparator.comparingInt(id -> id - (timestamp % id)))
                .orElseThrow();
        return String.valueOf((busId - (timestamp % busId)) * busId);
    }

    @Override
    public String solvePart2() {
        var schedule = Arrays.stream(getPuzzleInput().split("\n")[1].split(","))
                .map(Longs::tryParse)
                .collect(Collectors.toList());
        var n = schedule.stream().filter(Objects::nonNull).collect(Collectors.toList());
        var a = IntStream.range(0, schedule.size())
                .filter(i -> schedule.get(i) != null)
                .mapToObj(i -> LongMath.mod(-i, schedule.get(i)))
                .collect(Collectors.toList());
        return String.valueOf(crt(n, a));
    }

    private static long crt(List<Long> n, List<Long> a) {
        long product = n.stream().reduce((x, y) -> x * y).orElseThrow();
        long sum = 0;
        for (var i = 0; i < n.size(); i++) {
            var p = product / n.get(i);
            sum += a.get(i) * BigInteger.valueOf(p).modInverse(BigInteger.valueOf(n.get(i))).longValue() * p;
        }
        return sum % product;
    }
}
