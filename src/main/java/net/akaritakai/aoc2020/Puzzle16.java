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
        var tickets = otherTickets();
        var errorRate = tickets.stream()
                .flatMap(Collection::stream)
                .mapToInt(i -> i)
                .filter(i -> rules.values().stream().noneMatch(rule -> rule.test(i)))
                .sum();
        return String.valueOf(errorRate);
    }

    @Override
    public String solvePart2() {
        try (var context = new Context()) {
            var solver = context.mkSolver();
            var rules = rules();
            var myTicket = myTicket();
            var tickets = allValidTickets(rules, myTicket, otherTickets());
            var fields = new HashMap<String, ArithExpr>();
            for (var field : rules.keySet()) {
                var expr = (ArithExpr) context.mkConst(context.mkSymbol(field), context.getIntSort());
                fields.put(field, expr);
                solver.add(context.mkGe(expr, context.mkInt(0)));
                solver.add(context.mkLe(expr, context.mkInt(rules.size())));
            }
            solver.add(context.mkDistinct(fields.values().toArray(ArithExpr[]::new)));
            fields.forEach((field, expr) -> solver.add(context.mkOr(IntStream.range(0, rules.size())
                    .filter(i -> tickets.stream().allMatch(ticket -> rules.get(field).test(ticket.get(i))))
                    .mapToObj(i -> context.mkEq(expr, context.mkInt(i)))
                    .toArray(BoolExpr[]::new))));
            solver.check();
            var result = fields.entrySet()
                    .stream()
                    .filter(e -> e.getKey().startsWith("departure"))
                    .map(e -> Integer.parseInt(solver.getModel().eval(e.getValue(), false).toString()))
                    .mapToLong(i -> myTicket().get(i))
                    .reduce((a, b) -> a * b)
                    .orElseThrow();
            return String.valueOf(result);
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

    private static Set<List<Integer>> allValidTickets(Map<String, Predicate<Integer>> rules,
                                                      List<Integer> myTicket,
                                                      Collection<List<Integer>> otherTickets) {
        var tickets = new HashSet<List<Integer>>();
        tickets.add(myTicket);
        tickets.addAll(otherTickets);
        tickets.removeIf(ticket -> !ticket.stream().allMatch(i ->
                rules.values().stream().anyMatch(rule -> rule.test(i))));
        return tickets;
    }

    private static List<Integer> parseTicket(String ticketString) {
        return Arrays.stream(ticketString.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
