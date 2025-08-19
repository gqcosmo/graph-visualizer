package model;
import java.util.Iterator;

public class UndirectedGraph extends Graph {
    public UndirectedGraph(boolean isWeighted) {
        super(false, isWeighted);
    }

    @Override
    public void addEdge(Vertex src, Vertex dest, Double weight) {
        if (!(map.containsKey(src)) || !(map.containsKey(dest))) return;
        Edge edge = new Edge(src, dest, weight);
        map.get(src).add(edge);
        map.get(dest).add(edge);
        edges.add(edge);
    }

    @Override
    public boolean containsEdge(Vertex src, Vertex dest) {
        for (Edge edge : map.get(src)) {
            if (edge.getDest() == dest || edge.getSrc() == dest) return true;
        }
        return false;
    }

    @Override
    public void removeVertex(Vertex vertex) {
        Iterator<Edge> it = edges.iterator();

        while (it.hasNext()) {
            Edge edge = it.next();
            Vertex src = edge.getSrc();
            Vertex dest = edge.getDest();

            if (src == vertex || dest == vertex) {
                map.get(src).remove(edge);
                map.get(dest).remove(edge);
                it.remove();
            }
        }

        map.remove(vertex);
    }

    @Override
    public void removeEdge(Edge edge) {
        map.get(edge.getSrc()).remove(edge);
        map.get(edge.getDest()).remove(edge);
        edges.remove(edge);
    }
}
