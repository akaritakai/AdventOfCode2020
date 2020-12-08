package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle08 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle08("""
                nop +0
                acc +1
                jmp +4
                acc +3
                jmp -3
                acc -99
                acc +1
                jmp -4
                acc +6
                """);
        Assert.assertEquals(puzzle.solvePart1(), "5");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle08(getStoredInput(8));
        Assert.assertEquals(puzzle.solvePart1(), "1810");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle08("""
                nop +0
                acc +1
                jmp +4
                acc +3
                jmp -3
                acc -99
                acc +1
                jmp -4
                acc +6
                """);
        Assert.assertEquals(puzzle.solvePart2(), "8");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle08(getStoredInput(8));
        Assert.assertEquals(puzzle.solvePart2(), "969");
    }
}
