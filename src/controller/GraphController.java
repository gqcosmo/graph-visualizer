package controller;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import config.VertexSettings;
import model.Vertex;
import model.Graph;
import view.GraphPanel;

public class GraphController implements MouseListener {
    private final GraphPanel graphPanel;
    private final Graph graph;
    private final ArrayList<Vertex> selectedVertices;
    private InteractiveState state = InteractiveState.NONE;

    public GraphController(GraphPanel graphPanel, Graph graph) {
        this.graphPanel = graphPanel;
        this.graph = graph;
        selectedVertices = new ArrayList<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!(state == InteractiveState.ADD_EDGE)) selectedVertices.clear();

        switch (state) {
            case InteractiveState.ADD_VERTEX: { handleAddVertex(e); break; }
            case InteractiveState.ADD_EDGE: {handleAddEdge(e); break; }
            case InteractiveState.NONE:
            default:
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setState(InteractiveState state) {
        this.state = state;
    }

    private void handleAddVertex(MouseEvent e) {
        String label = JOptionPane.showInputDialog("Enter vertex label: ");

        if (label != null && !label.isEmpty()) {
            if (graph.containsLabel(label)) {
                JOptionPane.showMessageDialog(null,
                        "This label already exists",
                        "AddVertex Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                graph.addVertex(new Vertex(label, e.getPoint(), VertexSettings.DEFAULT_RADIUS));
                graphPanel.repaint();
            }
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Empty label",
                "AddVertex Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void handleAddEdge(MouseEvent e) {
        Vertex clicked = graph.getVertexAt(e.getPoint());
        if (clicked == null) return;

        selectedVertices.add(clicked);
        if (selectedVertices.size() == 2) {
            Double weight = numberInputDialog("Enter edge weight: ",
                    "Weight is not a number",
                    "AddEdge Error",
                    true);

            graph.addEdge(selectedVertices.get(0), selectedVertices.get(1), weight);
            selectedVertices.clear();
            graphPanel.repaint();
        }
    }

    private Double numberInputDialog(String text, String errorMessage, String errorTitle, boolean allowNull) {
        while (true) {
            try {
                String numStr = JOptionPane.showInputDialog(text);
                if (allowNull && numStr.isEmpty()) {
                    return null;
                }

                return Double.parseDouble(numStr);
            } catch (HeadlessException | NumberFormatException _) {
            }

            JOptionPane.showMessageDialog(null,
                    errorMessage,
                    errorTitle,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
