package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle14 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle14("""
                mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                mem[8] = 11
                mem[7] = 101
                mem[8] = 0
                """);
        Assert.assertEquals(puzzle.solvePart1(), "165");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle14(getStoredInput(14));
        Assert.assertEquals(puzzle.solvePart1(), "5902420735773");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle14("""
                mask = 000000000000000000000000000000X1001X
                mem[42] = 100
                mask = 00000000000000000000000000000000X0XX
                mem[26] = 1
                """);
        Assert.assertEquals(puzzle.solvePart2(), "208");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle14(getStoredInput(14));
        Assert.assertEquals(puzzle.solvePart2(), "3801988250775");
    }
}
