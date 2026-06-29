# Advanced Calculator

## Objective

Build a stronger calculator demo using Swing with a keypad layout, more operations, and history tracking.

## Entry Point

- `com.training.advancedcalculator.Main`
- Alternate main class: `com.training.advancedcalculator.AdvancedCalculatorApp`

## Run

```bash
mvn clean compile
mvn exec:java
```

## Swing Components Used

1. `JTextField`: calculator display for the active number.
2. `JButton`: keypad buttons for digits `0-9`, decimal, sign toggle, clear actions, and operators.
3. `JLabel`: current result display.
4. `JTextArea` + `JScrollPane`: calculation history.

## Event Handling

1. Keypad button clicks route through `handleButton(...)`.
2. Operator buttons store the pending calculation and update the display.
3. `=` triggers `calculate()`.
4. Clear buttons reset either the current entry or the full calculator state.

## Core Functions

1. `calculate()`: resolves the pending operation, updates the display, and appends to history.
2. `chooseOperation()`: stores the current value and selected operator.
3. `clearAll()`: resets display, output, pending operator, and history.
4. `clearEntry()`, `backspace()`, and `toggleSign()`: provide standard calculator editing controls.

## Practice Ideas

1. Add square root and absolute value operations.
2. Add keyboard shortcuts for the keypad and Enter for `=`.
3. Add copy-to-clipboard for selected history line.
