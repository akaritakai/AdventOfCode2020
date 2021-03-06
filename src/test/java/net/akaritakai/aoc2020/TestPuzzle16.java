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
    public void testPart2Example2() {
        /*
         * This puzzle created in:
         * https://www.reddit.com/r/adventofcode/comments/kf8mlu/2020_day_16_part_3_a_different_number_system/
         *
         * It is even harder than the above example.
         */
        var puzzle = new Puzzle16("""
                departure location: 621-925 or 928-995
                departure station: 634-728 or 809-984
                departure platform: 458-467 or 541-990
                departure track: 457-512 or 525-994
                departure date: 623-763 or 808-986
                departure time: 637-717 or 817-978
                arrival location: 15-35 or 40-998
                arrival station: 289-318 or 390-991
                arrival platform: 792-798 or 807-976
                arrival track: 681-689 or 828-963
                class: 702-714 or 827-962
                duration: 813-877 or 887-974
                price: 826-901 or 921-961
                route: 237-249 or 814-977
                row: 232-300 or 391-993
                seat: 10-572 or 573-999
                train: 234-316 or 393-996
                type: 416-927 or 960-992
                wagon: 34-588 or 589-997
                zone: 816-871 or 872-975
                                
                your ticket:
                848,898,872,965,818,972,822,638,635,628,802,543,525,454,513,439,519,357,388,60
                                
                nearby tickets:
                900,887,828,973,972,819,818,637,634,752,988,620,530,518,456,435,444,383,356,377
                925,899,891,967,820,818,965,661,979,730,781,467,473,453,425,523,423,42,66,35
                828,921,896,974,817,966,971,668,719,756,769,460,485,516,519,517,518,385,384,362
                961,829,831,824,966,821,820,701,728,624,767,542,538,438,524,442,523,384,229,319
                901,841,836,968,974,817,819,717,726,734,621,461,536,425,426,429,441,358,87,364
                829,874,897,822,823,967,968,716,984,762,803,566,479,440,514,426,427,374,363,110
                849,850,830,966,967,965,973,658,723,625,768,466,529,439,523,514,422,328,130,997
                897,833,890,972,821,969,825,696,982,627,770,616,512,450,447,446,456,77,45,92
                876,869,877,819,970,973,974,680,724,631,805,458,537,423,518,416,420,74,357,335
                865,831,860,820,971,964,972,670,727,626,806,583,472,417,517,437,521,95,135,222
                833,840,894,825,965,820,817,978,725,623,801,459,531,519,454,515,417,45,997,206
                830,901,899,969,964,825,823,655,983,985,772,463,540,448,516,518,517,91,386,34
                896,961,901,971,822,824,964,672,636,633,987,465,471,515,455,524,522,997,389,109
                960,897,961,817,973,823,967,694,980,729,622,599,510,514,521,418,434,230,359,366
                887,896,960,821,969,968,970,663,722,986,990,541,483,418,423,513,429,126,196,117
                834,960,874,970,824,822,969,639,721,731,787,553,480,437,429,449,455,226,102,194
                888,839,870,823,819,970,966,640,718,744,784,569,470,455,450,448,451,127,226,94
                832,867,921,964,825,971,824,669,720,632,989,611,535,431,446,445,520,370,106,354
                831,828,848,818,968,974,821,697,981,629,780,608,528,435,451,450,524,231,370,230
                """);
        Assert.assertEquals(puzzle.solvePart2(), "58168457716446000");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle16(getStoredInput(16));
        Assert.assertEquals(puzzle.solvePart2(), "2587271823407");
    }
}
