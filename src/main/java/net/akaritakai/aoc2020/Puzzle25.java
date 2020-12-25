package net.akaritakai.aoc2020;

public class Puzzle25 extends AbstractPuzzle {
    public Puzzle25(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 25;
    }

    @Override
    public String solvePart1() {
        var cardKey = Long.parseLong(getPuzzleInput().split("\n")[0]);
        var doorKey = Long.parseLong(getPuzzleInput().split("\n")[1]);
        var loopSize = 0L;
        var value = 1L;
        while (value != cardKey) {
            value = (7 * value) % 20201227;
            loopSize++;
        }
        var key = 1L;
        for (var i = 0; i < loopSize; i++) {
            key = (doorKey * key) % 20201227;

        }
        return String.valueOf(key);
    }

    @Override
    public String solvePart2() {
        return "Day 25 has not part 2";
    }
}
