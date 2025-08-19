package controller;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.geom.Line2D;

import config.VertexSettings;
import model.DirectedGraph;
import model.Graph;
import model.UndirectedGraph;
import model.Vertex;
import model.Edge;
import view.GraphPanel;

public class GraphController implements MouseListener {
    private Graph graph;
    private final GraphPanel graphPanel;
    private final HashSet<Vertex> selectedVertices;
    private InteractiveState state = InteractiveState.NONE;

    public GraphController(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        selectedVertices = new HashSet<>();
    }

    public void createGraph(String graphType, String edgeType) {
        boolean isDirected = graphType.equals("Directed");
        boolean isWeighted = edgeType.equals("Weighted");
        graph = isDirected ? new DirectedGraph(isWeighted) : new UndirectedGraph(isWeighted);
        graphPanel.setGraph(graph);
        graphPanel.repaint();
        selectedVertices.clear();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (graph == null) return;
        if (!(state == InteractiveState.ADD_EDGE)) selectedVertices.clear();

        switch (state) {
            case InteractiveState.ADD_VERTEX: { handleAddVertex(e); break; }
            case InteractiveState.ADD_EDGE: {handleAddEdge(e); break; }
            case InteractiveState.REMOVE_VERTEX: { handleRemoveVertex(e); break; }
            case InteractiveState.REMOVE_EDGE: { handleRemoveEdge(e); break; }
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
                e.getComponent().repaint();
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
            Iterator<Vertex> it = selectedVertices.iterator();
            Vertex src = it.next();
            Vertex dest = it.next();

            if (graph.containsEdge(src, dest)) {
                selectedVertices.clear();
                return;
            }

            Double weight = null;

            if (graph.isWeighted()) {
                weight = numberInputDialog("Enter edge weight: ",
                        "Weight is not a number",
                        "AddEdge Error",
                        true);
            }

            graph.addEdge(src, dest, weight);
            graphPanel.repaint();
            selectedVertices.clear();
        }
    }

    private void handleRemoveVertex(MouseEvent e) {
        Vertex clicked = graph.getVertexAt(e.getPoint());
        if (clicked == null) return;

        graph.removeVertex(clicked);
        graphPanel.repaint();
    }

    private void handleRemoveEdge(MouseEvent e) {
        HashSet<Edge> edges = graph.getEdges();

        for (Edge edge : edges) {
            int p1_x = edge.getSrc().getX();
            int p1_y = edge.getSrc().getY();
            int p2_x = edge.getDest().getX();
            int p2_y = edge.getDest().getY();

            double dist = Line2D.ptSegDist(p1_x, p1_y, p2_x, p2_y, e.getX(), e.getY());

            if (dist < 2) {
                graph.removeEdge(edge);
                graphPanel.repaint();
                return;
            }
        }
    }

    private Double numberInputDialog(String text, String errorMessage, String errorTitle, boolean allowNull) {
        while (true) {
            try {
                String numStr = JOptionPane.showInputDialog(text);
                if (allowNull && (numStr == null || numStr.isEmpty())) {
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
