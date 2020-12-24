package net.akaritakai.aoc2020;

import java.util.*;
import java.util.regex.Pattern;

public class Puzzle19 extends AbstractPuzzle {
    public Puzzle19(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 19;
    }

    @Override
    public String solvePart1() {
        return String.valueOf(numMatching(getPuzzleInput()));
    }

    @Override
    public String solvePart2() {
        return String.valueOf(numMatching(getPuzzleInput()
                .replace("8: 42", "8: 42 | 42 8")
                .replace("11: 42 31", "11: 42 31 | 42 11 31")));
    }

    private long numMatching(String grammarString) {
        var grammar = new CnfGrammar(new Grammar(grammarString));
        return getPuzzleInput().lines()
                .parallel()
                .filter(line -> line.matches("^[a-b]+$"))
                .filter(line -> new Cyk(grammar, line).inGrammar())
                .count();
    }

    /**
     * Class which implements the CYK algorithm.
     */
    private static class Cyk {
        private final Set<String>[][] cyk;
        private final int size;

        public Cyk(CnfGrammar grammar, String inputString) {
            var rules = grammar.reverseRules;
            var input = tokenize(inputString);
            size = input.size();
            //noinspection unchecked
            cyk = new Set[size][size];

            for (var x = 0; x < size; x++) {
                cyk(0, x).addAll(rules.get(input.subList(x, x + 1)));
            }
            for (var y = 1; y < size; y++) {
                for (var x = 0; x < size - y; x++) {
                    for (var i = 0; i < y; i++) {
                        for (var n1 : cyk(i, x)) {
                            for (var n2 : cyk(y-i-1, x+i+1)) {
                                for (var rule : rules.getOrDefault(List.of(n1, n2), Collections.emptySet())) {
                                    cyk(y, x).add(rule);
                                }
                            }
                        }
                    }
                }
            }
        }

        private Set<String> cyk(int y, int x) {
            if (cyk[y][x] == null) {
                cyk[y][x] = new HashSet<>();
            }
            return cyk[y][x];
        }

        private boolean inGrammar() {
            return cyk[size - 1][0] != null && cyk[size - 1][0].contains(CnfGrammar.START_SYMBOL);
        }
    }

    private static class CnfGrammar {
        private static final String START_SYMBOL = "START"; // The symbol "START" doesn't appear in our grammar
        private final Map<String, Set<List<String>>> rules = new HashMap<>();
        private final Map<List<String>, Set<String>> reverseRules = new HashMap<>();

        public CnfGrammar(Grammar grammar) {
            rules.putAll(grammar.rules);
            startStep();
            // TERM step (eliminate rules with non-solitary terminals) not needed in our grammar
            binStep();
            // DEL step (eliminate empty string rules) not needed in our grammar
            unitStep();
            createReverseRules();
        }

        private void startStep() {
            // START: Eliminate the start symbol from right-hand sides
            // In this case, our start symbol is "0"
            rules.computeIfAbsent(START_SYMBOL, s -> new HashSet<>()).add(Collections.singletonList("0"));
        }

        private void binStep() {
            // BIN: Eliminate right-hand sides with more than 2 non-terminals
            // Note: Our grammar's terminals are in right-hand sides with length of 1, so we do not have to exclude them
            for (var rule : Set.copyOf(rules.entrySet())) {
                var lhs = rule.getKey();
                var rhsValues = rule.getValue();
                for (var rhs : Set.copyOf(rhsValues)) {
                    if (rhs.size() > 2) {
                        rules.get(lhs).remove(rhs);
                        binStep(lhs, rhs);
                    }
                }
            }
        }

        private void binStep(String lhs, List<String> rhs) {
            if (rhs.size() <= 2) {
                rules.computeIfAbsent(lhs, s -> new HashSet<>()).add(rhs);
            } else {
                var nextRhs = rhs.subList(1, rhs.size());
                var nextLhs = String.join(",", nextRhs); // "," is not in our grammar
                binStep(lhs, List.of(rhs.get(0), nextLhs));
                binStep(nextLhs, nextRhs);
            }
        }

        private void unitStep() {
            // UNIT: Eliminate non-terminal unit rules
            var numMutations = 0;
            for (var rule : Set.copyOf(rules.entrySet())) {
                var lhs = rule.getKey();
                var rhsValues = rule.getValue();
                if (rhsValues.size() == 0) {
                    rules.remove(lhs);
                    numMutations++;
                } else {
                    for (var rhs : Set.copyOf(rhsValues)) {
                        if (rhs.size() == 0) {
                            rules.get(lhs).remove(rhs);
                            numMutations++;
                        } else if (rhs.size() == 1 && !isTerminal(rhs)) {
                            rules.get(lhs).remove(rhs);
                            rules.get(lhs).addAll(rules.get(rhs.get(0)));
                            numMutations++;
                        }
                    }
                }
            }
            if (numMutations > 0) {
                unitStep();
            }
        }

        private void createReverseRules() {
            rules.forEach((k, values) ->
                    values.forEach(v ->
                            reverseRules.computeIfAbsent(v, s -> new HashSet<>()).add(k)));
        }

        private boolean isTerminal(List<String> symbols) {
            // Terminal characters in our grammar are "a" or "b"
            return symbols.stream().allMatch(symbol -> symbol.equals("a") || symbol.equals("b"));
        }
    }

    /**
     * Class representing a CFG grammar as parsed from our rules.
     */
    private static class Grammar {
        private final Map<String, Set<List<String>>> rules = new HashMap<>();

        public Grammar(String inputString) {
            for (var line : inputString.split("\n")) {
                generateRule(line);
            }
        }

        private void generateRule(String ruleString) {
            if (ruleString.contains(": ")) {
                var lhsString = ruleString.split(": ")[0];
                var rhsString = ruleString.split(": ")[1];
                if (rhsString.contains(" | ")) {
                    generateRule(lhsString + ": " + rhsString.replaceAll("^(.*?) \\| (.*)$", "$1"));
                    generateRule(lhsString + ": " + rhsString.replaceAll("^(.*?) \\| (.*)$", "$2"));
                } else {
                    rules.computeIfAbsent(lhsString, s -> new HashSet<>()).add(tokenize(rhsString));
                }
            }
        }
    }

    private static List<String> tokenize(String input) {
        var tokens = new ArrayList<String>();
        if (input.matches("^[ab]+$")) {
            // This is one of our input strings
            for (var c : input.toCharArray()) {
                tokens.add(String.valueOf(c));
            }
        } else {
            // These are symbols
            var matcher = Pattern.compile("(\\d+)|\"([ab])\"").matcher(input);
            while (matcher.find()) {
                tokens.add(Optional.ofNullable(matcher.group(1)).orElse(matcher.group(2)));
            }
        }
        return tokens;
    }
}
