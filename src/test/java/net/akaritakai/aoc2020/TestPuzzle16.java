package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle16 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle16("""
                class: 1-3 or 5-7
                row: 6-11 or 33-44
                seat: 13-40 or 45-50
                                
                your ticket:
                7,1,14
                                
                nearby tickets:
                7,3,47
                40,4,50
                55,2,20
                38,6,12
                """);
        Assert.assertEquals(puzzle.solvePart1(), "71");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle16(getStoredInput(16));
        Assert.assertEquals(puzzle.solvePart1(), "28873");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle16(getStoredInput(16));
        Assert.assertEquals(puzzle.solvePart2(), "2587271823407");
    }
}
