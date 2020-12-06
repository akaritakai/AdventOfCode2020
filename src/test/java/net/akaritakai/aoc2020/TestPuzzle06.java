package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle06 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle06("""
                abcx
                abcy
                abcz
                """);
        Assert.assertEquals(puzzle.solvePart1(), "6");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle06("""
                abc
                                
                a
                b
                c
                                
                ab
                ac
                                
                a
                a
                a
                a
                                
                b
                """);
        Assert.assertEquals(puzzle.solvePart1(), "11");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle06(getStoredInput(6));
        Assert.assertEquals(puzzle.solvePart1(), "6259");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle06("""
                abc
                                
                a
                b
                c
                                
                ab
                ac
                                
                a
                a
                a
                a
                                
                b
                """);
        Assert.assertEquals(puzzle.solvePart2(), "6");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle06(getStoredInput(6));
        Assert.assertEquals(puzzle.solvePart2(), "3178");
    }
}
