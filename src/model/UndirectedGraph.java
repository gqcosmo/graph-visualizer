package model;

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
    }

    @Override
    public boolean containsEdge(Vertex src, Vertex dest) {
        for (Edge edge : map.get(src)) {
            if (edge.getDest() == dest || edge.getSrc() == dest) return true;
        }

        return false;
    }
}
