package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle10 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle10("""
                16
                10
                15
                5
                1
                11
                7
                19
                6
                12
                4
                """);
        Assert.assertEquals(puzzle.solvePart1(), String.valueOf(7 * 5));
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle10("""
                28
                33
                18
                42
                31
                14
                46
                20
                48
                47
                24
                23
                49
                45
                19
                38
                39
                11
                1
                32
                25
                35
                8
                17
                7
                9
                4
                2
                34
                10
                3
                """);
        Assert.assertEquals(puzzle.solvePart1(), String.valueOf(22 * 10));
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle10(getStoredInput(10));
        Assert.assertEquals(puzzle.solvePart1(), "2450");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle10("""
                16
                10
                15
                5
                1
                11
                7
                19
                6
                12
                4
                """);
        Assert.assertEquals(puzzle.solvePart2(), "8");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle10("""
                28
                33
                18
                42
                31
                14
                46
                20
                48
                47
                24
                23
                49
                45
                19
                38
                39
                11
                1
                32
                25
                35
                8
                17
                7
                9
                4
                2
                34
                10
                3
                """);
        Assert.assertEquals(puzzle.solvePart2(), "19208");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle10(getStoredInput(10));
        Assert.assertEquals(puzzle.solvePart2(), "32396521357312");
    }
}
