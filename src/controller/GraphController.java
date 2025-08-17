package controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import model.Vertex;
import model.Graph;
import view.GraphPanel;

public class GraphController implements MouseListener {
    private final GraphPanel graphPanel;
    private final Graph graph;
    private InteractiveState state = InteractiveState.ADD_VERTEX;

    public GraphController(GraphPanel graphPanel, Graph graph) {
        this.graphPanel = graphPanel;
        this.graph = graph;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (state) {
            case InteractiveState.ADD_VERTEX: handleAddVertex(e);
            case InteractiveState.ADD_EDGE:
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

    private void handleAddVertex(MouseEvent e) {
        String label = JOptionPane.showInputDialog("Enter vertex label: ");

        if (label != null && !label.isEmpty()) {
            if (graph.containsLabel(label)) {
                JOptionPane.showMessageDialog(null,
                        "This vertex already exists",
                        "AddVertex Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                graph.addVertex(new Vertex(label, e.getPoint()));
                graphPanel.repaint();
            }
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Empty label",
                "AddVertex Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
