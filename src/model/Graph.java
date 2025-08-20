package model;

import java.awt.Point;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.HashSet;

public abstract class Graph {
    protected final LinkedHashMap<Vertex, LinkedList<Edge>> map = new LinkedHashMap<>();
    protected final HashSet<Edge> edges;
    private final boolean IS_DIRECTED;
    private final boolean IS_WEIGHTED;

    public Graph(boolean isDirected, boolean isWeighted) {
        edges = new HashSet<>();
        IS_DIRECTED = isDirected;
        IS_WEIGHTED = isWeighted;
    }

    public abstract void addEdge(Vertex src, Vertex dest, Double weight);
    public abstract boolean containsEdge(Vertex src, Vertex dest);
    public abstract void removeVertex(Vertex vertex);
    public abstract void removeEdge(Edge edge);

    public void addVertex(Vertex vertex) {
        if (map.containsKey(vertex)) return;
        map.put(vertex, new LinkedList<>());
    }

    public LinkedList<Vertex> getVertices() {
        return new LinkedList<>(map.keySet());
    }

    public HashSet<Edge> getEdges() {
        return new HashSet<>(edges);
    }

    public boolean containsLabel(String label) {
        for (Vertex v : map.keySet()) {
            if (v.getLabel().equals(label))
                return true;
        }
        return false;
    }

    public Vertex getVertexAt(Point p) {
        for (Vertex v : map.reversed().keySet()) {
            if (v.isInVertex(p)) return v;
        }
        return null;
    }

    public boolean isDirected() { return IS_DIRECTED; }
    public boolean isWeighted() { return IS_WEIGHTED; }
}
