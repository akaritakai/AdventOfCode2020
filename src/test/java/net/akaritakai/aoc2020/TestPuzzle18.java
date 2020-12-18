package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle18 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle18("1 + 2 * 3 + 4 * 5 + 6");
        Assert.assertEquals(puzzle.solvePart1(), "71");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle18("1 + (2 * 3) + (4 * (5 + 6))");
        Assert.assertEquals(puzzle.solvePart1(), "51");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle18("2 * 3 + (4 * 5)");
        Assert.assertEquals(puzzle.solvePart1(), "26");
    }

    @Test
    public void testPart1Example4() {
        var puzzle = new Puzzle18("5 + (8 * 3 + 9 + 3 * 4 * 3)");
        Assert.assertEquals(puzzle.solvePart1(), "437");
    }

    @Test
    public void testPart1Example5() {
        var puzzle = new Puzzle18(" 5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        Assert.assertEquals(puzzle.solvePart1(), "12240");
    }

    @Test
    public void testPart1Example6() {
        var puzzle = new Puzzle18("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        Assert.assertEquals(puzzle.solvePart1(), "13632");
    }

    @Test
    public void testPart1Example7() {
        var puzzle = new Puzzle18("""
                2 * 3 + (4 * 5)
                5 + (8 * 3 + 9 + 3 * 4 * 3)
                5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))
                ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2
                """);
        Assert.assertEquals(puzzle.solvePart1(), String.valueOf(26 + 437 + 12240 + 13632));
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle18(getStoredInput(18));
        Assert.assertEquals(puzzle.solvePart1(), "5019432542701");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle18("1 + 2 * 3 + 4 * 5 + 6");
        Assert.assertEquals(puzzle.solvePart2(), "231");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle18("1 + (2 * 3) + (4 * (5 + 6))");
        Assert.assertEquals(puzzle.solvePart2(), "51");
    }

    @Test
    public void testPart2Example3() {
        var puzzle = new Puzzle18("2 * 3 + (4 * 5)");
        Assert.assertEquals(puzzle.solvePart2(), "46");
    }

    @Test
    public void testPart2Example4() {
        var puzzle = new Puzzle18("5 + (8 * 3 + 9 + 3 * 4 * 3)");
        Assert.assertEquals(puzzle.solvePart2(), "1445");
    }

    @Test
    public void testPart2Example5() {
        var puzzle = new Puzzle18("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        Assert.assertEquals(puzzle.solvePart2(), "669060");
    }

    @Test
    public void testPart2Example6() {
        var puzzle = new Puzzle18("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        Assert.assertEquals(puzzle.solvePart2(), "23340");
    }

    @Test
    public void testPart2Example7() {
        var puzzle = new Puzzle18("""
                1 + (2 * 3) + (4 * (5 + 6))
                2 * 3 + (4 * 5)
                5 + (8 * 3 + 9 + 3 * 4 * 3)
                5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))
                ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2
                """);
        Assert.assertEquals(puzzle.solvePart2(), String.valueOf(51 + 46 + 1445 + 669060 + 23340));
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle18(getStoredInput(18));
        Assert.assertEquals(puzzle.solvePart2(), "70518821989947");
    }
}
