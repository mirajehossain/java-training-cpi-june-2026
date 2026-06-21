# Event Handling Demo

## Objective

Learn core Swing event handling patterns in one screen.

## Entry Point

- `com.training.eventhandling.Main`
- Alternate main class: `com.training.eventhandling.EventHandlingDemo`

## Run

```bash
mvn clean compile
mvn exec:java
```

## Swing Components Used

1. `JTextField`: name input.
2. `JComboBox<String>`: color dropdown.
3. `JCheckBox`: enable/disable click button.
4. `JButton`: click action with counter.
5. `JPanel`: interactive mouse area.
6. `JLabel`: status and typing count output.

## Event Listeners Demonstrated

1. `ActionListener`: button click and combo-box selection.
2. `ItemListener`: checkbox state changes.
3. `KeyListener` (via `KeyAdapter`): typing updates.
4. `MouseListener` (via `MouseAdapter`): entered/exited/clicked events.
5. `WindowListener` (via `WindowAdapter`): close confirmation dialog.

## Function Walkthrough

1. Constructor builds the UI layout and components.
2. `registerEvents()` binds all listeners.
3. `applySelectedColor()` changes panel background by dropdown choice.
4. Window close event asks confirmation before `dispose()`.

## Why Adapter Classes

Adapter classes (`KeyAdapter`, `MouseAdapter`, `WindowAdapter`) let you override only needed methods instead of implementing all listener methods.

## IntelliJ Run Button Fix (if gutter run icon is missing)

1. Open/import [10-event-handling-demo/pom.xml](10-event-handling-demo/pom.xml) as a Maven project.
2. In Maven tool window, click Reload All Maven Projects.
3. Confirm JDK is set to 17:
   - File > Project Structure > Project SDK = 17
   - Modules > Language level = 17 (or SDK default)
4. Confirm source root is marked correctly:
   - [10-event-handling-demo/src/main/java](10-event-handling-demo/src/main/java) should be blue (Sources Root).
5. Open [10-event-handling-demo/src/main/java/com/training/eventhandling/Main.java](10-event-handling-demo/src/main/java/com/training/eventhandling/Main.java) and click Run.

If still not shown, use Run > Edit Configurations > + > Application:

- Main class: `com.training.eventhandling.Main`
- Use classpath of module: `event-handling-demo`
