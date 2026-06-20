package com.training.swingform;

import javax.swing.*;
import java.awt.*;

public class SwingComponentsForm extends JFrame {
    private final JTextField nameField;
    private final JCheckBox javaCheckBox;
    private final JComboBox<String> departmentBox;
    private final JList<String> subjectList;

    public SwingComponentsForm() {
        setTitle("Swing Components Form");
        setSize(520, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        nameField = new JTextField();
        javaCheckBox = new JCheckBox("Knows Java");

        departmentBox = new JComboBox<>(new String[]{
                "Computer", "Electrical", "Civil", "Mechanical"
        });

        subjectList = new JList<>(new String[]{
                "Java", "Database", "Web Development", "Networking"
        });

        JButton submitButton = new JButton("Submit");

        add(new JLabel("Name:"));
        add(nameField);

        add(new JLabel("Department:"));
        add(departmentBox);

        add(new JLabel("Skill:"));
        add(javaCheckBox);

        add(new JLabel("Subject:"));
        add(new JScrollPane(subjectList));

        add(new JLabel(""));
        add(submitButton);

        submitButton.addActionListener(e -> showData());
    }

    private void showData() {
        String name = nameField.getText().trim();
        String department = String.valueOf(departmentBox.getSelectedItem());
        String skill = javaCheckBox.isSelected() ? "Knows Java" : "Does not know Java";
        String subject = subjectList.getSelectedValue();

        if (subject == null) {
            subject = "No subject selected";
        }

        JOptionPane.showMessageDialog(this,
                "Name: " + name + " Department: " + department +
                        " Skill: " + skill +
                        " Subject: " + subject);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingComponentsForm().setVisible(true));
    }
}
