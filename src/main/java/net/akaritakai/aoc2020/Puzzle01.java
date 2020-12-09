package net.akaritakai.aoc2020;

import net.akaritakai.aoc2020.collections.SubsetSum;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * In Day 1, we're tasked with finding a subset of size k from a set of numbers of size n that sum to a given value,
 * and then return the subset's product.
 *
 * Such problems are typically called k-sum problems.
 * For 2-sum and 3-sum (our task) the best known runtimes are O(n) and O(n^2) respectively.
 *
 * A more advanced problem in this category is whether any subset (of any size) from a set of numbers of size n will sum
 * to a given value. This is known as the subset sum problem and is famously NP-complete.
 *
 * Note on bounds: The use of 32-bit signed/unsigned integers is sufficient here as: if the numbers are all positive,
 * there does not exist any tuple {a, b, c} of positive integers such that a+b+c = 2020 and a*b*c > (2^31)-1.
 */
public class Puzzle01 extends AbstractPuzzle {
    public Puzzle01(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public String solvePart1() {
        var numbers = getPuzzleInput().lines().map(Long::parseLong).collect(Collectors.toList());
        var sum = SubsetSum.twoSum(numbers, 2020).stream()
                .flatMap(Collection::stream)
                .reduce((a, b) -> a * b)
                .orElseThrow(() -> new IllegalStateException("Unable to find the solution"));
        return String.valueOf(sum);
    }

    @Override
    public String solvePart2() {
        // 3-SUM:
        // The naive algorithm performs in O(n^3) but we can do it in O(n^2) while handling duplicates:
        // - Sort the numbers (takes O(n lg n) time)
        // - Intelligently test tuples using the ordered property in O(n^2) time
        var numbers = getPuzzleInput().lines().map(Integer::parseInt).sorted().toArray(Integer[]::new);
        for (var i = 0; i < numbers.length - 2; i++) {
            var n1 = numbers[i];
            var j = i + 1;
            var k = numbers.length - 1;
            while (j < k) {
                var n2 = numbers[j];
                var n3 = numbers[k];
                var sum = n1 + n2 + n3;
                if (sum < 2020) {
                    j++;
                } else if (sum > 2020) {
                    k--;
                } else {
                    return String.valueOf(n1 * n2 * n3);
                }
            }
        }
        throw new IllegalStateException("Unable to find the solution");
    }
}
