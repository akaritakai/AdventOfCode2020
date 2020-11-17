package net.akaritakai.aoc2020.graph;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class GraphBuilder {
    public static <T> Graph<T, DefaultWeightedEdge> copyGraph(Graph<T, DefaultWeightedEdge> graph) {
        var newGraph = new DefaultUndirectedWeightedGraph<T, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        for (var vertex : graph.vertexSet()) {
            for (var edge : graph.edgesOf(vertex)) {
                var source = graph.getEdgeSource(edge);
                var target = graph.getEdgeTarget(edge);
                var weight = graph.getEdgeWeight(edge);

                newGraph.addVertex(source);
                newGraph.addVertex(target);
                var newEdge = newGraph.addEdge(source, target);
                if (newEdge != null) {
                    newGraph.setEdgeWeight(newEdge, weight);
                }
            }
        }
        return newGraph;
    }

    public static <T> Graph<T, DefaultWeightedEdge> buildUndirectedGraph(Set<T> vertices,
                                                                         Function<T, Set<T>> adjacentVertices) {
        var graph = new DefaultUndirectedWeightedGraph<T, DefaultWeightedEdge>(DefaultWeightedEdge.class);
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

    public static <T> void reduceGraph(Graph<T, DefaultWeightedEdge> graph, Set<T> irreducibleVertices) {
        var modified = true;
        while (modified) {
            modified = false;
            for (var vertex : new HashSet<>(graph.vertexSet())) {
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

    private static <T> boolean reduceVertex(Graph<T, DefaultWeightedEdge> graph, T vertex) {
        if (graph.degreeOf(vertex) == 2) {
            // Vertex degree 2 and can be reduced i.e.
            //     x <---m---> y <---n---> z
            // can be reduced to
            //     x <---m+n---> z

            // Perform the replacement
            var weight = 0; // Weight of our new edge
            T vertex1 = null; // Source of our new edge
            T vertex2 = null; // Target of our new edge
            for (var edge : graph.edgesOf(vertex)) {
                // Add the edge we're removing to the weight of our new edge
                weight += graph.getEdgeWeight(edge);

                // Find the vertex in the edge that isn't the one we're removing and bubble it up
                var edgeVertex = graph.getEdgeSource(edge);
                if (edgeVertex.equals(vertex)) {
                    edgeVertex = graph.getEdgeTarget(edge);
                }
                if (vertex1 == null) {
                    vertex1 = edgeVertex;
                } else {
                    vertex2 = edgeVertex;
                }
            }

            // Remove the reduced vertex and edges
            graph.removeVertex(vertex);

            // Create the new edge
            var edge = graph.addEdge(vertex1, vertex2);
            graph.setEdgeWeight(edge, weight);

            return true;
        }
        return false;
    }
}
