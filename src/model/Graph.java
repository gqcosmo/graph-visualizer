package model;

import java.awt.Point;
import java.util.*;

public class Graph {
    private final LinkedHashMap<Vertex, LinkedList<Edge>> map = new LinkedHashMap<>();

    public void addVertex(Vertex vertex) {
        if (map.containsKey(vertex)) return;
        map.put(vertex, new LinkedList<>());
    }

    public void addEdge(Vertex src, Vertex dest, Double weight) {
        if (!(map.containsKey(src)) || !(map.containsKey(dest))) return;
        Edge edge = new Edge(src, dest, weight);
        map.get(src).add(edge);
        map.get(dest).add(edge);
    }

    public LinkedList<Vertex> getVertices() {
        return new LinkedList<>(map.keySet());
    }

    public ArrayList<Edge> getEdges(Vertex vertex) {
        if (!(map.containsKey(vertex))) {
            throw new NoSuchElementException();
        }

        return new ArrayList<>(map.get(vertex));
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

    public boolean containsEdge(Vertex src, Vertex dest, boolean isDirected) {
        for (Edge edge : map.get(src)) {
            if (edge.getDest() == dest) return true;
            if (!isDirected && edge.getSrc() == dest) return true;
        }

        return false;
    }
}