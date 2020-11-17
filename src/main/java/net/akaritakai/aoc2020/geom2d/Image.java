package net.akaritakai.aoc2020.geom2d;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Image {
    public static <T> String renderImage(Collection<T> objects, Function<T, String> renderer, long width, long height) {
        var it = objects.iterator();
        return LongStream.range(0, height).boxed()
                .map(y -> LongStream.range(0, width).boxed()
                        .map(x -> renderer.apply(it.hasNext() ? it.next() : null))
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }

    public static String renderImage(Set<Point> points, Function<Point, String> renderer) {
        var imageSize = imageSize(points);
        return renderImage(imageSize, renderer);
    }

    public static String renderImage(Rectangle imageSize, Function<Point, String> renderer) {
        return LongStream.range(imageSize.y(), imageSize.y() + imageSize.height()).boxed()
                .map(y -> LongStream.range(imageSize.x(), imageSize.x() + imageSize.width()).boxed()
                        .map(x -> new Point(x, y))
                        .map(renderer)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }

    public static Rectangle imageSize(Set<Point> visiblePoints) {
        return imageSize(visiblePoints, point -> true);
    }

    public static Rectangle imageSize(Set<Point> points, Predicate<Point> isVisible) {
        var minHeight = points.stream().filter(isVisible).mapToLong(Point::y).min().orElseThrow();
        var maxHeight = points.stream().filter(isVisible).mapToLong(Point::y).max().orElseThrow();
        var minWidth = points.stream().filter(isVisible).mapToLong(Point::x).min().orElseThrow();
        var maxWidth = points.stream().filter(isVisible).mapToLong(Point::x).max().orElseThrow();
        return new Rectangle(minWidth, minHeight, maxWidth - minWidth + 1, maxHeight - minHeight + 1);
    }
}
