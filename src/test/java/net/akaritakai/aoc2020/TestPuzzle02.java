package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle02 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle02("""
                1-3 a: abcde
                1-3 b: cdefg
                2-9 c: ccccccccc
                """);
        Assert.assertEquals(puzzle.solvePart1(), "2");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle02("""
                1-3 a: abcde
                """);
        Assert.assertEquals(puzzle.solvePart1(), "1");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle02("""
                1-3 b: cdefg
                """);
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testPart1Example4() {
        var puzzle = new Puzzle02("""
                2-9 c: ccccccccc
                """);
        Assert.assertEquals(puzzle.solvePart1(), "1");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle02(getStoredInput(2));
        Assert.assertEquals(puzzle.solvePart1(), "434");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle02("""
                1-3 a: abcde
                1-3 b: cdefg
                2-9 c: ccccccccc
                """);
        Assert.assertEquals(puzzle.solvePart2(), "1");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle02("""
                1-3 a: abcde
                """);
        Assert.assertEquals(puzzle.solvePart2(), "1");
    }

    @Test
    public void testPart2Example3() {
        var puzzle = new Puzzle02("""
                1-3 b: cdefg
                """);
        Assert.assertEquals(puzzle.solvePart2(), "0");
    }

    @Test
    public void testPart2Example4() {
        var puzzle = new Puzzle02("""
                2-9 c: ccccccccc
                """);
        Assert.assertEquals(puzzle.solvePart2(), "0");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle02(getStoredInput(2));
        Assert.assertEquals(puzzle.solvePart2(), "509");
    }
}
