package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle01 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle01("""
                1721
                979
                366
                299
                675
                1456
                """);
        Assert.assertEquals(puzzle.solvePart1(), "514579");
    }

    @Test
    public void testPart1Example2() {
        // Ensure duplicate entries are handled
        var puzzle = new Puzzle01("""
                1000
                1010
                1010
                """);
        Assert.assertEquals(puzzle.solvePart1(), "1020100");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle01(getStoredInput(1));
        Assert.assertEquals(puzzle.solvePart1(), "440979");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle01("""
                1721
                979
                366
                299
                675
                1456
                """);
        Assert.assertEquals(puzzle.solvePart2(), "241861950");
    }

    @Test
    public void testPart2Example2() {
        // Ensure duplicate entries are handled
        var puzzle = new Puzzle01("""
                672
                672
                676
                """);
        Assert.assertEquals(puzzle.solvePart2(), "305270784");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle01(getStoredInput(1));
        Assert.assertEquals(puzzle.solvePart2(), "82498112");
    }
}
