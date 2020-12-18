package net.akaritakai.aoc2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;

/**
 * In Day 18 we have a classic problem: evaluating infix expressions with a custom operator precedence. It can be solved
 * as follows:
 * - First, we must must decode infix into an AST via the Shunting-yard algorithm.
 * - Finally, we evaluate the AST.
 */
public class Puzzle18 extends AbstractPuzzle {
    public Puzzle18(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 18;
    }

    @Override
    public String solvePart1() {
        var sum = 0L;
        for (var line : getPuzzleInput().split("\n")) {
            sum += evaluatePostfix(infixToPostfix(line, (value, stack) -> true));
        }
        return String.valueOf(sum);
    }

    @Override
    public String solvePart2() {
        var sum = 0L;
        for (var line : getPuzzleInput().split("\n")) {
            sum += evaluatePostfix(infixToPostfix(line, (stack, value) -> !stack.equals("*") || !value.equals("+")));
        }
        return String.valueOf(sum);
    }

    public static Long evaluatePostfix(List<String> tokens) {
        var stack = new Stack<Long>();
        for (var token : tokens) {
            switch (token) {
                case "+" -> {
                    var rhs = stack.pop();
                    var lhs = stack.pop();
                    stack.push(lhs + rhs);
                }
                case "-" -> {
                    var rhs = stack.pop();
                    var lhs = stack.pop();
                    stack.push(lhs - rhs);
                }
                case "*" -> {
                    var rhs = stack.pop();
                    var lhs = stack.pop();
                    stack.push(lhs * rhs);
                }
                case "/" -> {
                    var rhs = stack.pop();
                    var lhs = stack.pop();
                    stack.push(lhs / rhs);
                }
                default -> stack.push(Long.parseLong(token));
            }
        }
        return stack.pop();
    }

    public static List<String> infixToPostfix(String value, BiPredicate<String, String> precedence) {
        var output = new ArrayList<String>();
        var stack = new Stack<String>();
        value = value.replaceAll("\\s+", "");
        var matcher = Pattern.compile("(\\d+|[+*()])").matcher(value);
        while (matcher.find()) {
            var token = matcher.group(1);
            if (token.matches("[0-9]+")) {
                output.add(token);
            } else if (token.matches("[+*]")) {
                while (!stack.isEmpty() && precedence.test(stack.peek(), token) && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (stack.peek().equals("(")) {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        return output;
    }
}
