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
            var x = 0;
            var y = 0;
            while (y < height()) {
                if (hasTree(x, y)) {
                    count++;
                }
                x += dx;
                y += dy;
            }
            return count;
        }

        private boolean hasTree(int x, int y) {
            return knownTrees[x % width()][y];
        }

        private int height() {
            return knownTrees[0].length;
        }

        private int width() {
            return knownTrees.length;
        }
    }
}
