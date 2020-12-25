package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle25 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle25("""
                5764801
                17807724
                """);
        Assert.assertEquals(puzzle.solvePart1(), "14897079");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle25(getStoredInput(25));
        Assert.assertEquals(puzzle.solvePart1(), "18329280");
    }
}
