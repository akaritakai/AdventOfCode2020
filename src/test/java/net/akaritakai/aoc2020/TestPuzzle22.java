package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle22 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle22("""
                Player 1:
                9
                2
                6
                3
                1
                                
                Player 2:
                5
                8
                4
                7
                10
                """);
        Assert.assertEquals(puzzle.solvePart1(), "306");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle22(getStoredInput(22));
        Assert.assertEquals(puzzle.solvePart1(), "30197");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle22("""
                Player 1:
                9
                2
                6
                3
                1
                                
                Player 2:
                5
                8
                4
                7
                10
                """);
        Assert.assertEquals(puzzle.solvePart2(), "291");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle22(getStoredInput(22));
        Assert.assertEquals(puzzle.solvePart2(), "34031");
    }
}
