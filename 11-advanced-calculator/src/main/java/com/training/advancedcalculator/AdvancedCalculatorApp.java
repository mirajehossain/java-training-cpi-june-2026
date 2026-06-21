package com.training.advancedcalculator;

import javax.swing.*;
import java.awt.*;

public class AdvancedCalculatorApp extends JFrame {
    private final JTextField firstNumberField;
    private final JTextField secondNumberField;
    private final JComboBox<String> operationBox;
    private final JLabel resultLabel;
    private final JTextArea historyArea;

    public AdvancedCalculatorApp() {
        setTitle("Advanced Calculator");
        setSize(720, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        firstNumberField = new JTextField();
        secondNumberField = new JTextField();
        operationBox = new JComboBox<>(new String[]{"+", "-", "*", "/", "%", "^"});
        resultLabel = new JLabel("Result: ");

        formPanel.add(new JLabel("First Number:"));
        formPanel.add(firstNumberField);

        formPanel.add(new JLabel("Second Number:"));
        formPanel.add(secondNumberField);

        formPanel.add(new JLabel("Operation:"));
        formPanel.add(operationBox);

        formPanel.add(new JLabel("Output:"));
        formPanel.add(resultLabel);

        add(formPanel, BorderLayout.NORTH);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setBorder(BorderFactory.createTitledBorder("Calculation History"));
        add(historyScroll, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton calculateButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear All");
        actionPanel.add(clearButton);
        actionPanel.add(calculateButton);
        add(actionPanel, BorderLayout.SOUTH);

        calculateButton.addActionListener(e -> calculate());
        clearButton.addActionListener(e -> clearAll());
    }

    private void calculate() {
        try {
            double first = Double.parseDouble(firstNumberField.getText().trim());
            double second = Double.parseDouble(secondNumberField.getText().trim());
            String operation = String.valueOf(operationBox.getSelectedItem());

            double result;

            switch (operation) {
                case "+":
                    result = first + second;
                    break;
                case "-":
                    result = first - second;
                    break;
                case "*":
                    result = first * second;
                    break;
                case "/":
                    if (second == 0) {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero.");
                        return;
                    }
                    result = first / second;
                    break;
                case "%":
                    if (second == 0) {
                        JOptionPane.showMessageDialog(this, "Cannot modulo by zero.");
                        return;
                    }
                    result = first % second;
                    break;
                case "^":
                    result = Math.pow(first, second);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid operation.");
                    return;
            }

            resultLabel.setText("Result: " + result);
            historyArea.append(first + " " + operation + " " + second + " = " + result + "\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
        }
    }

    private void clearAll() {
        firstNumberField.setText("");
        secondNumberField.setText("");
        operationBox.setSelectedIndex(0);
        resultLabel.setText("Result: ");
        historyArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdvancedCalculatorApp().setVisible(true));
    }
}
