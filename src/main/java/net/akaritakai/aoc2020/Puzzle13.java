package net.akaritakai.aoc2020;

import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * In Day 13, we are given a bus schedule and told that each bus runs endlessly, and completes its route and returns to
 * our location in the same number of minutes as its id (e.g. a bus with id 5 completes its route in 5 minutes).
 *
 * In part 1, we are given a time and asked to find what the next bus we can take is. This is simply minimizing
 * id - (time % id) across all the buses we are given.
 *
 * Part 2 gets a bit trickier: we are asked to find the earliest time where the buses sync up in a certain way: what is
 * the first time 't' with the following property: the first bus on the schedule departs at time t, the second bus
 * departs at time 't+1', the third bus departs at time 't+2', etc.
 *
 * Another way to describe this problem is to say that we are looking for the least integer t that satisfies the
 * following:
 * (t + 0) % bus1 == 0
 * (t + 1) % bus2 == 0
 * (t + 2) % bus3 == 0
 * etc.
 * Luckily for us, our input has the buses all coprime to each other (it is left as an exercise to the reader to see how
 * restrictive the schedule would be if they were not coprime!). Thus, we can take advantage of a famous number theory
 * trick called the Chinese remainder theorem which finds the least x for:
 * x == a_1 % n_1
 * x == a_2 % n_2
 * x == a_3 % n_3
 * etc.
 * if n_1,n_2,n_3,... are all coprime.
 * Framing the problem in terms of a constant x gives us:
 * x ==    t % bus1
 * x == (-1) % bus2
 * x == (-2) % bus3
 * etc.
 * allowing us to use the trick.
 */
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
        return String.valueOf(chineseRemainderTheorem(n, a));
    }

    private static long chineseRemainderTheorem(List<Long> n, List<Long> a) {
        long product = n.stream().reduce((x, y) -> x * y).orElseThrow();
        long sum = 0;
        for (var i = 0; i < n.size(); i++) {
            var p = product / n.get(i);
            sum += a.get(i) * BigInteger.valueOf(p).modInverse(BigInteger.valueOf(n.get(i))).longValue() * p;
        }
        return sum % product;
    }
}
