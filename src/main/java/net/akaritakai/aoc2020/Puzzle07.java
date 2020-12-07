package net.akaritakai.aoc2020;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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
        var count = rules.keySet().stream().filter(color -> containsGold(rules, color)).count();
        return String.valueOf(count);
    }

    @Override
    public String solvePart2() {
        var count = nestedCount(rules(), "shiny gold") - 1;
        return String.valueOf(count);
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
            var matcher = Pattern.compile("^(.+) bags contain (.+)\\.$").matcher(line);
            if (matcher.find()) {
                var innerMap = map.computeIfAbsent(matcher.group(1), s -> new HashMap<>());
                for (var element : matcher.group(2).split(", ")) {
                    matcher = Pattern.compile("^(\\d+) (.+) bags?$").matcher(element);
                    if (matcher.find()) {
                        innerMap.put(matcher.group(2), Integer.parseInt(matcher.group(1)));
                    }
                }
            }
        }
        return map;
    }
}
