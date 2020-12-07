package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle07 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle07("""
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.
                """);
        Assert.assertEquals(puzzle.solvePart1(), "4");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle07(getStoredInput(7));
        Assert.assertEquals(puzzle.solvePart1(), "124");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle07("""
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.
                """);
        Assert.assertEquals(puzzle.solvePart2(), "32");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle07("""
                shiny gold bags contain 2 dark red bags.
                dark red bags contain 2 dark orange bags.
                dark orange bags contain 2 dark yellow bags.
                dark yellow bags contain 2 dark green bags.
                dark green bags contain 2 dark blue bags.
                dark blue bags contain 2 dark violet bags.
                dark violet bags contain no other bags.
                """);
        Assert.assertEquals(puzzle.solvePart2(), "126");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle07(getStoredInput(7));
        Assert.assertEquals(puzzle.solvePart2(), "34862");
    }
}
