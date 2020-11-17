package net.akaritakai.aoc2020.geom2d;

/**
 * Defines a rectangular image's dimensions given the provided lower left and upper right corners of the rectangle.
 * Assumes that the rectangle's size is perpendicular to the X/Y axes (as it should be for console output).
 */
public record ImageSize(Point lowerLeft, Point upperRight) {
    public long leftEdge() {
        return lowerLeft.x();
    }
    public long rightEdge() {
        return upperRight.x();
    }
    public long topEdge() {
        return upperRight.y();
    }
    public long bottomEdge() {
        return lowerLeft.y();
    }
}
