package view;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import model.Graph;
import model.Vertex;

public class GraphPanel extends JPanel {
    private final Graph graph;
    public GraphPanel(Graph graph) {
        this.graph = graph;
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int radius = 25;
        g.setFont(new Font("Courier", Font.BOLD, radius));
        FontMetrics fm = g.getFontMetrics();

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
}