package net.akaritakai.aoc2020;

/**
 * An abstract class that represents a solution to an Advent of Code puzzle.
 */
public abstract class AbstractPuzzle {
    private final String _puzzleInput;

    /**
     * Constructor which accepts the puzzle input to be solved.
     * @param puzzleInput the puzzle input
     */
    public AbstractPuzzle(String puzzleInput) {
        _puzzleInput = puzzleInput;
    }

    /**
     * Gets the puzzle input for this day's puzzle.
     * @return the puzzle input for this day's puzzle
     */
    public String getPuzzleInput() {
        return _puzzleInput;
    }

    /**
     * The day number of the puzzle.
     * @return the day number of the puzzle
     */
    public abstract int getDay();

    /**
     * Returns the solution to part 1 of the puzzle.
     * @return the solution to part 1 of the puzzle
     */
    public abstract String solvePart1();

    /**
     * Returns the solution to part 2 of the puzzle.
     * @return the solution to part 2 of the puzzle
     */
    public abstract String solvePart2();
}
