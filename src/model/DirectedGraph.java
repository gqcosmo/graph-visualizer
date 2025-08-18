package model;

public class DirectedGraph extends Graph {
    public DirectedGraph(boolean isWeighted) {
        super(true, isWeighted);
    }

    @Override
    public void addEdge(Vertex src, Vertex dest, Double weight) {
        if (!(map.containsKey(src)) || !(map.containsKey(dest))) return;
        Edge edge = new Edge(src, dest, weight);
        map.get(src).add(edge);
    }

    @Override
    public boolean containsEdge(Vertex src, Vertex dest) {
        return false;
    }
}
