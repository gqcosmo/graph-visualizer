package model;

import java.awt.Point;

public class Vertex {
    private final String label;
    private final Point location;
    private final int radius;
    private final int id;
    private static int currentId = 0;

    public Vertex(String label, Point location, int radius) {
        this.label = label;
        this.location = location;
        this.radius = radius;
        this.id = ++currentId;
    }

    public String getLabel() {
        return label;
    }

    public int getX() {
        return (int)location.getX();
    }

    public int getY() {
        return (int)location.getY();
    }

    public boolean isInVertex(Point p) {
        double dx = getX() - p.getX();
        double dy = getY() - p.getY();
        double distSq = dx * dx + dy * dy;

        return distSq <= radius * radius;
    }

    public int getId() { return id; }
}
