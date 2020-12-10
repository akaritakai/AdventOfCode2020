package net.akaritakai.aoc2020;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * In Day 10, we're given a set of values and told to find paths that connect the values from a source (0) to a sink
 * (3 more than the largest value given) if they can only be +1, +2, or +3 values apart.
 *
 * In part 1, we're asked to find the path using all the adapters and then perform an O(n) computation over that path.
 * The easiest method to find the path is to simply sort the list as our path will always involve monotonically
 * increasing numbers. This is typically done using an O(n lg n) algorithm, however our input has an unusual property:
 * since we are assured that our inputs cannot have numbers more than 3 apart, our inputs contain at least a third of
 * all numbers from source to sink. This density allows us to solve the problem in O(n) by doing the following:
 * - Store the values in a hash set in O(n)
 * - iterate from source to sink (approximately O(n)):
 *   - if the value is in the set, it's the next part of our path
 *
 * In part 2, we are tasked with finding the total number of paths. This is typically a recursive problem, however we
 * note that for worst case inputs (all consecutive numbers) that our runtime is O(3^n). Yikes! Luckily, we have a
 * solution: when we apply dynamic programming to our recursion, this runtime falls to O(n).
 */
public class Puzzle10 extends AbstractPuzzle {
    public Puzzle10(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 10;
    }

    @Override
    public String solvePart1() {
        // Build our sorted list in O(n) by storing all the numbers in a hash set in O(n) time and iterating over all
        // integers from min to max which is approximately n due to the density.
        var set = getPuzzleInput().lines().map(Integer::parseInt).collect(Collectors.toSet());
        var max = set.stream().max(Integer::compare).orElseThrow();
        var ones = 0;
        var threes = 0;
        var last = 0;
        for (var i = 1; i <= max; i++) {
            if (set.contains(i)) {
                if (i - last == 1) {
                    ones++;
                } else if (i - last == 3) {
                    threes++;
                }
                last = i;
            }
        }
        return String.valueOf(ones * (threes + 1));
    }

    @Override
    public String solvePart2() {
        // Get our input as a set of adapters and get that set's max value in O(n)
        var adapters = getPuzzleInput().lines().map(Integer::parseInt).collect(Collectors.toSet());
        var device = adapters.stream().max(Integer::compare).orElseThrow() + 3;
        // Count number of paths recursively using dynamic programming
        return String.valueOf(countPaths(adapters, new HashMap<>(), 0, device));
    }

    private static long countPaths(Set<Integer> adapters, Map<Integer, Long> memo, int current, int target) {
        if (current == target) {
            return 1;
        } else if (memo.containsKey(current)) {
            return memo.get(current);
        }

        var count = 0L;
        if (current + 1 == target || adapters.contains(current + 1)) {
            count += countPaths(adapters, memo, current + 1, target);
        }
        if (current + 2 == target || adapters.contains(current + 2)) {
            count += countPaths(adapters, memo, current + 2, target);
        }
        if (current + 3 == target || adapters.contains(current + 3)) {
            count += countPaths(adapters, memo, current + 3, target);
        }
        memo.put(current, count);
        return count;
    }
}
