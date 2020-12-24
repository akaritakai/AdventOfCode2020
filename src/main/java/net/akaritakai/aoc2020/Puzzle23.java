package net.akaritakai.aoc2020;

/**
 * In Day 23, we have another game where the choice of data structure is important. This game involves being able to
 * have insertions and removals in the middle of a list take O(1), and thus the structure is perfect for a linked list.
 *
 * We can save additional memory by using the input's density to hold references to the next node in an array rather
 * than in custom node objects.
 */
public class Puzzle23 extends AbstractPuzzle {
    public Puzzle23(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 23;
    }

    @Override
    public String solvePart1() {
        var cups = cups(9);
        game(cups, 100);
        var cup = 1;
        var sb = new StringBuilder();
        for (var i = 0; i < 8; i++) {
            sb.append(cups[cup]);
            cup = cups[cup];
        }
        return sb.toString();
    }

    @Override
    public String solvePart2() {
        var cups = cups(1_000_000);
        game(cups, 10_000_000);
        return String.valueOf((long) cups[1] * (long) cups[cups[1]]);
    }

    private void game(int[] cups, int moves) {
        int cup = Integer.parseInt(getPuzzleInput().trim().substring(0, 1));
        for (var i = 0; i < moves; i++) {
            var cup1 = cups[cup];
            var cup2 = cups[cup1];
            var cup3 = cups[cup2];

            var dest = cup;
            do {
                dest--;
                if (dest == 0) {
                    dest = cups.length - 1;
                }
            } while (dest == cup1 || dest == cup2 || dest == cup3);

            cups[cup] = cups[cup3];
            var temp = cups[dest];
            cups[dest] = cup1;
            cups[cup3] = temp;
            cup = cups[cup];
        }
    }

    private int[] cups(int numCups) {
        var cups = new int[numCups + 1];
        var input = getPuzzleInput().trim();
        for (var i = 0; i < input.length() - 1; i++) {
            cups[Integer.parseInt(input.substring(i, i + 1))] = Integer.parseInt(input.substring(i + 1, i + 2));
        }
        var i = Integer.parseInt(input.substring(input.length() - 1));
        if (input.length() == numCups) {
            cups[i] = Integer.parseInt(input.substring(0, 1));
        } else {
            cups[i] = input.length() + 1;
            for (i = input.length() + 1; i < numCups; i++) {
                cups[i] = i + 1;
            }
            cups[numCups] = Integer.parseInt(input.substring(0, 1));
        }
        return cups;
    }
}
