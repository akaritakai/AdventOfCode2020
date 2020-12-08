package net.akaritakai.aoc2020;

import com.google.common.math.IntMath;

import java.util.stream.Collectors;

/**
 * In Day 5, we are given what appears to be at first blush a binary search problem. We are given a fixed range and told
 * to subdivide it repeatedly by taking either the left or right half of the range until we are left with a single
 * value.
 *
 * On further examination however, we can see that the input itself builds a number: taking the upper half yields a 1,
 * and taking the lower half yields a '0'. Converting the instructions in this way, the binary number we generate is
 * exactly the seat id we are asked for!
 *
 * In part 1, we only wish to find the maximum value of the list of seat ids we are given, which cannot be done faster
 * than O(n) as we must check every element in the list.
 *
 * In part 2, we are asked to find the missing number in a list that contains all values from [min, max] except for the
 * number we need to find. The naive way to do this is to sort the list and iterate over it in O(n lg n) time, but it
 * can be done in O(n) time (and O(1) auxiliary space) instead:
 * - Read the list of seat ids and record the min value, max value, and total sum (requires an O(n) pass)
 * - Calculate the triangular numbers T(min - 1) and T(max) in O(1) using T(x) = binomial(x+1, 2) = x*(x+1)/2
 * - We know that if no discontinuities exist that the total sum would be T(max) - T(min - 1).
 *   Thus, the difference between T(max) - T(min - 1) and our actual sum is the missing value.
 */
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
        var idealSum = IntMath.binomial(seatIds.getMax() + 1, 2) - IntMath.binomial(seatIds.getMin(), 2);
        return String.valueOf(idealSum - seatIds.getSum());
    }

    private static int seatId(String instructions) {
        return Integer.parseInt(instructions.chars()
                .mapToObj(c -> c == 'B' || c == 'R' ? "1" : "0")
                .collect(Collectors.joining()), 2);
    }
}
