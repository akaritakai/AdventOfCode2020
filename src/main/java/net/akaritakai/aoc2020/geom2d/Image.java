package net.akaritakai.aoc2020.geom2d;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Image {
    public static <T> String renderImage(Collection<T> objects, Function<T, String> renderer, long width, long height) {
        var it = objects.iterator();
        var sb = new StringBuilder();
        for (var y = 0; y < height; y++) {
            for (var x = 0; x < width; x++) {
                var item = it.hasNext() ? it.next() : null;
                var render = renderer.apply(item);
                sb.append(render);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String renderImage(Set<Point> points, Function<Point, String> renderer) {
        var imageSize = imageSize(points);
        return renderImage(imageSize, renderer);
    }

    public static String renderImage(ImageSize imageSize, Function<Point, String> renderer) {
        var sb = new StringBuilder();
        for (var y = imageSize.topEdge(); y >= imageSize.bottomEdge(); y--) {
            for (var x = imageSize.leftEdge(); x <= imageSize.rightEdge(); x++) {
                var point = new Point(x, y);
                var render = renderer.apply(point);
                sb.append(render);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static ImageSize imageSize(Set<Point> visiblePoints) {
        return imageSize(visiblePoints, point -> true);
    }

    private static ImageSize imageSize(Set<Point> points, Predicate<Point> isVisible) {
        var minX = points.stream().filter(isVisible).mapToLong(Point::x).min().orElseThrow();
        var maxX = points.stream().filter(isVisible).mapToLong(Point::x).max().orElseThrow();
        var minY = points.stream().filter(isVisible).mapToLong(Point::y).min().orElseThrow();
        var maxY = points.stream().filter(isVisible).mapToLong(Point::y).max().orElseThrow();
        return new ImageSize(new Point(minX, minY), new Point(maxX, maxY));
    }
}
