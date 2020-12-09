package net.akaritakai.aoc2020;

import com.google.common.annotations.VisibleForTesting;
import net.akaritakai.aoc2020.collections.SubsetSum;

import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * In Day 9, we're tasked with some more subset sum operations.
 *
 * In part 1, we want to find whether a set of size k has a 2-sum for n-k elements in our list of numbers. In Day 1,
 * we learned that 2-sum can be done in O(n) time. Doing this over all our specified elements, we get a runtime of
 * O(n^2).
 *
 * In part 2, we take the value we found in part 1 and try to find a contiguous range within our set that sums to that
 * value. If the numbers we had were positive and negative the fastest solution would be O(n^2) and look like this:
 * - Create a list of partial sums of elements of the list [0, 0+1, 0+1+2, ..., 0+1+2+...+n]
 * - Iterate i,j over the list of sums and find the pair sum[j] - sum[i] that equal our value
 * However, the input gives us only positive numbers, thus we can perform it in O(n):
 * - start with i=0 and j=1 and a running sum that starts as i+j
 * - if the running sum is less than our value, increment j and add it to our sum
 * - if the running sum is greater than our value, subtract i from our sum and increment i
 * - if at any point i=j, increment j and add it to our sum
 */
public class Puzzle09 extends AbstractPuzzle {
    public Puzzle09(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 9;
    }

    @Override
    public String solvePart1() {
        return String.valueOf(findFirstInvalidNumber(numbers(), 25).orElseThrow());
    }

    @Override
    public String solvePart2() {
        var numbers = numbers();
        var invalidNumber = findFirstInvalidNumber(numbers, 25).orElseThrow();
        var contiguousRange = findContiguousRange(numbers, invalidNumber);
        var summary = contiguousRange.stream().mapToLong(i -> i).summaryStatistics();
        return String.valueOf(summary.getMin() + summary.getMax());
    }

    @VisibleForTesting
    static List<Long> findContiguousRange(List<Long> numbers, long invalidNumber) {
        var low = 0;
        var high = 1;
        var sum = numbers.get(low) + numbers.get(high);
        while (sum != invalidNumber) {
            if (sum < invalidNumber) {
                sum += numbers.get(++high);
            } else {
                sum -= (numbers.get(low++));
                if (low == high) {
                    sum += numbers.get(++high);
                }
            }
        }
        return numbers.subList(low, high + 1);
    }

    @VisibleForTesting
    static OptionalLong findFirstInvalidNumber(List<Long> numbers, int preambleSize) {
        return IntStream.range(preambleSize, numbers.size())
                .filter(i -> isInvalidNumber(numbers, preambleSize, i))
                .mapToLong(numbers::get)
                .findFirst();
    }

    static boolean isInvalidNumber(List<Long> numbers, int preambleSize, int position) {
        return SubsetSum.twoSum(numbers.subList(position - preambleSize, position), numbers.get(position)).isEmpty();
    }

    private List<Long> numbers() {
        return getPuzzleInput().lines().map(Long::parseLong).collect(Collectors.toList());
    }
}
