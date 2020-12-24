package net.akaritakai.aoc2020;

import net.akaritakai.aoc2020.geom2d.Point;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In Day 20, we are given a set of jig-saw puzzle pieces that need to be rotated, flipped, and arranged into a jig-saw
 * puzzle. The puzzle is assembled through a simple backtracking algorithm.
 */
public class Puzzle20 extends AbstractPuzzle {
    public Puzzle20(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 20;
    }

    @Override
    public String solvePart1() {
        var tiles = tiles();
        var length = (int) Math.sqrt(tiles.size());
        var arrangement = findArrangement(length, tiles, new HashMap<>(),
                new HashSet<>(tiles.keySet()), new Point(0, 0));
        var upperLeft = arrangement.get(new Point(0, 0)).number;
        var lowerLeft = arrangement.get(new Point(0, length - 1)).number;
        var upperRight = arrangement.get(new Point(length - 1, 0)).number;
        var lowerRight = arrangement.get(new Point(length - 1, length - 1)).number;
        return String.valueOf(upperLeft * lowerLeft * upperRight * lowerRight);
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public String solvePart2() {
        // Discover the arrangement and size of the image
        var tiles = tiles();
        var length = (int) Math.sqrt(tiles.size());
        var arrangement = findArrangement(length, tiles, new HashMap<>(),
                new HashSet<>(tiles.keySet()), new Point(0, 0));
        var tileLength = arrangement.get(new Point(0, 0)).data.length;
        var imageSize = length * (tileLength - 2);

        // Stitch our image together
        boolean[][] image = new boolean[imageSize][imageSize];
        var y = 0;
        for (var row = 0; row < length; row++) {
            for (var tileHeight = 1; tileHeight <= tileLength - 2; tileHeight++) {
                var x = 0;
                for (var col = 0; col < length; col++) {
                    var tile = arrangement.get(new Point(col, row)).data;
                    for (var tileWidth = 1; tileWidth <= tileLength - 2; tileWidth++) {
                        image[y][x] = tile[tileHeight][tileWidth];
                        x++;
                    }
                }
                y++;
            }
        }

        // Remove all the sea monsters
        if (removeAllSeaMonsters(image)) {
            return String.valueOf(roughness(image));
        }
        image = rotateGrid(image);
        if (removeAllSeaMonsters(image)) {
            return String.valueOf(roughness(image));
        }
        image = rotateGrid(image);
        if (removeAllSeaMonsters(image)) {
            return String.valueOf(roughness(image));
        }
        image = rotateGrid(image);
        if (removeAllSeaMonsters(image)) {
            return String.valueOf(roughness(image));
        }
        image = flipGrid(image);
        if (removeAllSeaMonsters(image)) {
            return String.valueOf(roughness(image));
        }
        image = rotateGrid(image);
        if (removeAllSeaMonsters(image)) {
            return String.valueOf(roughness(image));
        }
        image = rotateGrid(image);
        if (removeAllSeaMonsters(image)) {
            return String.valueOf(roughness(image));
        }
        image = rotateGrid(image);
        if (removeAllSeaMonsters(image)) {
            return String.valueOf(roughness(image));
        }
        throw new IllegalStateException("Unable to find the solution");
    }

    private static Map<Point, Tile> findArrangement(int length, Map<Long, Set<Tile>> allTiles,
                                                    Map<Point, Tile> selectedTiles, Set<Long> tilesRemaining,
                                                    Point point) {
        if (tilesRemaining.isEmpty()) {
            return selectedTiles; // We have found our arrangement
        }
        // So we need to find a puzzle piece we can choose for the 'nextPoint' position
        var possibleTiles = allTiles.entrySet().stream()
                .filter(e -> tilesRemaining.contains(e.getKey()))
                .flatMap(e -> e.getValue().stream())
                .filter(tile -> tile.fits(
                        selectedTiles.get(point.move(0, -1)), // above
                        selectedTiles.get(point.move(0, 1)),  // below
                        selectedTiles.get(point.move(-1, 0)), // left
                        selectedTiles.get(point.move(1, 0)))) // right
                .collect(Collectors.toSet());
        if (possibleTiles.isEmpty()) {
            return Map.of(); // No combination possible
        }
        for (var tile : possibleTiles) {
            // Try this tile
            selectedTiles.put(point, tile);
            tilesRemaining.remove(tile.number);
            var result = findArrangement(length, allTiles, selectedTiles, tilesRemaining,
                    nextPoint(length, point));
            if (!result.isEmpty()) {
                return result; // We found the arrangement
            } else {
                // The arrangement was invalid, so backtrack it
                selectedTiles.remove(point, tile);
                tilesRemaining.add(tile.number);
            }
        }

        return Map.of(); // No combination was found
    }

    private static Point nextPoint(int length, Point point) {
        if (point.y() + 1 == length) {
            return new Point(point.x() + 1, 0);
        } else {
            return new Point(point.x(), point.y() + 1);
        }
    }

    private Map<Long, Set<Tile>> tiles() {
        var tiles = new HashMap<Long, Set<Tile>>();
        for (var input : getPuzzleInput().split("\n\n")) {
            var tile = Tile.parse(input);
            tiles.put(tile.number, Set.of(
                    tile,
                    tile.rotate(),
                    tile.rotate().rotate(),
                    tile.rotate().rotate().rotate(),
                    tile.flip(),
                    tile.flip().rotate(),
                    tile.flip().rotate().rotate(),
                    tile.flip().rotate().rotate().rotate()));
        }
        return tiles;
    }

    private static record Tile(long number, boolean[][] data) {
        private static Tile parse(String input) {
            var lines = input.split("\n");
            var number = Long.parseLong(lines[0].replaceAll("^Tile (\\d+):", "$1"));
            var height = lines.length - 1;
            var width = lines[1].length();
            var data = new boolean[width][height];
            for (var y = 0; y < height; y++) {
                var line = lines[y + 1];
                for (var x = 0; x < width; x++) {
                    if (line.charAt(x) == '#') {
                        data[y][x] = true;
                    }
                }
            }
            return new Tile(number, data);
        }

        private Tile rotate() {
            return new Tile(number, rotateGrid(data));
        }

        private Tile flip() {
            return new Tile(number, flipGrid(data));
        }

        private boolean[] topEdge() {
            return data[0];
        }

        private boolean[] bottomEdge() {
            return data[data.length - 1];
        }

        private boolean[] leftEdge() {
            var edge = new boolean[data.length];
            for (var y = 0; y < data.length; y++) {
                edge[y] = data[y][0];
            }
            return edge;
        }

        private boolean[] rightEdge() {
            var edge = new boolean[data.length];
            for (var y = 0; y < data.length; y++) {
                edge[y] = data[y][data[0].length - 1];
            }
            return edge;
        }

        private boolean fits(Tile above, Tile below, Tile left, Tile right) {
            return (above == null || Arrays.equals(this.topEdge(), above.bottomEdge()))
                    && (below == null || Arrays.equals(this.bottomEdge(), below.topEdge()))
                    && (left == null || Arrays.equals(this.leftEdge(), left.rightEdge()))
                    && (right == null || Arrays.equals(this.rightEdge(), right.leftEdge()));
        }
    }

    private static boolean[][] rotateGrid(boolean[][] grid) {
        var height = grid.length;
        var width = grid[0].length;
        var nextGrid = new boolean[height][width];
        for (var y = 0; y < height; y++) {
            for (var x = 0; x < width; x++) {
                nextGrid[y][width - x - 1] = grid[x][y];
            }
        }
        return nextGrid;
    }

    private static boolean[][] flipGrid(boolean[][] grid) {
        var height = grid.length;
        var width = grid[0].length;
        var nextGrid = new boolean[height][width];
        for (var y = 0; y < (height / 2); y++) {
            for (var x = 0; x < width; x++) {
                nextGrid[y][x] = grid[grid.length - y - 1][x];
                nextGrid[grid.length - y - 1][x] = grid[y][x];
            }
        }
        return nextGrid;
    }

    private static final Point[] SEA_MONSTER_COORDINATES = new Point[] {
            new Point(18, 0),
            new Point(0, 1),
            new Point(5, 1),
            new Point(6, 1),
            new Point(11, 1),
            new Point(12, 1),
            new Point(17, 1),
            new Point(18, 1),
            new Point(19, 1),
            new Point(1, 2),
            new Point(4, 2),
            new Point(7, 2),
            new Point(10, 2),
            new Point(13, 2),
            new Point(16, 2)
    };

    private static boolean removeAllSeaMonsters(boolean[][] image) {
        boolean removed = false;
        for (var y = 0; y < image.length; y++) {
            for (var x = 0; x < image[0].length; x++) {
                removed |= removeSeaMonster(image, y, x);
            }
        }
        return removed;
    }

    private static boolean removeSeaMonster(boolean[][] image, int y, int x) {
        if (y + 3 > image.length) {
            return false; // Out of bounds
        }
        if (x + 20 > image[0].length) {
            return false; // Out of bounds
        }
        boolean matches = true;
        for (var point : SEA_MONSTER_COORDINATES) {
            int height = (int) (y + point.y());
            int width = (int) (x + point.x());
            var value = image[height][width];
            matches &= value;
        }
        if (matches) {
            for (var point : SEA_MONSTER_COORDINATES) {
                image[(int) (y + point.y())][(int) (x + point.x())] = false;
            }
        }
        return matches;
    }

    private static int roughness(boolean[][] image) {
        var count = 0;
        for (boolean[] slice : image) {
            for (var x = 0; x < image[0].length; x++) {
                if (slice[x]) {
                    count++;
                }
            }
        }
        return count;
    }
}
