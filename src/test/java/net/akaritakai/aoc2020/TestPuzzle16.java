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
    public void testPart2Example1() {
        /*
         * This puzzle is one of my own design. It is "harder" than the puzzle that we are given as an input.
         */
        var puzzle = new Puzzle16("""
                departure location: 134-135 or 161-182
                departure station: 94-95 or 143-184
                departure platform: 141-165 or 166-202
                departure track: 125-209 or 240-241
                departure date: 71-86 or 126-213
                departure time: 70-92 or 115-217
                arrival location: 10-176 or 177-255
                arrival station: 11-100 or 101-256
                arrival platform: 12-196 or 197-257
                arrival track: 13-171 or 172-258
                class: 14-174 or 175-259
                duration: 15-102 or 103-260
                price: 16-104 or 105-261
                route: 17-106 or 107-262
                row: 18-235 or 236-263
                seat: 19-232 or 233-264
                train: 20-57 or 58-265
                type: 21-178 or 179-266
                wagon: 22-185 or 186-267
                zone: 23-188 or 189-287
                                
                your ticket:
                179,144,141,203,71,87,23,26,24,25,246,27,30,31,32,33,93,219,34,269
                                
                nearby tickets:
                161,143,142,126,72,88,24,25,26,27,28,58,29,32,33,34,96,218,35,268
                162,145,189,127,73,89,25,27,28,29,30,59,31,33,34,35,233,220,36,270
                163,146,190,128,74,90,26,28,29,30,42,60,32,34,35,36,107,221,37,271
                164,147,185,129,210,91,218,29,39,31,32,61,33,35,36,37,234,222,38,272
                165,148,186,130,211,92,219,30,31,32,33,62,220,36,37,38,235,223,39,273
                166,149,187,131,75,115,27,31,32,33,41,63,34,37,42,39,242,224,40,274
                177,150,188,132,212,116,28,32,40,218,43,64,44,47,45,46,97,225,107,275
                167,151,191,133,76,117,29,33,41,219,44,220,45,48,49,50,98,226,108,276
                168,152,192,136,82,118,30,34,42,242,45,65,46,49,50,243,99,227,109,277
                169,153,193,204,77,119,220,221,43,243,46,222,47,50,51,52,100,228,110,278
                170,154,194,137,78,120,221,242,107,34,47,223,48,51,52,53,108,229,111,279
                171,155,195,138,79,121,222,35,44,45,48,66,49,52,53,54,109,230,242,280
                172,156,196,139,80,122,223,233,45,46,49,67,50,53,54,55,110,231,243,281
                173,157,197,140,81,123,224,234,46,47,236,68,225,54,55,56,111,232,244,282
                174,158,198,205,83,124,236,237,47,48,238,69,242,55,56,57,112,243,245,283
                178,159,199,206,84,214,237,101,48,49,239,242,243,102,244,58,113,245,246,284
                180,160,200,207,85,215,242,36,243,50,244,245,246,107,247,96,114,248,249,285
                181,183,201,208,86,216,243,37,49,51,245,246,247,108,248,109,249,250,251,287
                182,184,202,209,213,217,244,96,245,246,247,248,249,250,251,252,253,254,255,286
                """);
        Assert.assertEquals(puzzle.solvePart2(), "4557306889296");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle16(getStoredInput(16));
        Assert.assertEquals(puzzle.solvePart2(), "2587271823407");
    }
}
