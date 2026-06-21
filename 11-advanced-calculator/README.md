# Advanced Calculator

## Objective

Build a stronger calculator demo using Swing with more operations and history tracking.

## Entry Point

- `com.training.advancedcalculator.Main`
- Alternate main class: `com.training.advancedcalculator.AdvancedCalculatorApp`

## Run

```bash
mvn clean compile
mvn exec:java
```

## Swing Components Used

1. `JTextField`: first and second number input.
2. `JComboBox<String>`: operation selector (`+`, `-`, `*`, `/`, `%`, `^`).
3. `JButton`: calculate and clear actions.
4. `JLabel`: current result display.
5. `JTextArea` + `JScrollPane`: calculation history.

## Event Handling

1. `calculateButton.addActionListener(...)` triggers `calculate()`.
2. `clearButton.addActionListener(...)` triggers `clearAll()`.

## Core Functions

1. `calculate()`: reads values, applies selected operation, updates result + history.
2. `clearAll()`: resets all inputs, output, and history.

## Practice Ideas

1. Add square root and absolute value operations.
2. Add keyboard Enter shortcut for Calculate.
3. Add copy-to-clipboard for selected history line.
