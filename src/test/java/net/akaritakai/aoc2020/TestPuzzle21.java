package net.akaritakai.aoc2020;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle21 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle21("""
                mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                trh fvjkl sbzzf mxmxvkd (contains dairy)
                sqjhc fvjkl (contains soy)
                sqjhc mxmxvkd sbzzf (contains fish)
                """);
        Assert.assertEquals(puzzle.solvePart1(), "5");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle21(getStoredInput(21));
        Assert.assertEquals(puzzle.solvePart1(), "2410");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle21("""
                mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                trh fvjkl sbzzf mxmxvkd (contains dairy)
                sqjhc fvjkl (contains soy)
                sqjhc mxmxvkd sbzzf (contains fish)
                """);
        Assert.assertEquals(puzzle.solvePart2(), "mxmxvkd,sqjhc,fvjkl");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle21(getStoredInput(21));
        Assert.assertEquals(puzzle.solvePart2(), "tmp,pdpgm,cdslv,zrvtg,ttkn,mkpmkx,vxzpfp,flnhl");
    }
}
