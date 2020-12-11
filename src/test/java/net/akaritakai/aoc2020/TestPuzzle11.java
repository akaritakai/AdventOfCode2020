package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle11 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle11("""
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL
                """);
        Assert.assertEquals(puzzle.solvePart1(), "37");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle11(getStoredInput(11));
        Assert.assertEquals(puzzle.solvePart1(), "2354");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle11("""
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL
                """);
        Assert.assertEquals(puzzle.solvePart2(), "26");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle11(getStoredInput(11));
        Assert.assertEquals(puzzle.solvePart2(), "2072");
    }
}
