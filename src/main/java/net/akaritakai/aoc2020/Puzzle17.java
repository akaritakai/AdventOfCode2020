package net.akaritakai.aoc2020;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Puzzle17 extends AbstractPuzzle {
    public Puzzle17(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 17;
    }

    @Override
    public String solvePart1() {
        var grid = new Grid(getPuzzleInput());
        IntStream.range(0, 6).forEach(i -> grid.round3D());
        return String.valueOf(grid.activeCubes3D.size());
    }

    @Override
    public String solvePart2() {
        var grid = new Grid(getPuzzleInput());
        IntStream.range(0, 6).forEach(i -> grid.round4D());
        return String.valueOf(grid.activeCubes4D.size());
    }

    private static class Grid {
        private Set<Point3D> activeCubes3D = new HashSet<>();
        private Set<Point4D> activeCubes4D = new HashSet<>();

        public Grid(String input) {
            var lines = input.split("\n");
            for (var y = 0; y < lines.length; y++) {
                for (var x = 0; x < lines[0].length(); x++) {
                    if (lines[y].charAt(x) == '#') {
                        activeCubes3D.add(new Point3D(x, y, 0));
                        activeCubes4D.add(new Point4D(x, y, 0, 0));
                    }
                }
            }
        }

        public void round3D() {
            var set = new HashSet<Point3D>();
            // Handle active points
            activeCubes3D.forEach(point -> {
                var adjacent = point.adjacentPoints();
                var numAdjacent = activeCubes3D.stream().filter(adjacent::contains).count();
                if (numAdjacent == 2 || numAdjacent == 3) {
                    set.add(point);
                }
            });
            // Handle inactive points
            activeCubes3D.stream()
                    .flatMap(point -> point.adjacentPoints().stream())
                    .filter(point -> !activeCubes3D.contains(point))
                    .forEach(point -> {
                        var adjacent = point.adjacentPoints();
                        var numAdjacent = activeCubes3D.stream().filter(adjacent::contains).count();
                        if (numAdjacent == 3) {
                            set.add(point);
                        }
                    });
            activeCubes3D = set;
        }

        public void round4D() {
            var set = new HashSet<Point4D>();
            // Handle active points
            activeCubes4D.forEach(point -> {
                var adjacent = point.adjacentPoints();
                var numAdjacent = activeCubes4D.stream().filter(adjacent::contains).count();
                if (numAdjacent == 2 || numAdjacent == 3) {
                    set.add(point);
                }
            });
            // Handle inactive points
            activeCubes4D.stream()
                    .flatMap(point -> point.adjacentPoints().stream())
                    .filter(point -> !activeCubes4D.contains(point))
                    .forEach(point -> {
                        var adjacent = point.adjacentPoints();
                        var numAdjacent = activeCubes4D.stream().filter(adjacent::contains).count();
                        if (numAdjacent == 3) {
                            set.add(point);
                        }
                    });
            activeCubes4D = set;
        }
    }

    private record Point3D(long x, long y, long z) {
        private Set<Point3D> adjacentPoints() {
            var adjacent = new HashSet<Point3D>();
            for (var dx = -1; dx <= 1; dx++) {
                for (var dy = -1; dy <= 1; dy++) {
                    for (var dz = -1; dz <= 1; dz++) {
                        if (dx == 0 && dy == 0 && dz == 0) continue;
                        adjacent.add(new Point3D(x + dx, y + dy, z + dz));
                    }
                }
            }
            return adjacent;
        }
    }

    private record Point4D(long x, long y, long z, long w) {
        private Set<Point4D> adjacentPoints() {
            var adjacent = new HashSet<Point4D>();
            for (var dx = -1; dx <= 1; dx++) {
                for (var dy = -1; dy <= 1; dy++) {
                    for (var dz = -1; dz <= 1; dz++) {
                        for (var dw = -1; dw <= 1; dw++) {
                            if (dx == 0 && dy == 0 && dz == 0 && dw == 0) continue;
                            adjacent.add(new Point4D(x + dx, y + dy, z + dz, w + dw));
                        }
                    }
                }
            }
            return adjacent;
        }
    }
}
