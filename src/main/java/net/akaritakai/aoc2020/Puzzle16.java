package net.akaritakai.aoc2020;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * In Day 16, we are given a SAT puzzle with various constraints and told to work out a valid model for the given
 * constraints.
 *
 * Very hard cases of SAT problems are NP-complete and require an algorithm like dancing links (Knuth's Algorithm X).
 */
public class Puzzle16 extends AbstractPuzzle {
    public Puzzle16(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 16;
    }

    @Override
    public String solvePart1() {
        var rules = rules();
        var errorRate = otherTickets().stream()
                .flatMap(Collection::stream)
                .mapToInt(i -> i)
                .filter(i -> rules.values().stream().noneMatch(rule -> rule.test(i)))
                .sum();
        return String.valueOf(errorRate);
    }

    @Override
    public String solvePart2() {
        // Get our tickets and rules
        var rules = rules();
        var tickets = new ArrayList<List<Integer>>();
        tickets.add(myTicket()); // Our ticket will be the first in the array
        // Add the other tickets, tossing out any ticket which has invalid values
        otherTickets().stream()
                .filter(ticket -> ticket.stream().allMatch(i ->
                        rules.values().stream().anyMatch(rule -> rule.test(i))))
                .forEach(tickets::add);
        try (var context = new Context()) {
            // Define our SAT solver
            var solver = context.mkSolver();

            // Define the existence of each fields and assert that each field corresponds to an index in a ticket
            // i.e. a field is associated with a number between 0 to numFields-1; and,
            //      the value associated with a given field is not shared by any other field
            var fields = new HashMap<String, ArithExpr>();
            for (var field : rules.keySet()) {
                var expr = (ArithExpr) context.mkConst(context.mkSymbol(field), context.getIntSort());
                fields.put(field, expr);
                solver.add(context.mkGe(expr, context.mkInt(0)));
                solver.add(context.mkLe(expr, context.mkInt(rules.size())));
            }
            solver.add(context.mkDistinct(fields.values().toArray(ArithExpr[]::new)));

            // For each field, test all the tickets to determine which indexes the field could be associated with and
            // give that restriction to the solver
            // i.e. field1 == 1 || field1 == 2 || ...
            fields.forEach((field, expr) -> solver.add(context.mkOr(IntStream.range(0, rules.size())
                    .filter(i -> tickets.stream().allMatch(ticket -> rules.get(field).test(ticket.get(i))))
                    .mapToObj(i -> context.mkEq(expr, context.mkInt(i)))
                    .toArray(BoolExpr[]::new))));

            // Solve the SAT
            solver.check();

            // Use our generated model to get our result
            return String.valueOf(fields.entrySet()
                    .stream()
                    .filter(e -> e.getKey().startsWith("departure"))
                    .map(e -> Integer.parseInt(solver.getModel().eval(e.getValue(), false).toString()))
                    .mapToLong(i -> tickets.get(0).get(i))
                    .reduce((a, b) -> a * b)
                    .orElseThrow());
        }
    }

    private Map<String, Predicate<Integer>> rules() {
        var rules = new HashMap<String, Predicate<Integer>>();
        var matcher = Pattern.compile("(.*?): (\\d+)-(\\d+) or (\\d+)-(\\d+)").matcher(getPuzzleInput());
        while (matcher.find()) {
            var field = matcher.group(1);
            var low1 = Integer.parseInt(matcher.group(2));
            var high1 = Integer.parseInt(matcher.group(3));
            var low2 = Integer.parseInt(matcher.group(4));
            var high2 = Integer.parseInt(matcher.group(5));
            rules.put(field, i -> (i >= low1 && i <= high1) || (i >= low2 && i <= high2));
        }
        return rules;
    }

    private List<Integer> myTicket() {
        return parseTicket(getPuzzleInput().split("\n\n")[1].split("\n")[1]);
    }

    private Collection<List<Integer>> otherTickets() {
        return getPuzzleInput().split("\n\n")[2]
                .lines()
                .skip(1)
                .map(Puzzle16::parseTicket)
                .collect(Collectors.toSet());
    }

    private static List<Integer> parseTicket(String ticketString) {
        return Arrays.stream(ticketString.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
