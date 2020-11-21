package net.akaritakai.aoc2020.graph;

import net.akaritakai.aoc2020.geom2d.Point;
import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GraphBuilder {
    /**
     * Clones a graph to a new instance of a graph.
     * Note: Vertex and edge objects are not deep cloned.
     */
    public static <T> Graph<T, DefaultWeightedEdge> copyGraph(Graph<T, DefaultWeightedEdge> graph) {
        //noinspection unchecked
        return (Graph<T, DefaultWeightedEdge>) ((AbstractBaseGraph<T, DefaultWeightedEdge>) graph).clone();
    }

    /**
     * Builds a planar directed graph of points where points are connected only if they are adjacent per
     * {@link Point#adjacentPoints()} (like in a maze).
     */
    public static Graph<Point, DefaultWeightedEdge> buildMazeGraph(Set<Point> vertices) {
        return buildGraph(vertices, point ->
            point.adjacentPoints().stream().filter(vertices::contains).collect(Collectors.toSet()));
    }

    /**
     * Specifies that vertices are 'blockers' in a graph. In a directed graph, the blockers themselves are reachable,
     * but cannot be traversed past once reached.
     */
    public static <T> void addBlockers(Graph<T, DefaultWeightedEdge> graph, Set<T> blockers) {
        for (T blocker : blockers) {
            for (var edge : Set.copyOf(graph.outgoingEdgesOf(blocker))) {
                graph.removeEdge(edge);
            }
        }
    }

    /**
     * Specifies that vertices are 'blockers' in a graph. In a directed graph, the blockers themselves are unreachable.
     */
    public static <T> void addBlockersStrict(Graph<T, DefaultWeightedEdge> graph, Set<T> blockers) {
        for (T blocker : blockers) {
            graph.removeVertex(blocker);
        }
    }

    /**
     * Builds a directed graph.
     */
    public static <T> Graph<T, DefaultWeightedEdge> buildGraph(Set<T> vertices, Function<T, Set<T>> adjacentVertices) {
        var graph = new SimpleDirectedWeightedGraph<T, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        for (T vertex : vertices) {
            graph.addVertex(vertex);
            for (T adjacent : adjacentVertices.apply(vertex)) {
                graph.addVertex(adjacent);
                var edge = graph.addEdge(vertex, adjacent);
                if (edge != null) {
                    graph.setEdgeWeight(edge, 1);
                }
            }
        }
        return graph;
    }

    /**
     * This method performs the {@link GraphBuilder#reduceVertex(Graph, Object)} on every node in the graph except for
     * those listed in the irreducible vertices set.
     */
    public static <T> void reduceGraph(Graph<T, DefaultWeightedEdge> graph, Set<T> irreducibleVertices) {
        var modified = true;
        while (modified) {
            modified = false;
            for (var vertex : Set.copyOf(graph.vertexSet())) {
                if (!graph.containsVertex(vertex)) {
                    continue; // This vertex was reduced already
                }
                if (irreducibleVertices.contains(vertex)) {
                    continue; // This vertex cannot be reduced
                }
                modified |= reduceVertex(graph, vertex);
            }
        }
    }

    /**
     * Suppose the edge set of the given vertex y is the following:
     *
     *     a         c
     *  ------>   ------>
     * x        y         z
     *  <------   <------
     *     b         d
     *
     * where x,z are vertices and a,b,c,d are edge weights. If x and z are not directly connected to each other, then
     * the graph could be reduced to:
     *
     *    a+c
     *  ------>
     * x       z
     *  <------
     *    b+d
     *
     * and y will be removed from the graph.
     *
     * This method attempts to make this reduction on the provided vertex 'y', and will do nothing if the conditions are
     * not met.
     */
    private static <T> boolean reduceVertex(Graph<T, DefaultWeightedEdge> graph, T y) {
        if (graph.getType().isAllowingSelfLoops()) {
            throw new IllegalArgumentException("Graph cannot allow self loops");
        }
        if (!graph.getType().isDirected()) {
            throw new IllegalArgumentException("Graph must be directed");
        }

        var incomingEdges = graph.incomingEdgesOf(y);
        var outgoingEdges = graph.outgoingEdgesOf(y);
        var incomingVertices = incomingEdges.stream().map(graph::getEdgeSource).collect(Collectors.toSet());
        var outgoingVertices = outgoingEdges.stream().map(graph::getEdgeTarget).collect(Collectors.toSet());

        if (incomingVertices.size() == 2 && outgoingVertices.size() == 2 && incomingEdges.equals(outgoingEdges)) {
            // Vertex may be reduced if x and z do not connect with each other
            var vertices = List.copyOf(incomingVertices);
            var x = vertices.get(0);
            var z = vertices.get(1);

            if (graph.getEdge(x, z) == null && graph.getEdge(z, x) == null) {
                // x and z do not connect with each other. This graph can be reduced.

                // Calculate x -> z weight and add edge
                var xzWeight = graph.getEdgeWeight(graph.getEdge(x, y)) + graph.getEdgeWeight(graph.getEdge(y, z));
                var xzEdge = graph.addEdge(x, z);
                graph.setEdgeWeight(xzEdge, xzWeight);

                // Calculate z -> x weight and add edge
                var zxWeight = graph.getEdgeWeight(graph.getEdge(z, y)) + graph.getEdgeWeight(graph.getEdge(y, x));
                var zxEdge = graph.addEdge(z, x);
                graph.setEdgeWeight(zxEdge, zxWeight);

                // Remove y edges and vertex
                graph.removeVertex(y);
                return true;
            }
        }
        return false;
    }
}
