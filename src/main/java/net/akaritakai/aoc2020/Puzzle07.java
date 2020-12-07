package net.akaritakai.aoc2020;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Day 7 is the first day that tests our comfort with recursion. We are given a function of color to (quantity, color)
 * tuples that serve to tell us the contents of a given colored bag.
 *
 * Part 1 asks us to find those colored bags which would contain (in any nesting) a 'shiny gold' bag. This is done by
 * checking every color recursively: it either contains it or a color it contains must contain it.
 *
 * Part 2 asks us to find the nested number of colored bags that a single 'shiny gold' bag would contain. This is also
 * done recursively by adding 1 (our current bag) plus the quantity * number of nested bags for each contained bag. At
 * the end, we are left to subtract 1 to exclude the gold bag itself from the resulting count.
 *
 * The inputs themselves are both small and do not appear to contain cycles, and so no care (cycle-detection) or dynamic
 * programming is required to solve this, but it would be trivial to extend the solution to handle these requirements.
 */
public class Puzzle07 extends AbstractPuzzle {
    public Puzzle07(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 7;
    }

    @Override
    public String solvePart1() {
        var rules = rules();
        return String.valueOf(rules.keySet().stream().filter(color -> containsGold(rules, color)).count());
    }

    @Override
    public String solvePart2() {
        return String.valueOf(nestedCount(rules(), "shiny gold") - 1);
    }

    private static boolean containsGold(Map<String, Map<String, Integer>> rules, String bagColor) {
        var contents = rules.getOrDefault(bagColor, Map.of()).keySet();
        return contents.contains("shiny gold") || contents.stream().anyMatch(color -> containsGold(rules, color));
    }

    private static int nestedCount(Map<String, Map<String, Integer>> rules, String bagColor) {
        return 1 + rules.getOrDefault(bagColor, Map.of()).entrySet().stream()
                .mapToInt(e -> e.getValue() * nestedCount(rules, e.getKey()))
                .sum();
    }

    private Map<String, Map<String, Integer>> rules() {
        var map = new HashMap<String, Map<String, Integer>>();
        for (var line : getPuzzleInput().split("\n")) {
            var matcher = Pattern.compile("(\\d+) (.+?) bags?").matcher(line);
            while (matcher.find()) {
                map.computeIfAbsent(line.split(" bags contain ")[0], s -> new HashMap<>())
                        .put(matcher.group(2), Integer.parseInt(matcher.group(1)));
            }
        }
        return map;
    }
}
