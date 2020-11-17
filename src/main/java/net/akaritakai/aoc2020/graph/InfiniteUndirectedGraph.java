package net.akaritakai.aoc2020.graph;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.graph.DefaultGraphType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public abstract class InfiniteUndirectedGraph<T> implements Graph<T, DirectedGraphEdge<T>> {
    public abstract Set<T> adjacentVertices(T vertex);

    @Override
    public abstract boolean containsVertex(T vertex);

    @Override
    public Set<DirectedGraphEdge<T>> getAllEdges(T sourceVertex, T targetVertex) {
        throw new UnsupportedOperationException("Graph is infinite");
    }

    @Override
    public DirectedGraphEdge<T> getEdge(T sourceVertex, T targetVertex) {
        if (containsEdge(sourceVertex, targetVertex)) {
            return new DirectedGraphEdge<>(sourceVertex, targetVertex);
        }
        return null;
    }

    @Override
    public Supplier<T> getVertexSupplier() {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public Supplier<DirectedGraphEdge<T>> getEdgeSupplier() {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public DirectedGraphEdge<T> addEdge(T sourceVertex, T targetVertex) {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public boolean addEdge(T sourceVertex, T targetVertex, DirectedGraphEdge<T> edge) {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public T addVertex() {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public boolean addVertex(T t) {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public boolean containsEdge(T sourceVertex, T targetVertex) {
        if (sourceVertex == null) return false;
        if (targetVertex == null) return false;
        if (!containsVertex(sourceVertex)) return false;
        if (!containsVertex(targetVertex)) return false;
        return adjacentVertices(sourceVertex).contains(targetVertex);
    }

    @Override
    public boolean containsEdge(DirectedGraphEdge<T> edge) {
        return containsEdge(edge.source(), edge.target());
    }

    @Override
    public Set<DirectedGraphEdge<T>> edgeSet() {
        throw new UnsupportedOperationException("Graph is infinite");
    }

    @Override
    public int degreeOf(T vertex) {
        if (vertex == null) throw new NullPointerException();
        if (!containsVertex(vertex)) throw new IllegalArgumentException("Vertex " + vertex + " is not in graph");
        return 2 * adjacentVertices(vertex).size();
    }

    @Override
    public Set<DirectedGraphEdge<T>> edgesOf(T vertex) {
        if (vertex == null) throw new NullPointerException();
        if (!containsVertex(vertex)) throw new IllegalArgumentException("Vertex " + vertex + " is not in graph");

        var edges = new HashSet<DirectedGraphEdge<T>>();
        for (T adjacent : adjacentVertices(vertex)) {
            edges.add(new DirectedGraphEdge<>(vertex, adjacent));
            edges.add(new DirectedGraphEdge<>(adjacent, vertex));
        }
        return edges;
    }

    @Override
    public int inDegreeOf(T vertex) {
        return degreeOf(vertex) / 2;
    }

    @Override
    public Set<DirectedGraphEdge<T>> incomingEdgesOf(T vertex) {
        if (vertex == null) throw new NullPointerException();
        if (!containsVertex(vertex)) throw new IllegalArgumentException("Vertex " + vertex + " is not in graph");

        var edges = new HashSet<DirectedGraphEdge<T>>();
        for (T adjacent : adjacentVertices(vertex)) {
            edges.add(new DirectedGraphEdge<>(adjacent, vertex));
        }
        return edges;
    }

    @Override
    public int outDegreeOf(T vertex) {
        return degreeOf(vertex) / 2;
    }

    @Override
    public Set<DirectedGraphEdge<T>> outgoingEdgesOf(T vertex) {
        if (vertex == null) throw new NullPointerException();
        if (!containsVertex(vertex)) throw new IllegalArgumentException("Vertex " + vertex + " is not in graph");

        var edges = new HashSet<DirectedGraphEdge<T>>();
        for (T adjacent : adjacentVertices(vertex)) {
            edges.add(new DirectedGraphEdge<>(vertex, adjacent));
        }
        return edges;
    }

    @Override
    public boolean removeAllEdges(Collection<? extends DirectedGraphEdge<T>> edges) {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public Set<DirectedGraphEdge<T>> removeAllEdges(T sourceVertex, T targetVertex) {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public boolean removeAllVertices(Collection<? extends T> vertices) {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public DirectedGraphEdge<T> removeEdge(T sourceVertex, T targetVertex) {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public boolean removeEdge(DirectedGraphEdge<T> edge) {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public boolean removeVertex(T vertex) {
        throw new UnsupportedOperationException("Graph is immutable");
    }

    @Override
    public Set<T> vertexSet() {
        throw new UnsupportedOperationException("Graph is infinite");
    }

    @Override
    public T getEdgeSource(DirectedGraphEdge<T> edge) {
        return edge.source();
    }

    @Override
    public T getEdgeTarget(DirectedGraphEdge<T> edge) {
        return edge.target();
    }

    @Override
    public GraphType getType() {
        return new DefaultGraphType.Builder().directed().allowSelfLoops(false).modifiable(false).build();
    }

    @Override
    public double getEdgeWeight(DirectedGraphEdge<T> edge) {
        return Graph.DEFAULT_EDGE_WEIGHT;
    }

    @Override
    public void setEdgeWeight(DirectedGraphEdge<T> edge, double weight) {
        throw new UnsupportedOperationException("Graph is immutable");
    }
}
