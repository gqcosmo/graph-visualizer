package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
import java.util.HashSet;
import java.util.ArrayList;

import config.VertexSettings;
import model.Edge;
import model.Vertex;
import model.Graph;

public class GraphPanel extends JPanel {
    private final Graph graph;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int radius = 20;
        g.setFont(new Font("Courier", Font.BOLD, radius));
        FontMetrics fm = g.getFontMetrics();

        HashSet<Edge> drawnEdges = new HashSet<>();

        for (Vertex v : graph.getVertices()) {
            g.setColor(Color.WHITE);
            g.fillOval(v.getX() - radius, v.getY() - radius, radius * 2, radius * 2);

            drawEdges(g, v, drawnEdges);

            g.setColor(Color.BLACK);
            int textWidth = fm.stringWidth(v.getLabel());
            int textHeight = fm.getAscent();
            int yOffset = (int)(radius * .16);
            g.drawString(v.getLabel(), v.getX() - textWidth / 2, v.getY() + textHeight / 2 - yOffset);
        }
    }

    private void drawEdges(Graphics g, Vertex v, HashSet<Edge> drawn) {
        ArrayList<Edge> edges = graph.getEdges(v);

        for (Edge edge : edges) {
            if (drawn.contains(edge)) continue;
            drawn.add(edge);

            g.setColor(Color.WHITE);
            Vertex p1 = edge.getSrc();
            Vertex p2 = edge.getDest();

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke((float) VertexSettings.DEFAULT_RADIUS / 10));
            g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            g2.setStroke(new BasicStroke(1f));  // default

            if (!edge.isWeighted()) continue;
            String weight = Double.toString(edge.getWeight());

            int midX = (p1.getX() + p2.getX()) / 2;
            int midY = (p1.getY() + p2.getY()) / 2;

            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(weight);
            int textHeight = fm.getAscent();

            g2.setColor(Color.RED);
            g2.drawString(weight, midX - textWidth / 2, midY + textHeight / 2);
        }
    }
}