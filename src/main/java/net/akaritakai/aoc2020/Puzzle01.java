package net.akaritakai.aoc2020;

import java.util.function.Function;
import java.util.stream.Collectors;

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
        // This problem is effectively known as "2SUM"
        // The naive algorithm requires O(n^2) but we can do it in O(n):
        // The typical O(n) method is to stash all the numbers in a frequency map and then iterate over the key set (i)
        // and test if 2020-i is also present in the key set.
        // The frequency part is needed so that we can handle duplicates.
        var numbers = getPuzzleInput().lines().map(Long::parseLong)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (var n1 : numbers.keySet()) {
            var n2 = 2020 - n1;
            if (numbers.containsKey(n2)) {
                if (n1 != n2) {
                    return String.valueOf(n1 * n2);
                } else if (numbers.get(n2) >= 2) {
                    return String.valueOf(n1 * n2);
                }
            }
        }
        throw new IllegalStateException("Unable to find the solution");
    }

    @Override
    public String solvePart2() {
        // This problem is effectively known as "3SUM"
        // The naive algorithm requires O(n^3) but we can do it in O(n^2):
        // The typical O(n^2) method is to stash all the numbers in a hash set and then iterate over pairs i, j and test
        // if 2020-i-j is in the hash set. However, that won't work for us as we might have duplicate entries.
        // Instead, we will eat an O(n lg n) sort, and then do O(n^2) comparisons.
        var numbers = getPuzzleInput().lines().map(Long::parseLong).sorted().collect(Collectors.toList());
        for (var i = 0; i < numbers.size() - 2; i++) {
            var n1 = numbers.get(i);
            var j = i + 1;
            var k = numbers.size() - 1;
            while (j < k) {
                var n2 = numbers.get(j);
                var n3 = numbers.get(k);
                if (n1 + n2 + n3 == 2020) {
                    return String.valueOf(n1 * n2 * n3);
                } else if (n1 + n2 + n3 > 2020) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        throw new IllegalStateException("Unable to find the solution");
    }
}
