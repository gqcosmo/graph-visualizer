package model;

import java.awt.Point;

public class Vertex {
    private final String label;
    private final Point location;

    public Vertex(String label, Point location) {
        this.label = label;
        this.location = location;
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
}
