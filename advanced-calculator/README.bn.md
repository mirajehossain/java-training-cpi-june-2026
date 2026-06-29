# Advanced Calculator (বাংলা গাইড)

## উদ্দেশ্য

Swing ব্যবহার করে একটু উন্নত calculator বানানো, যেখানে বেশি operation এবং history log থাকবে।

## Entry Point

- `com.training.advancedcalculator.Main`
- বিকল্প main class: `com.training.advancedcalculator.AdvancedCalculatorApp`

## Run করার নিয়ম

```bash
mvn clean compile
mvn exec:java
```

## UI-তে কী কী আছে

1. `JTextField`: দুইটা number input.
2. `JComboBox<String>`: operation dropdown (`+`, `-`, `*`, `/`, `%`, `^`).
3. `JButton`: Calculate এবং Clear All.
4. `JLabel`: result দেখায়.
5. `JTextArea` + `JScrollPane`: আগের calculation history দেখায়.

## Event Handling

1. Calculate button click করলে `calculate()` run হয়।
2. Clear All button click করলে `clearAll()` run হয়।

## Function গুলো কী করে

1. `calculate()`:
   - input parse করে
   - operation অনুযায়ী result বের করে
   - result label update করে
   - history area-তে line add করে

2. `clearAll()`:
   - input ফাঁকা করে
   - operation default করে
   - result reset করে
   - history clear করে

## Quick Revision

- `JComboBox` = dropdown থেকে operation নির্বাচন
- `ActionListener` = button click event handle
- `Math.pow(a, b)` = `a` এর power `b`
- `JTextArea` = multi-line history text
