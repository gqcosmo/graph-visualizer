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
    private Graph graph;

    public GraphPanel() {
        setBackground(Color.BLACK);
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int radius = 20;
        g.setFont(new Font("Courier", Font.BOLD, radius));
        Graphics2D g2 = (Graphics2D) g;
        FontMetrics fm = g2.getFontMetrics();

        if (graph == null) {
            createMessage(g2, fm);
            return;
        }

        HashSet<Edge> drawnEdges = new HashSet<>();

        for (Vertex v : graph.getVertices()) {
            g.setColor(Color.WHITE);
            g.fillOval(v.getX() - radius, v.getY() - radius, radius * 2, radius * 2);

            drawEdges(g, g2, v, drawnEdges);

            g.setColor(Color.BLACK);
            int textWidth = fm.stringWidth(v.getLabel());
            int textHeight = fm.getAscent();
            int yOffset = (int)(radius * .16);
            g.drawString(v.getLabel(), v.getX() - textWidth / 2, v.getY() + textHeight / 2 - yOffset);
        }
    }

    private void createMessage(Graphics2D g2, FontMetrics fm) {
        String message = "Create new graph to get started";
        int textWidth = fm.stringWidth(message);
        int textHeight = fm.getHeight();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() - textHeight) / 2 + fm.getAscent();
        g2.setColor(Color.WHITE);
        g2.drawString(message, x, y);
    }

    private void drawEdges(Graphics g, Graphics2D g2, Vertex v, HashSet<Edge> drawn) {
        ArrayList<Edge> edges = graph.getEdges(v);

        for (Edge edge : edges) {
            if (drawn.contains(edge)) continue;
            drawn.add(edge);

            g.setColor(Color.WHITE);
            Vertex p1 = edge.getSrc();
            Vertex p2 = edge.getDest();

            g2.setStroke(new BasicStroke((float) VertexSettings.DEFAULT_RADIUS / 10));
            g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());

            if (!edge.isWeighted()) continue;
            String weight = Double.toString(edge.getWeight());

            int midX = (p1.getX() + p2.getX()) / 2;
            int midY = (p1.getY() + p2.getY()) / 2;

            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(weight);
            int textHeight = fm.getAscent();

            g2.setColor(Color.RED);
            g2.drawString(weight, midX - textWidth / 2, midY + textHeight / 2 + textHeight);
        }
    }
}