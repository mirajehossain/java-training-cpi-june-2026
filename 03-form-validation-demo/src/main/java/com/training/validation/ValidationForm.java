package com.training.validation;

import javax.swing.*;
import java.awt.*;

public class ValidationForm extends JFrame {
    private final JTextField nameField;
    private final JTextField ageField;
    private final JTextField emailField;
    private final JTextField phoneField;

    public ValidationForm() {
        setTitle("Form Validation Demo");
        setSize(480, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        nameField = new JTextField();
        ageField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();

        JButton submitButton = new JButton("Submit");

        add(new JLabel("Name:"));
        add(nameField);

        add(new JLabel("Age:"));
        add(ageField);

        add(new JLabel("Email:"));
        add(emailField);

        add(new JLabel("Phone:"));
        add(phoneField);

        add(new JLabel(""));
        add(submitButton);

        submitButton.addActionListener(e -> validateForm());
    }

    private void validateForm() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty()) {
            showError("Name is required.");
            return;
        }

        if (ageText.isEmpty()) {
            showError("Age is required.");
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            if (age <= 0) {
                showError("Age must be positive.");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("Age must be a number.");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            showError("Invalid email address.");
            return;
        }

        if (!phone.matches("\\d{11,}")) {
            showError("Phone number must be at least 11 digits.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Validation successful.");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ValidationForm().setVisible(true));
    }
}
