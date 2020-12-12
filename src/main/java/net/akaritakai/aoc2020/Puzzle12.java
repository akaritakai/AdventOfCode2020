package net.akaritakai.aoc2020;

import net.akaritakai.aoc2020.geom2d.Point;
import net.akaritakai.aoc2020.geom2d.Turn;

import static net.akaritakai.aoc2020.geom2d.Direction.*;

public class Puzzle12 extends AbstractPuzzle {
    public Puzzle12(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 12;
    }

    @Override
    public String solvePart1() {
        var ship = new Point(0, 0);
        var direction = EAST;
        for (var line : getPuzzleInput().split("\n")) {
            var action = line.charAt(0);
            var value = Integer.parseInt(line.substring(1));
            switch (action) {
                case 'N' -> ship = NORTH.move(ship, value);
                case 'S' -> ship = SOUTH.move(ship, value);
                case 'E' -> ship = EAST.move(ship, value);
                case 'W' -> ship = WEST.move(ship, value);
                case 'L' -> {
                    while (value >= 90) {
                        direction = direction.turn(Turn.LEFT);
                        value -= 90;
                    }
                }
                case 'R' -> {
                    while (value >= 90) {
                        direction = direction.turn(Turn.RIGHT);
                        value -= 90;
                    }
                }
                case 'F' -> ship = direction.move(ship, value);
            }
        }
        return String.valueOf(ship.manhattanDistance(new Point(0, 0)));
    }

    @Override
    public String solvePart2() {
        var ship = new Point(0, 0);
        var waypoint = new Point(10, 1);
        for (var line : getPuzzleInput().split("\n")) {
            var action = line.charAt(0);
            var value = Integer.parseInt(line.substring(1));
            switch (action) {
                case 'N' -> waypoint = NORTH.move(waypoint, value);
                case 'S' -> waypoint = SOUTH.move(waypoint, value);
                case 'E' -> waypoint = EAST.move(waypoint, value);
                case 'W' -> waypoint = WEST.move(waypoint, value);
                case 'L' -> {
                    while (value >= 90) {
                        waypoint = new Point(-waypoint.y(), waypoint.x());
                        value -= 90;
                    }
                }
                case 'R' -> {
                    while (value >= 90) {
                        waypoint = new Point(waypoint.y(), -waypoint.x());
                        value -= 90;
                    }
                }
                case 'F' -> ship = ship.move(waypoint.x() * value, waypoint.y() * value);
            }
        }
        return String.valueOf(ship.manhattanDistance(new Point(0, 0)));
    }
}
