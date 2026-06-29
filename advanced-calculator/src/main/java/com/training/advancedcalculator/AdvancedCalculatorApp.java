package com.training.advancedcalculator;

import javax.swing.*;
import java.awt.*;

public class AdvancedCalculatorApp extends JFrame {
    private final JTextField displayField;
    private final JLabel resultLabel;
    private final JTextArea historyArea;
    private double storedValue;
    private String pendingOperation;
    private boolean startNewNumber;

    public AdvancedCalculatorApp() {
        setTitle("Advanced Calculator");
        setSize(760, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        startNewNumber = true;

        JPanel calculatorPanel = new JPanel(new BorderLayout(10, 10));

        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        JPanel displayPanel = new JPanel(new BorderLayout(8, 8));
        displayPanel.add(displayField, BorderLayout.CENTER);
        displayPanel.add(resultLabel, BorderLayout.SOUTH);
        calculatorPanel.add(displayPanel, BorderLayout.NORTH);

        JPanel keypadPanel = new JPanel(new GridLayout(6, 4, 8, 8));
        String[] buttonLabels = {
                "C", "CE", "<", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "=",
                "%", "^", "CH", ""
        };

        for (String label : buttonLabels) {
            if (label.isEmpty()) {
                keypadPanel.add(new JLabel());
                continue;
            }

            JButton button = new JButton(label);
            button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            button.addActionListener(e -> handleButton(label));
            keypadPanel.add(button);
        }

        calculatorPanel.add(keypadPanel, BorderLayout.CENTER);
        add(calculatorPanel, BorderLayout.CENTER);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setBorder(BorderFactory.createTitledBorder("Calculation History"));
        historyScroll.setPreferredSize(new Dimension(260, 0));
        add(historyScroll, BorderLayout.EAST);
    }

    private void handleButton(String label) {
        switch (label) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                appendDigit(label);
                break;
            case ".":
                appendDecimalPoint();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "^":
                chooseOperation(label);
                break;
            case "=":
                calculate();
                break;
            case "+/-":
                toggleSign();
                break;
            case "C":
                clearAll();
                break;
            case "CE":
                clearEntry();
                break;
            case "<":
                backspace();
                break;
            case "CH":
                historyArea.setText("");
                break;
            default:
                break;
        }
    }

    private void appendDigit(String digit) {
        if (startNewNumber || "0".equals(displayField.getText())) {
            displayField.setText(digit);
            startNewNumber = false;
            return;
        }

        displayField.setText(displayField.getText() + digit);
    }

    private void appendDecimalPoint() {
        if (startNewNumber) {
            displayField.setText("0.");
            startNewNumber = false;
            return;
        }

        if (!displayField.getText().contains(".")) {
            displayField.setText(displayField.getText() + ".");
        }
    }

    private void chooseOperation(String operation) {
        try {
            double currentValue = Double.parseDouble(displayField.getText());

            if (pendingOperation != null && !startNewNumber) {
                storedValue = applyOperation(storedValue, currentValue, pendingOperation);
                displayField.setText(formatNumber(storedValue));
                resultLabel.setText("Result: " + formatNumber(storedValue));
            } else {
                storedValue = currentValue;
            }

            pendingOperation = operation;
            startNewNumber = true;
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void calculate() {
        if (pendingOperation == null) {
            resultLabel.setText("Result: " + displayField.getText());
            return;
        }

        try {
            double secondValue = Double.parseDouble(displayField.getText());
            double firstValue = storedValue;
            double result = applyOperation(firstValue, secondValue, pendingOperation);

            displayField.setText(formatNumber(result));
            resultLabel.setText("Result: " + formatNumber(result));
            historyArea.append(formatNumber(firstValue) + " " + pendingOperation + " "
                    + formatNumber(secondValue) + " = " + formatNumber(result) + "\n");

            storedValue = result;
            pendingOperation = null;
            startNewNumber = true;
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private double applyOperation(double firstValue, double secondValue, String operation) {
        switch (operation) {
            case "+":
                return firstValue + secondValue;
            case "-":
                return firstValue - secondValue;
            case "*":
                return firstValue * secondValue;
            case "/":
                if (secondValue == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
                return firstValue / secondValue;
            case "%":
                if (secondValue == 0) {
                    throw new ArithmeticException("Cannot modulo by zero.");
                }
                return firstValue % secondValue;
            case "^":
                return Math.pow(firstValue, secondValue);
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }

    private void toggleSign() {
        if ("0".equals(displayField.getText())) {
            return;
        }

        double currentValue = Double.parseDouble(displayField.getText());
        displayField.setText(formatNumber(currentValue * -1));
        startNewNumber = false;
    }

    private void backspace() {
        if (startNewNumber) {
            return;
        }

        String value = displayField.getText();
        if (value.length() <= 1 || (value.length() == 2 && value.startsWith("-"))) {
            displayField.setText("0");
            startNewNumber = true;
            return;
        }

        displayField.setText(value.substring(0, value.length() - 1));
    }

    private void clearEntry() {
        displayField.setText("0");
        resultLabel.setText("Result: ");
        startNewNumber = true;
    }

    private void clearAll() {
        clearEntry();
        storedValue = 0;
        pendingOperation = null;
        historyArea.setText("");
    }

    private String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }

        return String.valueOf(value);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdvancedCalculatorApp().setVisible(true));
    }
}
