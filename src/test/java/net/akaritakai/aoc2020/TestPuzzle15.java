package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle15 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle15("0,3,6");
        Assert.assertEquals(puzzle.solvePart1(), "436");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle15("1,3,2");
        Assert.assertEquals(puzzle.solvePart1(), "1");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle15("2,1,3");
        Assert.assertEquals(puzzle.solvePart1(), "10");
    }

    @Test
    public void testPart1Example4() {
        var puzzle = new Puzzle15("1,2,3");
        Assert.assertEquals(puzzle.solvePart1(), "27");
    }

    @Test
    public void testPart1Example5() {
        var puzzle = new Puzzle15("2,3,1");
        Assert.assertEquals(puzzle.solvePart1(), "78");
    }

    @Test
    public void testPart1Example6() {
        var puzzle = new Puzzle15("3,2,1");
        Assert.assertEquals(puzzle.solvePart1(), "438");
    }

    @Test
    public void testPart1Example7() {
        var puzzle = new Puzzle15("3,1,2");
        Assert.assertEquals(puzzle.solvePart1(), "1836");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle15(getStoredInput(15));
        Assert.assertEquals(puzzle.solvePart1(), "1522");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle15("0,3,6");
        Assert.assertEquals(puzzle.solvePart2(), "175594");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle15("1,3,2");
        Assert.assertEquals(puzzle.solvePart2(), "2578");
    }

    @Test
    public void testPart2Example3() {
        var puzzle = new Puzzle15("2,1,3");
        Assert.assertEquals(puzzle.solvePart2(), "3544142");
    }

    @Test
    public void testPart2Example4() {
        var puzzle = new Puzzle15("1,2,3");
        Assert.assertEquals(puzzle.solvePart2(), "261214");
    }

    @Test
    public void testPart2Example5() {
        var puzzle = new Puzzle15("2,3,1");
        Assert.assertEquals(puzzle.solvePart2(), "6895259");
    }

    @Test
    public void testPart2Example6() {
        var puzzle = new Puzzle15("3,2,1");
        Assert.assertEquals(puzzle.solvePart2(), "18");
    }

    @Test
    public void testPart2Example7() {
        var puzzle = new Puzzle15("3,1,2");
        Assert.assertEquals(puzzle.solvePart2(), "362");
    }


    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle15(getStoredInput(15));
        Assert.assertEquals(puzzle.solvePart2(), "18234");
    }
}
