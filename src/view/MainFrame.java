package view;

import javax.swing.*;

import controller.GraphController;
import model.DirectedGraph;
import model.Graph;
import model.UndirectedGraph;

public class MainFrame extends JFrame {
    private final GraphController graphController;
    private final GraphPanel graphPanel;
    private final GraphMenuBar graphMenuBar;
    private final Graph graph;

    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        graph = graphChoice();
        graphPanel = new GraphPanel(graph);
        graphController = new GraphController(graphPanel, graph);
        graphPanel.addMouseListener(graphController);
        add(graphPanel);

        graphMenuBar = new GraphMenuBar(graphController);
        setJMenuBar(graphMenuBar);

        setVisible(true);
    }

    private Graph graphChoice() {
        String[] graphOptions = {"Undirected", "Directed"};
        String selectedGraph = (String) JOptionPane.showInputDialog(
                null,
                "Select graph type:",
                "Graph Type",
                JOptionPane.QUESTION_MESSAGE,
                null,
                graphOptions,
                graphOptions[0]
        );

        String[] edgeOptions = {"Unweighted", "Weighted"};
        String selectedEdge = (String) JOptionPane.showInputDialog(
                null,
                "Select edge type:",
                "Edge Type",
                JOptionPane.QUESTION_MESSAGE,
                null,
                edgeOptions,
                edgeOptions[0]
        );

        Graph graph;
        boolean isWeighted = selectedEdge.equals("Weighted");

        if (selectedGraph.equals("Undirected")) {
            graph = new UndirectedGraph(isWeighted);
        } else {
            graph = new DirectedGraph(isWeighted);
        }

        return graph;
    }
}
