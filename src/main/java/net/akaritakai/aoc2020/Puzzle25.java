package net.akaritakai.aoc2020;

/**
 * In Day 25, we are told to crack a key exchange algorithm.
 *
 * This algorithm is fundamentally a Diffie-Hellman key exchange with modulus p = 20201227 and base g = 7.
 * So, the card's public key 'C' is derived from its private key 'c' as:
 * C = g^c mod p
 * And the door's public key 'D' is derived from its private key 'd' as:
 * D = g^d mod p
 * Where the shared secret is:
 * C^d mod p = g^(cd) mod p = g^(dc) mod p = D^c mod p
 *
 * Solving this quickly is a hard problem (see the discrete logarithm problem), but our input is small enough that a
 * simple brute-force attack suffices.
 */
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
        return "Day 25 has no part 2";
    }
}
