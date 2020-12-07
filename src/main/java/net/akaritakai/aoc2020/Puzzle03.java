package net.akaritakai.aoc2020;

public class Puzzle03 extends AbstractPuzzle {
    public Puzzle03(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public String solvePart1() {
        var forest = new Forest(getPuzzleInput());
        return String.valueOf(forest.treesOnSlope(3, 1));
    }

    @Override
    public String solvePart2() {
        var count = 1L;
        var forest = new Forest(getPuzzleInput());
        count *= forest.treesOnSlope(1, 1);
        count *= forest.treesOnSlope(3, 1);
        count *= forest.treesOnSlope(5, 1);
        count *= forest.treesOnSlope(7, 1);
        count *= forest.treesOnSlope(1, 2);
        return String.valueOf(count);
    }

    private static class Forest {
        private final boolean[][] knownTrees;

        public Forest(String input) {
            var lines = input.split("\n");
            var height = lines.length;
            var width = lines[0].length();
            knownTrees = new boolean[width][height];
            for (var y = 0; y < height; y++) {
                for (var x = 0; x < width; x++) {
                    knownTrees[x][y] = lines[y].charAt(x) == '#';
                }
            }
        }

        public int treesOnSlope(int dx, int dy) {
            var count = 0;
            for (int x = 0, y = 0; y < knownTrees[0].length; x += dx, y += dy) {
                if (knownTrees[x % knownTrees.length][y]) {
                    count++;
                }
            }
            return count;
        }
    }
}
