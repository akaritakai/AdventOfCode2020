package net.akaritakai.aoc2020;

import net.akaritakai.aoc2020.Puzzle04.Passport;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle04 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle04("""
                ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
                byr:1937 iyr:2017 cid:147 hgt:183cm
                                
                iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
                hcl:#cfa07d byr:1929
                                
                hcl:#ae17e1 iyr:2013
                eyr:2024
                ecl:brn pid:760753108 byr:1931
                hgt:179cm
                                
                hcl:#cfa07d eyr:2025 pid:166559648
                iyr:2011 ecl:brn hgt:59in
                """);
        Assert.assertEquals(puzzle.solvePart1(), "2");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle04("""
                ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
                byr:1937 iyr:2017 cid:147 hgt:183cm
                """);
        Assert.assertEquals(puzzle.solvePart1(), "1");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle04("""
                iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
                hcl:#cfa07d byr:1929
                """);
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testPart1Example4() {
        var puzzle = new Puzzle04("""
                hcl:#ae17e1 iyr:2013
                eyr:2024
                ecl:brn pid:760753108 byr:1931
                hgt:179cm
                """);
        Assert.assertEquals(puzzle.solvePart1(), "1");
    }

    @Test
    public void testPart1Example5() {
        var puzzle = new Puzzle04("""
                hcl:#cfa07d eyr:2025 pid:166559648
                iyr:2011 ecl:brn hgt:59in
                """);
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle04(getStoredInput(4));
        Assert.assertEquals(puzzle.solvePart1(), "250");
    }

    @Test
    public void testPart2Example1() {
        Assert.assertTrue(Passport.isValueValid("byr", "2002"));
        Assert.assertFalse(Passport.isValueValid("byr", "2003"));
        Assert.assertTrue(Passport.isValueValid("hgt", "60in"));
        Assert.assertTrue(Passport.isValueValid("hgt", "190cm"));
        Assert.assertFalse(Passport.isValueValid("hgt", "190in"));
        Assert.assertFalse(Passport.isValueValid("hgt", "190"));
        Assert.assertTrue(Passport.isValueValid("hcl", "#123abc"));
        Assert.assertFalse(Passport.isValueValid("hcl", "#123abz"));
        Assert.assertFalse(Passport.isValueValid("hcl", "123abc"));
        Assert.assertTrue(Passport.isValueValid("ecl", "brn"));
        Assert.assertFalse(Passport.isValueValid("ecl", "wat"));
        Assert.assertTrue(Passport.isValueValid("pid", "000000001"));
        Assert.assertFalse(Passport.isValueValid("pid", "0123456789"));
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle04("""
                eyr:1972 cid:100
                hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926
                                
                iyr:2019
                hcl:#602927 eyr:1967 hgt:170cm
                ecl:grn pid:012533040 byr:1946
                                
                hcl:dab227 iyr:2012
                ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277
                                
                hgt:59cm ecl:zzz
                eyr:2038 hcl:74454a iyr:2023
                pid:3556412378 byr:2007
                """);
        Assert.assertEquals(puzzle.solvePart2(), "0");
    }

    @Test
    public void testPart2Example3() {
        var puzzle = new Puzzle04("""
                pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
                hcl:#623a2f
                                
                eyr:2029 ecl:blu cid:129 byr:1989
                iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm
                                
                hcl:#888785
                hgt:164cm byr:2001 iyr:2015 cid:88
                pid:545766238 ecl:hzl
                eyr:2022
                                
                iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
                """);
        Assert.assertEquals(puzzle.solvePart2(), "4");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle04(getStoredInput(4));
        Assert.assertEquals(puzzle.solvePart2(), "158");
    }
}
