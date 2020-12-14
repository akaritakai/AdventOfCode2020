package net.akaritakai.aoc2020;

import net.akaritakai.aoc2020.geom2d.Point;
import net.akaritakai.aoc2020.geom2d.Turn;

import static net.akaritakai.aoc2020.geom2d.Direction.*;

/**
 * In Day 12, we are given a set of translation and rotation instructions on the cartesian plane and told to find the
 * end result.
 *
 * What makes this problem difficult is that we are asked to rotate a point around another, which is not necessarily
 * easy to reason about. If we were not working with such nice values (90, 180, or 270 degree rotations only), the best
 * way to do this would be to use the following method:
 *
 * Given a point p with coordinates (x,y) and a center of rotation c (a,b) in the cartesian plane, we can calculate the
 * rotation as a set of matrix operations:
 * p' = [cosΘ  -sinΘ] x [x-a] + [a] = (a,b)
 *      [sinΘ   cosΘ]   [y-b]   [b]
 * where Θ is the counterclockwise rotation in radians.
 *
 * For example, rotating p = (1,2) around c = (3,5) to the left (counterclockwise) by 90 degrees (pi/2 radians) yields:
 * p' = [cos(pi/2)  -sin(pi/2)] x [1-3] + [3]
 *      [sin(pi/2)   cos(pi/2)]   [2-5] + [5]
 *    = [0  -1] x [-2] + [3] = [ 3] + [3] = [6]
 *      [1   0]   [-3]   [5]   [-2]   [5]   [3]
 *    = (6,3)
 */
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
