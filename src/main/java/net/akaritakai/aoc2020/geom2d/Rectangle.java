package net.akaritakai.aoc2020.geom2d;

import org.apache.commons.lang3.Validate;

import java.util.stream.Stream;

/**
 * A rectangle with the upper left corner at (x,y) and the lower right corner at (x+width, y-height), and sides parallel
 * to the X/Y axes.
 */
public record Rectangle(long x, long y, long width, long height) {
    public Rectangle {
        Validate.isTrue(width > 0, "width must be positive");
        Validate.isTrue(height > 0, "height must be positive");
    }

    public static Rectangle fromPoints(Point p1, Point p2, Point p3, Point p4) {
        var minX = Stream.of(p1, p2, p3, p4).mapToLong(Point::x).min().orElseThrow();
        var maxX = Stream.of(p1, p2, p3, p4).mapToLong(Point::x).max().orElseThrow();
        var minY = Stream.of(p1, p2, p3, p4).mapToLong(Point::y).min().orElseThrow();
        var maxY = Stream.of(p1, p2, p3, p4).mapToLong(Point::y).max().orElseThrow();

        Validate.isTrue(Stream.of(p1, p2, p3, p4).filter(p -> p.x() == minX).count() == 2
                && Stream.of(p1, p2, p3, p4).filter(p -> p.x() == maxX).count() == 2
                && Stream.of(p1, p2, p3, p4).filter(p -> p.y() == minY).count() == 2
                && Stream.of(p1, p2, p3, p4).filter(p -> p.y() == maxY).count() == 2,
                "points don't form a rectangle");

        return new Rectangle(minX, maxY, maxX - minX, maxY - minY);
    }

    public long area() {
        return width * height;
    }

    public Point upperLeft() {
        return new Point(x, y);
    }

    public Point upperRight() {
        return new Point(x + width, y);
    }

    public Point lowerLeft() {
        return new Point(x, y - height);
    }

    public Point lowerRight() {
        return new Point(x + width, y - height);
    }

    public long leftEdge() {
        return x;
    }

    public long rightEdge() {
        return x + width;
    }

    public long topEdge() {
        return y;
    }

    public long bottomEdge() {
        return y - height;
    }

    public static boolean intersects(Rectangle r1, Rectangle r2) {
        // Conditions for non-intersection:
        // - R1 is right of R2 (r1.leftEdge() > r2.rightEdge())
        // - R1 is left of R2 (r1.rightEdge() < r2.leftEdge())
        // - R1 is below R2 (r1.topEdge() < r2.bottomEdge())
        // - R1 is above R2 (r1.bottomEdge() > r2.topEdge())

        return r1.leftEdge() <= r2.rightEdge()
                && r1.rightEdge() >= r2.leftEdge()
                && r1.topEdge() >= r2.bottomEdge()
                && r1.bottomEdge() <= r2.topEdge();
    }

    public boolean intersects(Rectangle other) {
        return intersects(this, other);
    }
}
