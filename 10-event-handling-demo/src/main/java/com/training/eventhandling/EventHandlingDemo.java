package com.training.eventhandling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EventHandlingDemo extends JFrame {
    private final JTextField nameField;
    private final JLabel statusLabel;
    private final JLabel typedLabel;
    private final JComboBox<String> colorBox;
    private final JCheckBox enableButtonCheckBox;
    private final JButton clickButton;
    private final JPanel mouseArea;
    private int clickCount = 0;

    public EventHandlingDemo() {
        setTitle("Event Handling Demo");
        setSize(640, 420);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        nameField = new JTextField();
        colorBox = new JComboBox<>(new String[]{"Default", "Red", "Green", "Blue"});
        enableButtonCheckBox = new JCheckBox("Enable button", true);
        clickButton = new JButton("Click Me");

        topPanel.add(new JLabel("Type your name:"));
        topPanel.add(nameField);

        topPanel.add(new JLabel("Pick background color:"));
        topPanel.add(colorBox);

        topPanel.add(enableButtonCheckBox);
        topPanel.add(clickButton);

        add(topPanel, BorderLayout.NORTH);

        mouseArea = new JPanel(new BorderLayout());
        mouseArea.setBorder(BorderFactory.createTitledBorder("Mouse Area"));
        mouseArea.add(new JLabel("Click, enter, or exit this area", SwingConstants.CENTER), BorderLayout.CENTER);
        add(mouseArea, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 6));
        typedLabel = new JLabel("Typed chars: 0");
        statusLabel = new JLabel("Status: Ready");
        bottomPanel.add(typedLabel);
        bottomPanel.add(statusLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        registerEvents();
    }

    private void registerEvents() {
        clickButton.addActionListener(e -> {
            clickCount++;
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                name = "Guest";
            }
            statusLabel.setText("Status: Button clicked " + clickCount + " time(s) by " + name);
        });

        enableButtonCheckBox.addItemListener(e -> {
            boolean enabled = enableButtonCheckBox.isSelected();
            clickButton.setEnabled(enabled);
            statusLabel.setText(enabled
                    ? "Status: Button enabled"
                    : "Status: Button disabled");
        });

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int length = nameField.getText().length();
                typedLabel.setText("Typed chars: " + length);
            }
        });

        colorBox.addActionListener(e -> applySelectedColor());

        mouseArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                statusLabel.setText("Status: Mouse entered area");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                statusLabel.setText("Status: Mouse exited area");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                statusLabel.setText("Status: Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        EventHandlingDemo.this,
                        "Do you want to close the app?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    private void applySelectedColor() {
        String selected = String.valueOf(colorBox.getSelectedItem());

        switch (selected) {
            case "Red":
                mouseArea.setBackground(Color.RED);
                break;
            case "Green":
                mouseArea.setBackground(new Color(220, 255, 220));
                break;
            case "Blue":
                mouseArea.setBackground(new Color(220, 235, 255));
                break;
            default:
                mouseArea.setBackground(UIManager.getColor("Panel.background"));
                break;
        }

        statusLabel.setText("Status: Color changed to " + selected);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventHandlingDemo().setVisible(true));
    }
}
