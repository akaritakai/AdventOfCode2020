package net.akaritakai.aoc2020.collections;

import java.util.*;

public class SubsetSum {

    /**
     * Finds a subset of size 2 within the given collection that sums to the given value.
     */
    public static Optional<Collection<Long>> twoSum(Collection<Long> numbers, long value) {
        // 2-SUM:
        // The naive algorithm performs in O(n^2) but we can do it in O(n) while handling duplicates:
        // - Initialize an empty set S
        // - For every number n:
        //   - If 2020-n is present in S, our subset is {n, 2020-n}
        //   - Add n to S
        var seen = new HashSet<Long>();
        for (var n1 : numbers) {
            var n2 = value - n1;
            if (seen.contains(n2)) {
                return Optional.of(List.of(n1, n2));
            }
            seen.add(n1);
        }
        return Optional.empty();
    }
}
