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

    public long manhattanDistance(Point other) {
        return manhattanDistance(this, other);
    }

    public static long manhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public double distance(Point other) {
        return distance(this, other);
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
}
