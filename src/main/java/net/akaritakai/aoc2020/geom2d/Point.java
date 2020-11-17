package net.akaritakai.aoc2020.geom2d;

import java.util.Set;

import static net.akaritakai.aoc2020.geom2d.Direction.*;

public record Point(long x, long y) {
    public Set<Point> adjacentPoints() {
        return Set.of(
                NORTH.move(this),
                SOUTH.move(this),
                EAST.move(this),
                WEST.move(this)
        );
    }

    public double manhattanDistance(Point other) {
        return manhattanDistance(this, other);
    }

    public static double manhattanDistance(Point lhs, Point rhs) {
        return Math.abs(lhs.x - rhs.x) + Math.abs(lhs.y - rhs.y);
    }

    public double distance(Point other) {
        return distance(this, other);
    }

    public static double distance(Point lhs, Point rhs) {
        return Math.sqrt((lhs.x - rhs.x) * (lhs.x - rhs.x) + (lhs.y - rhs.y) * (lhs.y - rhs.y));
    }
}
