package view;
import javax.swing.*;
import java.awt.*;
import model.Graph;
import model.Vertex;
import controller.GraphController;

public class MainFrame extends JFrame {
    private GraphController graphController;
    private GraphPanel graphPanel;

    public MainFrame(Graph graph) {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        graphPanel = new GraphPanel(graph);
        graphController = new GraphController(graphPanel, graph);
        graphPanel.addMouseListener(graphController);

        add(graphPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex(new Vertex("0", new Point(350, 350)));
        new MainFrame(graph);
    }
}
