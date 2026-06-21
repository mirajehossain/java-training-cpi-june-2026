# Swing Components Form

## Objective

Learn core Swing UI components and basic form event handling.

## Run

```bash
mvn clean compile
mvn exec:java
```

## Main Class

- `SwingComponentsForm` (extends `JFrame`)

## Swing Components Used

1. `JFrame`: top-level window.
2. `JLabel`: static text labels.
3. `JTextField`: single-line text input (`nameField`).
4. `JCheckBox`: true/false selection (`javaCheckBox`).
5. `JComboBox<String>`: dropdown (`departmentBox`).
6. `JList<String>` + `JScrollPane`: selectable list (`subjectList`).
7. `JButton`: submit action.

## Layout and Spacing

1. `setLayout(new GridLayout(6, 2, 10, 10))` for row/column arrangement.
2. `BorderFactory.createEmptyBorder(12, 12, 12, 12)` for minimum window padding.

## Event Handling

1. `submitButton.addActionListener(...)` listens for click events.
2. Click triggers `showData()`.

## Function Walkthrough

1. Constructor builds the full UI.
2. `showData()` reads values from all components.
3. If list selection is empty, fallback text is shown.
4. Final output shown with `JOptionPane.showMessageDialog(...)`.

## Key API Notes

- `getText()` reads text from `JTextField`.
- `isSelected()` reads checkbox state.
- `getSelectedItem()` reads selected dropdown value.
- `getSelectedValue()` reads selected list item.

## Practice Ideas

1. Make `subjectList` multi-select.
2. Disable submit button until name is entered.
3. Add reset/clear button.
