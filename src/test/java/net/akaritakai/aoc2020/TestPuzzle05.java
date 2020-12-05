package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle05 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle05("FBFBBFFRLR");
        Assert.assertEquals(puzzle.solvePart1(), "357");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle05("BFFFBBFRRR");
        Assert.assertEquals(puzzle.solvePart1(), "567");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle05("FFFBBBFRRR");
        Assert.assertEquals(puzzle.solvePart1(), "119");
    }

    @Test
    public void testPart1Example4() {
        var puzzle = new Puzzle05("BBFFBBFRLL");
        Assert.assertEquals(puzzle.solvePart1(), "820");
    }

    @Test
    public void testPart1Example5() {
        var puzzle = new Puzzle05("""
                FBFBBFFRLR
                BFFFBBFRRR
                BBFFBBFRLL
                FFFBBBFRRR
                """);
        Assert.assertEquals(puzzle.solvePart1(), "820");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle05(getStoredInput(5));
        Assert.assertEquals(puzzle.solvePart1(), "906");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle05(getStoredInput(5));
        Assert.assertEquals(puzzle.solvePart2(), "519");
    }
}
