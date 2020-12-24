package net.akaritakai.aoc2020;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * In Day 22, we have a simple recursively-defined game. Critically, we spend a lot of time performing head removal and
 * tail addition (FIFO), so using a queue structure with O(1) additions and removals is critical.
 */
public class Puzzle22 extends AbstractPuzzle {
    public Puzzle22(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 22;
    }

    @Override
    public String solvePart1() {
        var player1 = deck(1);
        var player2 = deck(2);
        while (!player1.isEmpty() && !player2.isEmpty()) {
            var card1 = player1.removeFirst();
            var card2 = player2.removeFirst();
            if (card1 > card2) {
                player1.addLast(card1);
                player1.addLast(card2);
            } else {
                player2.addLast(card2);
                player2.addLast(card1);
            }
        }
        if (player1.isEmpty()) {
            return String.valueOf(score(player2));
        } else {
            return String.valueOf(score(player1));
        }
    }

    @Override
    public String solvePart2() {
        var winningDeck = playGame(deck(1), deck(2));
        return String.valueOf(score(winningDeck));
    }

    @SuppressWarnings("DuplicatedCode")
    private static LinkedList<Integer> playGame(LinkedList<Integer> player1, LinkedList<Integer> player2) {
        var states = new HashSet<DeckState>();
        while (!player1.isEmpty() && !player2.isEmpty()) {
            if (!states.add(new DeckState(List.copyOf(player1), List.copyOf(player2)))) {
                return player1;
            }
            var card1 = player1.removeFirst();
            var card2 = player2.removeFirst();
            if (card1 <= player1.size() && card2 <= player2.size()) {
                if (playSubGame(new LinkedList<>(player1.subList(0, card1)), new LinkedList<>(player2.subList(0, card2)))) {
                    player1.addLast(card1);
                    player1.addLast(card2);
                } else {
                    player2.addLast(card2);
                    player2.addLast(card1);
                }
            } else {
                if (card1 > card2) {
                    player1.addLast(card1);
                    player1.addLast(card2);
                } else {
                    player2.addLast(card2);
                    player2.addLast(card1);
                }
            }
        }
        if (player1.isEmpty()) {
            return player2;
        } else {
            return player1;
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private static boolean playSubGame(LinkedList<Integer> player1, LinkedList<Integer> player2) {
        // During a sub game, if player 1 has the card with the highest number and the value of that card is more than
        // the players' remaining deck size, then player 1 wins.
        var max1 = player1.stream().mapToInt(i -> i).max().orElseThrow();
        var max2 = player2.stream().mapToInt(i -> i).max().orElseThrow();
        if (max1 > max2 && max1 > player1.size() + player2.size()) {
            return true; // Player 1 wins
        }
        var states = new HashSet<DeckState>();
        while (!player1.isEmpty() && !player2.isEmpty()) {
            if (!states.add(new DeckState(List.copyOf(player1), List.copyOf(player2)))) {
                return true; // Player 1 wins
            }
            var card1 = player1.removeFirst();
            var card2 = player2.removeFirst();
            if (card1 <= player1.size() && card2 <= player2.size()) {
                if (playSubGame(new LinkedList<>(player1.subList(0, card1)), new LinkedList<>(player2.subList(0, card2)))) {
                    player1.addLast(card1);
                    player1.addLast(card2);
                } else {
                    player2.addLast(card2);
                    player2.addLast(card1);
                }
            } else {
                if (card1 > card2) {
                    player1.addLast(card1);
                    player1.addLast(card2);
                } else {
                    player2.addLast(card2);
                    player2.addLast(card1);
                }
            }
        }
        return !player1.isEmpty();
    }

    private LinkedList<Integer> deck(int player) {
        var lines = getPuzzleInput().split("\n\n")[player - 1].split("\n");
        var deck = new LinkedList<Integer>();
        for (var i = 1; i < lines.length; i++) {
            deck.add(Integer.parseInt(lines[i]));
        }
        return deck;
    }

    private static int score(LinkedList<Integer> winningDeck) {
        var score = 0;
        var multiplier = 1;
        while (!winningDeck.isEmpty()) {
            score += winningDeck.removeLast() * multiplier++;
        }
        return score;
    }

    private record DeckState(List<Integer> player1, List<Integer> player2) {
    }
}
