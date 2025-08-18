package model;

public class Edge {
    private final Vertex src;
    private final Vertex dest;
    private final Double weight;

    public Edge(Vertex src, Vertex dest, Double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public boolean isWeighted() {
        return weight != null;
    }

    public Double getWeight() {
        return weight;
    }

    public Vertex getSrc() {
        return src;
    }

    public Vertex getDest() {
        return dest;
    }
}
