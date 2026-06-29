# Form Validation Demo

## Objective

Learn input validation rules in a Swing form.

## Run

```bash
mvn clean compile
mvn exec:java
```

## Main Class

- `ValidationForm` (extends `JFrame`)

## Swing Components Used

1. `JTextField`: name, age, email, phone inputs.
2. `JButton`: submit action.
3. `JOptionPane`: success/error message dialogs.

## Validation Rules Implemented

1. Name is required.
2. Age is required.
3. Age must be numeric and positive.
4. Email must include `@` and `.`.
5. Phone must be at least 11 digits (`\\d{11,}`).

## Event and Functions

1. `submitButton.addActionListener(...)` triggers `validateForm()`.
2. `validateForm()` checks each rule step-by-step and returns early on first error.
3. `showError(String message)` centralizes error popup behavior.

## Key API Notes

- `Integer.parseInt(...)` for numeric conversion.
- `String.matches(...)` for regex-based validation.
- `JOptionPane.ERROR_MESSAGE` for error dialog icon/type.

## Practice Ideas

1. Add stronger email regex validation.
2. Add real-time validation while typing.
3. Highlight invalid fields with custom border color.
