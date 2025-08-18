package view;

import javax.swing.*;
import java.awt.*;

public class NewGraphDialog extends JDialog {
    private final JComboBox<String> graphTypeBox;
    private final JComboBox<String> edgeTypeBox;
    private boolean confirm = false;

    public NewGraphDialog(JFrame parent) {
        super(parent, "Create New Graph", true);

        graphTypeBox = new JComboBox<>(new String[] {"Undirected", "Directed"});
        edgeTypeBox = new JComboBox<>(new String[] {"Unweighted", "Weighted"});

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.add(new JLabel("Graph Type:"));
        formPanel.add(graphTypeBox);
        formPanel.add(new JLabel("Edge Type:"));
        formPanel.add(edgeTypeBox);

        JButton createButton = new JButton("create");
        createButton.addActionListener(e -> {
            confirm = true;
            dispose();
        });

        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, formPanel);
        add(BorderLayout.SOUTH, createButton);
        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() {
        return confirm;
    }

    public String graphType() {
        return (String) graphTypeBox.getSelectedItem();
    }

    public String edgeType() {
        return (String) edgeTypeBox.getSelectedItem();
    }
}
