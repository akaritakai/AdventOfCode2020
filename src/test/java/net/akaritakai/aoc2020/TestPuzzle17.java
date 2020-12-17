package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle17 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle17("""
                .#.
                ..#
                ###
                """);
        Assert.assertEquals(puzzle.solvePart1(), "112");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle17(getStoredInput(17));
        Assert.assertEquals(puzzle.solvePart1(), "232");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle17("""
                .#.
                ..#
                ###
                """);
        Assert.assertEquals(puzzle.solvePart2(), "848");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle17(getStoredInput(17));
        Assert.assertEquals(puzzle.solvePart2(), "1620");
    }
}
