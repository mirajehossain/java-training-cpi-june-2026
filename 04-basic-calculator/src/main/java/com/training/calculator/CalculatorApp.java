package com.training.calculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorApp extends JFrame {
    private final JTextField firstNumberField;
    private final JTextField secondNumberField;
    private final JComboBox<String> operationBox;
    private final JLabel resultLabel;

    public CalculatorApp() {
        setTitle("Basic Calculator");
        setSize(460, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        firstNumberField = new JTextField();
        secondNumberField = new JTextField();
        operationBox = new JComboBox<>(new String[]{"+", "-", "*", "/"});
        JButton calculateButton = new JButton("Calculate");
        resultLabel = new JLabel("Result: ");

        add(new JLabel("First Number:"));
        add(firstNumberField);

        add(new JLabel("Second Number:"));
        add(secondNumberField);

        add(new JLabel("Operation:"));
        add(operationBox);

        add(new JLabel(""));
        add(calculateButton);

        add(new JLabel("Output:"));
        add(resultLabel);

        calculateButton.addActionListener(e -> calculate());
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
                default:
                    JOptionPane.showMessageDialog(this, "Invalid operation.");
                    return;
            }

            resultLabel.setText("Result: " + result);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp().setVisible(true));
    }
}
