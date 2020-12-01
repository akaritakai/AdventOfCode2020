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
    public void testPart1Example3() {
        // Ensure duplicate entries are handled (alternate order)
        var puzzle = new Puzzle01("""
                1010
                1000
                1010
                """);
        Assert.assertEquals(puzzle.solvePart1(), "1020100");
    }

    @Test
    public void testPart1Example4() {
        // Ensure a size of 2 is handled
        var puzzle = new Puzzle01("""
                1009
                1011
                """);
        Assert.assertEquals(puzzle.solvePart1(), "1020099");
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testPart1Example5() {
        // Ensure a size of 1 fails
        var puzzle = new Puzzle01("""
                2020
                """);
        puzzle.solvePart1();
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testPart1Example6() {
        // Ensure an input that does not satisfy part 1 fails
        var puzzle = new Puzzle01("""
                1010
                1011
                """);
        puzzle.solvePart1();
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testPart1Example7() {
        // Ensure an input that does not satisfy part 1 fails (alternate)
        var puzzle = new Puzzle01("""
                1721
                979
                366
                675
                1456
                """);
        puzzle.solvePart1();
    }


    @Test
    public void testPart1Example8() {
        // Ensure a size of 2 with duplicates are handled
        var puzzle = new Puzzle01("""
                1010
                1010
                """);
        Assert.assertEquals(puzzle.solvePart1(), "1020100");
    }

    @Test
    public void testPart1Example9() {
        // Ensure a pass if the subset is on the extremes of the array
        var puzzle = new Puzzle01("""
                1721
                979
                366
                675
                1456
                299
                """);
        Assert.assertEquals(puzzle.solvePart1(), "514579");
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testPart1Example10() {
        // Ensure an empty input fails
        var puzzle = new Puzzle01("");
        puzzle.solvePart1();
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
    public void testPart2Example3() {
        // Ensure duplicate entries are handled (alternate order)
        var puzzle = new Puzzle01("""
                672
                676
                672
                """);
        Assert.assertEquals(puzzle.solvePart2(), "305270784");
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testPart2Example4() {
        // Ensure a size of 2 fails
        var puzzle = new Puzzle01("""
                1009
                1011
                """);
        puzzle.solvePart2();
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testPart2Example5() {
        // Ensure a size of 1 fails
        var puzzle = new Puzzle01("""
                2020
                """);
        puzzle.solvePart2();
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testPart2Example6() {
        // Ensure an input that does not satisfy part 2 fails
        var puzzle = new Puzzle01("""
                500
                501
                1020
                """);
        puzzle.solvePart2();
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testPart2Example7() {
        // Ensure an input that does not satisfy part 2 fails (alternate)
        var puzzle = new Puzzle01("""
                1721
                979
                366
                299
                1456
                """);
        puzzle.solvePart2();
    }

    @Test
    public void testPart2Example8() {
        // Ensure a size of 4 with duplicates are handled
        var puzzle = new Puzzle01("""
                672
                500
                672
                676
                """);
        Assert.assertEquals(puzzle.solvePart2(), "305270784");
    }

    @Test
    public void testPart2Example9() {
        // Ensure a pass if the subset is on the extremes of the array
        var puzzle = new Puzzle01("""
                979
                1721
                1456
                366
                299
                675
                """);
        Assert.assertEquals(puzzle.solvePart2(), "241861950");
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testPart2Example10() {
        // Ensure an empty input fails
        var puzzle = new Puzzle01("");
        puzzle.solvePart2();
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle01(getStoredInput(1));
        Assert.assertEquals(puzzle.solvePart2(), "82498112");
    }
}
