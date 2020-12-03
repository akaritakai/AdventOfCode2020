package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle03 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle03("""
                ..##.......
                #...#...#..
                .#....#..#.
                ..#.#...#.#
                .#...##..#.
                ..#.##.....
                .#.#.#....#
                .#........#
                #.##...#...
                #...##....#
                .#..#...#.#
                """);
        Assert.assertEquals(puzzle.solvePart1(), "7");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle03(getStoredInput(3));
        Assert.assertEquals(puzzle.solvePart1(), "284");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle03("""
                ..##.......
                #...#...#..
                .#....#..#.
                ..#.#...#.#
                .#...##..#.
                ..#.##.....
                .#.#.#....#
                .#........#
                #.##...#...
                #...##....#
                .#..#...#.#
                """);
        Assert.assertEquals(puzzle.solvePart2(), "336");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle03(getStoredInput(3));
        Assert.assertEquals(puzzle.solvePart2(), "3510149120");
    }
}
