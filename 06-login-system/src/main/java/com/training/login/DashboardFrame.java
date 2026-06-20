package com.training.login;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    public DashboardFrame() {
        setTitle("Dashboard");
        setSize(420, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome! Login successful.", SwingConstants.CENTER);
        JButton logoutButton = new JButton("Logout");

        add(label, BorderLayout.CENTER);
        add(logoutButton, BorderLayout.SOUTH);

        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }
}
