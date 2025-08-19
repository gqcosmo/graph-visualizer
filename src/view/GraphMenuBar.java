package view;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.*;

public class GraphMenuBar extends JMenuBar {
    public GraphMenuBar(GraphController controller) {
        JMenu fileBar = new JMenu("File");
        add(fileBar);

        JMenuItem newGraph = new JMenuItem("new");
        newGraph.addActionListener(e -> {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            NewGraphDialog dialog = new NewGraphDialog(parent);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                controller.createGraph(dialog.graphType(), dialog.edgeType());
            }
        });
        fileBar.add(newGraph);

        JMenu modeBar = new JMenu("Mode: None");
        add(modeBar);

        JMenuItem addVertex = new JMenuItem("Add a vertex");
        addVertex.addActionListener(e -> {
                    controller.setState(InteractiveState.ADD_VERTEX);
                    modeBar.setText("Mode: Add Vertex");
                });

        JMenuItem addEdge = new JMenuItem("Add an edge");
        addEdge.addActionListener(e -> {
            controller.setState(InteractiveState.ADD_EDGE);
            modeBar.setText("Mode: Add Edge");
        });

        JMenuItem removeVertex = new JMenuItem("Remove a vertex");
        removeVertex.addActionListener(e -> {
            controller.setState(InteractiveState.REMOVE_VERTEX);
            modeBar.setText("Mode: Remove Vertex");
        });

        JMenuItem none = new JMenuItem("None");
        none.addActionListener(e -> {
                    controller.setState(InteractiveState.NONE);
                    modeBar.setText("Mode: None");
                });

        modeBar.add(addVertex);
        modeBar.add(addEdge);
        modeBar.add(removeVertex);
        modeBar.add(none);
    }
}
