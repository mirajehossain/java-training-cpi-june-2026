# Basic Calculator

## Objective

Build a simple Swing calculator using dropdown operations and button click events.

## Run

```bash
mvn clean compile
mvn exec:java
```

## Main Class

- `CalculatorApp` (extends `JFrame`)

## Swing Components Used

1. `JTextField`: first and second number input.
2. `JComboBox<String>`: operation selector (`+`, `-`, `*`, `/`).
3. `JButton`: calculate action.
4. `JLabel`: output display.
5. `JOptionPane`: error dialogs.

## Event Handling

1. `calculateButton.addActionListener(...)` handles button click.
2. Click triggers `calculate()`.

## Function Walkthrough

1. `calculate()` reads and parses number input.
2. Reads selected dropdown operation via `getSelectedItem()`.
3. Uses `switch` to run the chosen math operation.
4. Handles divide-by-zero with safe popup and early return.
5. Updates UI with `resultLabel.setText(...)`.
6. Handles invalid input using `NumberFormatException`.

## Key API Notes

- `Double.parseDouble(...)` converts text to number.
- `setText(...)` updates label content.
- `SwingUtilities.invokeLater(...)` creates UI on Swing event thread.

## Practice Ideas

1. Add `%` and power (`^`) operations.
2. Format result to fixed decimals.
3. Add keyboard Enter key support.
