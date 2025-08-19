package view;

import javax.swing.JFrame;

import controller.GraphController;

public class MainFrame extends JFrame {
    private final GraphController graphController;
    private final GraphPanel graphPanel;
    private final GraphMenuBar graphMenuBar;

    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        graphPanel = new GraphPanel();
        graphController = new GraphController(graphPanel);
        graphPanel.addMouseListener(graphController);
        add(graphPanel);

        graphMenuBar = new GraphMenuBar(graphController);
        setJMenuBar(graphMenuBar);

        setVisible(true);
    }
}
