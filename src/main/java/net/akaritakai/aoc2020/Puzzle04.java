package net.akaritakai.aoc2020;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.primitives.Ints;

import java.util.*;
import java.util.regex.Pattern;

/**
 * In Day 4, we are again given a set of predicates for a list of inputs and told to find how many inputs match all the
 * predicates.
 *
 * The problem is identical to Day 2 with the 'curveball' being how much more involved parsing the inputs and checking
 * the predicates are.
 */
public class Puzzle04 extends AbstractPuzzle {
    public Puzzle04(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public String solvePart1() {
        var count = Arrays.stream(getPuzzleInput().split("\n\n"))
                .map(Passport::parse)
                .filter(Passport::hasAllRequiredFields)
                .count();
        return String.valueOf(count);
    }

    @Override
    public String solvePart2() {
        var count = Arrays.stream(getPuzzleInput().split("\n\n"))
                .map(Passport::parse)
                .filter(passport -> passport.hasAllRequiredFields() && passport.allValuesAreValid())
                .count();
        return String.valueOf(count);
    }

    @VisibleForTesting
    record Passport(Map<String, String> entries) {
        private static Passport parse(String passportString) {
            var data = new HashMap<String, String>();
            for (var token : passportString.split("[ \n]+")) {
                var matcher = Pattern.compile("^(byr|iyr|eyr|hgt|hcl|ecl|pid|cid):(\\S+)$").matcher(token);
                if (matcher.find()) {
                    data.put(matcher.group(1), matcher.group(2));
                }
            }
            return new Passport(data);
        }

        private boolean hasAllRequiredFields() {
            return entries.size() == 8 || entries.size() == 7 && !entries.containsKey("cid");
        }

        private boolean allValuesAreValid() {
            return entries.entrySet().stream().allMatch(e -> isValueValid(e.getKey(), e.getValue()));
        }

        @VisibleForTesting
        static boolean isValueValid(String key, String value) {
            return switch (key) {
                case "byr" -> value.matches("^\\d{4}$") && inRange(value, 1920, 2002);
                case "iyr" -> value.matches("^\\d{4}$") && inRange(value, 2010, 2020);
                case "eyr" -> value.matches("^\\d{4}$") && inRange(value, 2020, 2030);
                case "hgt" -> value.matches("^\\d+cm$") && inRange(value.split("cm")[0], 150, 193)
                        || value.matches("^\\d+in$") && inRange(value.split("in")[0], 59, 76);
                case "hcl" -> value.matches("^#[0-9a-f]{6}$");
                case "ecl" -> value.matches("^amb|blu|brn|gry|grn|hzl|oth$");
                case "pid" -> value.matches("^\\d{9}$");
                case "cid" -> true;
                default -> false;
            };
        }

        @SuppressWarnings("UnstableApiUsage")
        private static boolean inRange(String numberString, int startInclusive, int endInclusive) {
            var number = Ints.tryParse(numberString);
            return number != null && number >= startInclusive && number <= endInclusive;
        }
    }
}
