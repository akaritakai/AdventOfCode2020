package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle12 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle12("""
                F10
                N3
                F7
                R90
                F11
                """);
        Assert.assertEquals(puzzle.solvePart1(), "25");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle12(getStoredInput(12));
        Assert.assertEquals(puzzle.solvePart1(), "1589");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle12("""
                F10
                N3
                F7
                R90
                F11
                """);
        Assert.assertEquals(puzzle.solvePart2(), "286");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle12(getStoredInput(12));
        Assert.assertEquals(puzzle.solvePart2(), "23960");
    }
}
