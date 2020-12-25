package net.akaritakai.aoc2020.geom2d;

public record Point(long x, long y) {
    public Point move(long dx, long dy) {
        return new Point(x + dx, y + dy);
    }

    public long manhattanDistance(Point other) {
        return manhattanDistance(this, other);
    }

    public static long manhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
