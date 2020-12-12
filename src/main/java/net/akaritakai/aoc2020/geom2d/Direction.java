package net.akaritakai.aoc2020.geom2d;

import static net.akaritakai.aoc2020.geom2d.Turn.LEFT;
import static net.akaritakai.aoc2020.geom2d.Turn.RIGHT;

public enum Direction {
    NORTH (0, 1),  // UP
    SOUTH (0, -1), // DOWN
    EAST (1, 0),   // LEFT
    WEST (-1, 0);  // RIGHT

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Point move(Point point) {
        return new Point(point.x() + dx, point.y() + dy);
    }

    public Point move(Point point, long distance) {
        return new Point(point.x() + dx * distance, point.y() + dy * distance);
    }

    public Direction turn(Turn turn) {
        return switch (turn) {
            case LEFT -> switch (this) {
                case NORTH -> WEST;
                case SOUTH -> EAST;
                case EAST -> NORTH;
                case WEST -> SOUTH;
            };
            case RIGHT -> switch (this) {
                case NORTH -> EAST;
                case SOUTH -> WEST;
                case EAST -> SOUTH;
                case WEST -> NORTH;
            };
        };
    }

    public Turn turn(Direction newDirection) {
        switch (this) {
            case NORTH -> {
                if (newDirection == WEST) return LEFT;
                if (newDirection == EAST) return RIGHT;
            }
            case SOUTH -> {
                if (newDirection == EAST) return LEFT;
                if (newDirection == WEST) return RIGHT;
            }
            case EAST -> {
                if (newDirection == NORTH) return LEFT;
                if (newDirection == SOUTH) return RIGHT;
            }
            case WEST -> {
                if (newDirection == SOUTH) return LEFT;
                if (newDirection == NORTH) return RIGHT;
            }
        }
        throw new UnsupportedOperationException("Direction change is not a 90 degree turn (i.e. 0 degrees or 180 degrees)");
    }

    public static Direction fromSegment(Point start, Point end) {
        long dx = end.x() - start.x();
        long dy = end.y() - start.y();
        if (dx == 0 && dy > 0) return NORTH;
        if (dx == 0 && dy < 0) return SOUTH;
        if (dx > 0 && dy == 0) return EAST;
        if (dx < 0 && dy == 0) return WEST;
        throw new IllegalArgumentException("Line segment is not vertical or horizontal");
    }

    public Direction opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }
}
