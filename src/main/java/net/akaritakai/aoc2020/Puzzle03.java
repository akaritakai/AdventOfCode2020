package net.akaritakai.aoc2020;

/**
 * In Day 3, we are given the definition of a forest: a set of non-negative integer tuples (x, y) whose y value (height)
 * is bounded and whose x value (width) is unbounded.
 *
 * We are then given a boolean function f(x, y) for our 'instance' of the forest which tells us if a tree is present at
 * that location. Explicitly we are given finite numbers 0..n of x for every y and told that f(n+1, y) == f(0, y),
 * f(n+2, y) == f(1, y), etc.
 *
 * Indeed, this problem is our first this year that takes advantage of modular arithmetic:
 * For all i, f(i, y) = f(i modulo n, y).
 */
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

    private record Forest(String[] lines) {
        public Forest(String input) {
            this(input.split("\n"));
        }

        public int treesOnSlope(int dx, int dy) {
            var count = 0;
            for (int x = 0, y = 0; y < lines.length; x += dx, y += dy) {
                if (lines[y].charAt(x % lines[y].length()) == '#') {
                    count++;
                }
            }
            return count;
        }
    }
}
