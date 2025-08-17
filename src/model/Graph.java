package model;
import controller.InteractiveState;

import java.awt.Point;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Graph {
    private final HashMap<Vertex, ArrayList<Vertex>> map = new HashMap<>();

    public void addVertex(Vertex vertex) {
        if (map.containsKey(vertex)) return;
        map.put(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex src, Vertex dest) {
        if (!(map.containsKey(src)) || !(map.containsKey(dest))) return;
        map.get(src).add(dest);
        map.get(dest).add(src);
    }

    public ArrayList<Vertex> getVertices() {
        return new ArrayList<>(map.keySet());
    }

    public ArrayList<Vertex> getEdges(Vertex vertex) {
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
}