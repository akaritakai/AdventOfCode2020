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
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle01(getStoredInput(1));
        Assert.assertEquals(puzzle.solvePart2(), "82498112");
    }
}
