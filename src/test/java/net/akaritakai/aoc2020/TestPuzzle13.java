package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle13 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle13("""
                939
                7,13,x,x,59,x,31,19
                """);
        Assert.assertEquals(puzzle.solvePart1(), "295");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle13(getStoredInput(13));
        Assert.assertEquals(puzzle.solvePart1(), "3789");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle13("""
                939
                7,13,x,x,59,x,31,19
                """);
        Assert.assertEquals(puzzle.solvePart2(), "1068781");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle13("""
                0
                17,x,13,19
                """);
        Assert.assertEquals(puzzle.solvePart2(), "3417");
    }

    @Test
    public void testPart2Example3() {
        var puzzle = new Puzzle13("""
                0
                67,7,59,61
                """);
        Assert.assertEquals(puzzle.solvePart2(), "754018");
    }

    @Test
    public void testPart2Example4() {
        var puzzle = new Puzzle13("""
                0
                67,x,7,59,61
                """);
        Assert.assertEquals(puzzle.solvePart2(), "779210");
    }

    @Test
    public void testPart2Example5() {
        var puzzle = new Puzzle13("""
                0
                67,7,x,59,61
                """);
        Assert.assertEquals(puzzle.solvePart2(), "1261476");
    }

    @Test
    public void testPart2Example6() {
        var puzzle = new Puzzle13("""
                0
                1789,37,47,1889
                """);
        Assert.assertEquals(puzzle.solvePart2(), "1202161486");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle13(getStoredInput(13));
        Assert.assertEquals(puzzle.solvePart2(), "667437230788118");
    }
}
