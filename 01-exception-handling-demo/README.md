# Exception Handling Demo

## Objective

Learn how Java handles runtime errors without crashing the full program.

## Run

```bash
mvn clean compile
mvn exec:java
```

## Main Class

- `ExceptionDemo`

## What This Project Demonstrates

1. `try` block: code that may fail.
2. `catch` block: handles specific exception types.
3. `finally` block: always runs (cleanup, logging, closing resources).
4. Multiple catches for different problems:
   - `NumberFormatException` when text is not a number.
   - `ArithmeticException` when dividing by zero.
   - `NullPointerException` when calling a method on `null`.

## Function/Logic Walkthrough

1. Reads two numbers from console using `Scanner`.
2. Converts text to `int` using `Integer.parseInt(...)`.
3. Divides first number by second number.
4. Handles invalid input or divide-by-zero safely.
5. Runs a second demo block showing null reference handling.

## Key API Notes

- `Integer.parseInt(String)` throws `NumberFormatException` if input is invalid.
- `scanner.nextLine()` reads full text line from console.
- `scanner.close()` should be called when finished.

## Practice Ideas

1. Add a custom message for negative numbers.
2. Add a loop so user can retry instead of exiting.
3. Add custom exception type for business validation.
