package view;

import javax.swing.*;
import model.Graph;
import controller.GraphController;

public class MainFrame extends JFrame {
    private final GraphController graphController;
    private final GraphPanel graphPanel;
    private final GraphMenuBar graphMenuBar;
    private final Graph graph = new Graph();

    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        graphPanel = new GraphPanel(graph);
        graphController = new GraphController(graphPanel, graph);
        graphPanel.addMouseListener(graphController);
        add(graphPanel);

        graphMenuBar = new GraphMenuBar(graphController);
        setJMenuBar(graphMenuBar);

        setVisible(true);
    }
}
