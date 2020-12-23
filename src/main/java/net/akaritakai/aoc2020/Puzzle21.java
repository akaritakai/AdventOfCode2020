package net.akaritakai.aoc2020;

import com.google.common.collect.Sets;
import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Puzzle21 extends AbstractPuzzle {
    public Puzzle21(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 21;
    }

    @Override
    public String solvePart1() {
        var labels = labels();
        var possibilities = new HashMap<String, Set<String>>();
        labels.forEach((ingredients, allergens) -> allergens.forEach(allergen -> {
            if (possibilities.containsKey(allergen)) {
                possibilities.put(allergen, Sets.intersection(possibilities.get(allergen), ingredients));
            } else {
                possibilities.put(allergen, new HashSet<>(ingredients));
            }
        }));

        return String.valueOf(labels.keySet()
                .stream()
                .flatMap(Collection::stream)
                .filter(ingredient -> possibilities.values()
                        .stream()
                        .flatMap(Collection::stream)
                        .noneMatch(i -> i.equals(ingredient)))
                .count());
    }

    @Override
    public String solvePart2() {
        var labels = labels();

        try (var context = new Context()) {
            var solver = context.mkSolver();

            // First, let's give every ingredient a number
            var ingredients = new HashMap<String, Integer>();
            var i = new AtomicInteger();
            labels.keySet()
                    .stream()
                    .flatMap(Collection::stream)
                    .distinct()
                    .forEach(ingredient -> ingredients.put(ingredient, i.getAndIncrement()));

            // Now, let's define our allergens as being numbers (so we can have a relationship between allergens and
            // ingredients
            var allergens = new HashMap<String, ArithExpr>();
            labels.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .distinct()
                    .forEach(allergen -> allergens.put(allergen, (ArithExpr) context.mkConst(allergen, context.getIntSort())));

            // Allergens must point to one of the ingredients
            allergens.values().forEach(allergen -> {
                solver.add(context.mkGe(allergen, context.mkInt(0)));
                solver.add(context.mkLt(allergen, context.mkInt(ingredients.size())));
            });

            // The allergens -> ingredients function must be injective
            solver.add(context.mkDistinct(allergens.values().toArray(ArithExpr[]::new)));

            // Now, lets define our constraints
            labels.forEach((labelIngredients, labelAllergens) -> {
                labelAllergens.forEach(allergen -> {
                    solver.add(context.mkOr(ingredients.entrySet().stream()
                            .filter(e -> labelIngredients.contains(e.getKey()))
                            .map(e -> context.mkEq(allergens.get(allergen), context.mkInt(e.getValue())))
                            .toArray(BoolExpr[]::new)));
                });
            });

            // Solve it!
            solver.check();

            return allergens.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(e -> Integer.parseInt(solver.getModel().eval(e.getValue(), false).toString()))
                    .map(e -> ingredients.entrySet()
                            .stream()
                            .filter(n -> n.getValue().equals(e))
                            .map(Map.Entry::getKey)
                            .findAny()
                            .orElseThrow())
                    .collect(Collectors.joining(","));
        }
    }

    private Map<Set<String>, Set<String>> labels() {
        var labels = new HashMap<Set<String>, Set<String>>();
        for (var line : getPuzzleInput().split("\n")) {
            var sides = line.split("\\(");
            var ingredients = Arrays.stream(sides[0].split(" ")).collect(Collectors.toSet());
            var allergens = labels.computeIfAbsent(ingredients, s -> new HashSet<>());
            if (sides.length > 1) {
                allergens.addAll(Arrays.asList(sides[1].substring("contains ".length(), sides[1].length() - 1).split(", ")));
            }
        }
        return labels;
    }
}
