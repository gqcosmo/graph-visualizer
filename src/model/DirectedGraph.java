package model;
import java.util.Iterator;

public class DirectedGraph extends Graph {
    public DirectedGraph(boolean isWeighted) {
        super(true, isWeighted);
    }

    @Override
    public void addEdge(Vertex src, Vertex dest, Double weight) {
        if (!(map.containsKey(src)) || !(map.containsKey(dest))) return;
        Edge edge = new Edge(src, dest, weight);
        map.get(src).add(edge);
        edges.add(edge);
    }

    @Override
    public boolean containsEdge(Vertex src, Vertex dest) {
        for (Edge edge : map.get(src)) {
            if (edge.getDest() == dest) return true;
        }
        return false;
    }

    @Override
    public void removeVertex(Vertex vertex) {
        for (Edge edge : map.get(vertex)) {
            edges.remove(edge);
        }

        for (Vertex v : map.keySet()) {
            if (v == vertex) continue;

            Iterator<Edge> it = map.get(v).iterator();
            while (it.hasNext()) {
                Edge edge = it.next();

                if (edge.getDest() == vertex) {
                    it.remove();
                    edges.remove(edge);
                }
            }
        }

        map.remove(vertex);
    }

    @Override
    public void removeEdge(Edge edge) {
        map.get(edge.getSrc()).remove(edge);
        edges.remove(edge);
    }
}
