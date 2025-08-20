package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
import java.util.HashSet;

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

        drawEdges(g, g2);

        for (Vertex v : graph.getVertices()) {
            g.setColor(Color.WHITE);
            g.fillOval(v.getX() - radius, v.getY() - radius, radius * 2, radius * 2);
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

    private void drawEdges(Graphics g, Graphics2D g2) {
        HashSet<Edge> edges = graph.getEdges();

        for (Edge edge : edges) {
            g.setColor(Color.WHITE);
            Vertex p1 = edge.getSrc();
            Vertex p2 = edge.getDest();
            int x1 = p1.getX();
            int y1 = p1.getY();
            int x2 = p2.getX();
            int y2 = p2.getY();
            boolean negateWeightX = false;
            boolean negateWeightY = false;

            if (graph.containsEdge(p2, p1)) {
                int gap = 7;
                double dx = x2 - x1;
                double dy = y2 - y1;
                double len = Math.hypot(dx, dy);
                double perpX = -dy / len;
                double perpY = dx / len;

                int shiftX = (int) Math.round(perpX * gap);
                int shiftY = (int) Math.round(perpY * gap);

                if (shiftY < 0) { negateWeightY = true; }
                if (shiftX > 0) { negateWeightX = true; }

                x1 += shiftX;
                y1 += shiftY;
                x2 += shiftX;
                y2 += shiftY;
            }

            if (graph.isDirected()) {
                drawArrow(g, x1, y1, x2, y2);
            } else {
                g2.setStroke(new BasicStroke((float) VertexSettings.DEFAULT_RADIUS / 10));
                g2.drawLine(x1, y1, x2, y2);
            }

            if (!edge.isWeighted()) continue;

            int midX = (x1 + x2) / 2;
            int midY = (y1 + y2) / 2;

            FontMetrics fm = g2.getFontMetrics();
            String weight = Double.toString(edge.getWeight());
            int textWidth = fm.stringWidth(weight);
            int textHeight = fm.getAscent();

            if (negateWeightY) { textHeight *= -1; }
            if (negateWeightX) { textWidth *= -1; }

            g2.setColor(Color.RED);
            g2.drawString(weight,
                    midX - (negateWeightY ? -textWidth : textWidth) / 2,
                    midY + textHeight / 2 + (negateWeightY ? 0 : textHeight));
        }
    }

    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke((float) VertexSettings.DEFAULT_RADIUS / 10));
        g2.drawLine(x1, y1, x2, y2);

        double phi = Math.toRadians(30);
        int barb = 10;

        double dx = x2 - x1;
        double dy = y2 - y1;
        double theta = Math.atan2(dy, dx);

        int r = VertexSettings.DEFAULT_RADIUS;
        x2 -= (int) (r * Math.cos(theta));
        y2 -= (int) (r * Math.sin(theta));

        for (int j = 0; j < 2; j++) {
            double rho = theta + (j == 0 ? phi : -phi);
            int x = (int) (x2 - barb * Math.cos(rho));
            int y = (int) (y2 - barb * Math.sin(rho));
            g2.drawLine(x2, y2, x, y);
        }
    }
}