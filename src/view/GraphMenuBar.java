package view;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.*;

public class GraphMenuBar extends JMenuBar {
    public GraphMenuBar(GraphController controller) {
        JMenu menuBar = new JMenu("Mode: None");
        add(menuBar);

        JMenuItem addVertex = new JMenuItem("Add a vertex");
        addVertex.addActionListener(e -> {
                    controller.setState(InteractiveState.ADD_VERTEX);
                    menuBar.setText("Mode: Add Vertex");
                });

        JMenuItem addEdge = new JMenuItem("Add an edge");
        addEdge.addActionListener(e -> {
            controller.setState(InteractiveState.ADD_EDGE);
            menuBar.setText("Mode: Add Edge");
        });

        JMenuItem none = new JMenuItem("None");
        none.addActionListener(e -> {
                    controller.setState(InteractiveState.NONE);
                    menuBar.setText("Mode: None");
                });

        menuBar.add(addVertex);
        menuBar.add(addEdge);
        menuBar.add(none);
    }
}
