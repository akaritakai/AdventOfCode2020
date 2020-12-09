package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Test
public class TestPuzzle09 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var numbers = LongStream.rangeClosed(1, 25).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        numbers.add(26L);
        Assert.assertFalse(Puzzle09.isInvalidNumber(numbers, 25, 25));
    }

    @Test
    public void testPart1Example2() {
        var numbers = LongStream.rangeClosed(1, 25).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        numbers.add(49L);
        Assert.assertFalse(Puzzle09.isInvalidNumber(numbers, 25, 25));
    }

    @Test
    public void testPart1Example3() {
        var numbers = LongStream.rangeClosed(1, 25).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        numbers.add(100L);
        Assert.assertTrue(Puzzle09.isInvalidNumber(numbers, 25, 25));
    }

    @Test
    public void testPart1Example4() {
        var numbers = LongStream.rangeClosed(1, 25).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        numbers.add(50L);
        Assert.assertTrue(Puzzle09.isInvalidNumber(numbers, 25, 25));
    }

    @Test
    public void testPart1Example5() {
        var numbers = new LinkedList<Long>();
        LongStream.rangeClosed(1, 19).forEach(numbers::add);
        LongStream.rangeClosed(21, 25).forEach(numbers::add);
        Collections.shuffle(numbers);
        numbers.addFirst(20L);
        numbers.addLast(45L);
        numbers.add(26L);
        Assert.assertFalse(Puzzle09.isInvalidNumber(numbers, 25, 26));
    }

    @Test
    public void testPart1Example6() {
        var numbers = new LinkedList<Long>();
        LongStream.rangeClosed(1, 19).forEach(numbers::add);
        LongStream.rangeClosed(21, 25).forEach(numbers::add);
        Collections.shuffle(numbers);
        numbers.addFirst(20L);
        numbers.addLast(45L);
        numbers.add(65L);
        Assert.assertTrue(Puzzle09.isInvalidNumber(numbers, 25, 26));
    }

    @Test
    public void testPart1Example7() {
        var numbers = new LinkedList<Long>();
        LongStream.rangeClosed(1, 19).forEach(numbers::add);
        LongStream.rangeClosed(21, 25).forEach(numbers::add);
        Collections.shuffle(numbers);
        numbers.addFirst(20L);
        numbers.addLast(45L);
        numbers.add(64L);
        Assert.assertFalse(Puzzle09.isInvalidNumber(numbers, 25, 26));
    }

    @Test
    public void testPart1Example8() {
        var numbers = new LinkedList<Long>();
        LongStream.rangeClosed(1, 19).forEach(numbers::add);
        LongStream.rangeClosed(21, 25).forEach(numbers::add);
        Collections.shuffle(numbers);
        numbers.addFirst(20L);
        numbers.addLast(45L);
        numbers.add(66L);
        Assert.assertFalse(Puzzle09.isInvalidNumber(numbers, 25, 26));
    }

    public void testPart1Example9() {
        var numbers = List.of(35L, 20L, 15L, 25L, 47L, 40L, 62L, 55L, 65L, 95L, 102L, 117L, 150L, 182L, 127L,
                219L, 299L, 277L, 309L, 576L);
        Assert.assertEquals(Puzzle09.findFirstInvalidNumber(numbers, 5).orElseThrow(), 127L);
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle09(getStoredInput(9));
        Assert.assertEquals(puzzle.solvePart1(), "373803594");
    }

    @Test
    public void testPart2Example1() {
        var numbers = List.of(35L, 20L, 15L, 25L, 47L, 40L, 62L, 55L, 65L, 95L, 102L, 117L, 150L, 182L, 127L,
                219L, 299L, 277L, 309L, 576L);
        var contiguousRange = Puzzle09.findContiguousRange(numbers, 127L);
        Assert.assertEquals(contiguousRange, List.of(15L, 25L, 47L, 40L));
        var summary = contiguousRange.stream().mapToLong(i -> i).summaryStatistics();
        Assert.assertEquals(summary.getMin(), 15L);
        Assert.assertEquals(summary.getMax(), 47L);
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle09(getStoredInput(9));
        Assert.assertEquals(puzzle.solvePart2(), "51152360");
    }
}
