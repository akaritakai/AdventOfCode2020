package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle23 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle23("389125467");
        Assert.assertEquals(puzzle.solvePart1(), "67384529");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle23(getStoredInput(23));
        Assert.assertEquals(puzzle.solvePart1(), "45983627");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle23("389125467");
        Assert.assertEquals(puzzle.solvePart2(), "149245887792");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle23(getStoredInput(23));
        Assert.assertEquals(puzzle.solvePart2(), "111080192688");
    }
}
