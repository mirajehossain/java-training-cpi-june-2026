# Java Training — Master Reference Guide

Everything used across all 11 projects in one place.

---

## Table of Contents

0. [How Events Work in Swing — Concept](#0-how-events-work-in-swing--concept)
1. [JFrame — Window](#1-jframe--window)
2. [JPanel — Container/Group](#2-jpanel--containergroup)
3. [JLabel — Text Display](#3-jlabel--text-display)
4. [JTextField — Text Input](#4-jtextfield--text-input)
5. [JPasswordField — Password Input](#5-jpasswordfield--password-input)
6. [JTextArea — Multi-line Text](#6-jtextarea--multi-line-text)
7. [JButton — Clickable Button](#7-jbutton--clickable-button)
8. [JCheckBox — Checkbox](#8-jcheckbox--checkbox)
9. [JComboBox — Dropdown](#9-jcombobox--dropdown)
10. [JList — Scrollable List](#10-jlist--scrollable-list)
11. [JTable — Data Table](#11-jtable--data-table)
12. [DefaultTableModel — Table Data Model](#12-defaulttablemodel--table-data-model)
13. [JScrollPane — Scroll Wrapper](#13-jscrollpane--scroll-wrapper)
14. [JOptionPane — Popup Dialog](#14-joptionpane--popup-dialog)
15. [Layouts — GridLayout, BorderLayout, FlowLayout](#15-layouts)
16. [BorderFactory — Borders and Padding](#16-borderfactory--borders-and-padding)
17. [ActionListener — Button/Dropdown Click](#17-actionlistener--buttondropdown-click)
18. [ItemListener — Checkbox State](#18-itemlistener--checkbox-state)
    18a. [Adapter Classes — What, Why, How](#18a-adapter-classes--what-why-how)
19. [KeyAdapter — Keyboard Typing](#19-keyadapter--keyboard-typing)
20. [MouseAdapter — Mouse Events](#20-mouseadapter--mouse-events)
21. [WindowAdapter — Window Events](#21-windowadapter--window-events)
22. [ListSelectionListener — Table Row Selection](#22-listselectionlistener--table-row-selection)
23. [SwingUtilities.invokeLater — Safe UI Start](#23-swingutilitiesinvokelater--safe-ui-start)
24. [JDBC — DriverManager and Connection](#24-jdbc--drivermanager-and-connection)
25. [PreparedStatement — Safe SQL](#25-preparedstatement--safe-sql)
26. [ResultSet — Read DB Rows](#26-resultset--read-db-rows)
27. [Exception Handling — try/catch/finally](#27-exception-handling--trycatchfinally)
28. [Scanner — Console Input](#28-scanner--console-input)
29. [String Methods](#29-string-methods)
30. [Type Conversion Methods](#30-type-conversion-methods)
31. [Math Class](#31-math-class)
32. [HttpServlet — Web Servlet](#32-httpservlet--web-servlet)

---

## 0. How Events Work in Swing — Concept

**What is an Event?**
An event is something the user does: clicks a button, types text, moves the mouse, closes a window. Java detects this action and packages it into an event object.

**What is a Listener?**
A listener is a piece of code you attach to a component that says: "when this event happens, run this code". Without a listener, the click/type/move does nothing.

**The Event Cycle (step by step):**

```
User does something
       ↓
Java creates an Event object (e.g. ActionEvent, KeyEvent, MouseEvent)
       ↓
Java calls your Listener method and passes the event object
       ↓
Your code runs
```

**Example — Button Click:**

```java
// 1. Create component
JButton btn = new JButton("Click Me");

// 2. Attach listener
btn.addActionListener(e -> {
    // 3. This runs when user clicks
    System.out.println("Button was clicked!");
});
```

**What is the Event Dispatch Thread (EDT)?**
Swing is single-threaded. All UI updates must happen on one special thread called the Event Dispatch Thread (EDT). If you update the UI from a different thread, you get random visual bugs.

That is why we always write:

```java
SwingUtilities.invokeLater(() -> new MyApp().setVisible(true));
```

`invokeLater` schedules the UI creation to run safely on the EDT.

**Types of Listeners and What They Listen To:**

| Listener                           | Attached to              | Fires when                     |
| ---------------------------------- | ------------------------ | ------------------------------ |
| `ActionListener`                   | `JButton`, `JComboBox`   | Clicked or selected            |
| `ItemListener`                     | `JCheckBox`              | Checked or unchecked           |
| `KeyListener` / `KeyAdapter`       | `JTextField`             | Key pressed/released/typed     |
| `MouseListener` / `MouseAdapter`   | Any component            | Mouse clicked/entered/exited   |
| `WindowListener` / `WindowAdapter` | `JFrame`                 | Window opened/closed/minimized |
| `ListSelectionListener`            | `JTable` selection model | Row selected in table          |

---

## 1. JFrame — Window

**What it is:** The main application window. Every Swing desktop app starts with a JFrame.

**Used in:** 02, 03, 04, 06, 07, 08, 10, 11

```java
public class MyApp extends JFrame {
    public MyApp() {
        setTitle("My Window");         // title bar text
        setSize(500, 400);             // width x height in pixels
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close app on X
        setLocationRelativeTo(null);   // center on screen
        setVisible(true);              // show the window
    }
}
```

**Key methods:**

| Method                          | What it does                             |
| ------------------------------- | ---------------------------------------- |
| `setTitle(String)`              | Sets window title                        |
| `setSize(int, int)`             | Sets window width and height             |
| `setVisible(boolean)`           | Show or hide window                      |
| `setLayout(LayoutManager)`      | Sets how components are arranged         |
| `add(Component)`                | Adds a component to the window           |
| `dispose()`                     | Closes this window (without killing JVM) |
| `setDefaultCloseOperation(int)` | What happens when user clicks X          |
| `setLocationRelativeTo(null)`   | Center window on screen                  |
| `getContentPane()`              | Returns the content area                 |

---

## 2. JPanel — Container/Group

**What it is:** A container to group and organize other components. You can set its own layout separately from the main frame.

**Used in:** 07, 08, 10, 11

```java
JPanel panel = new JPanel(new FlowLayout());
panel.add(new JButton("OK"));
panel.add(new JButton("Cancel"));
add(panel, BorderLayout.SOUTH);
```

**Key methods:**

| Method                     | What it does                     |
| -------------------------- | -------------------------------- |
| `add(Component)`           | Adds component to panel          |
| `setLayout(LayoutManager)` | Sets layout of this panel        |
| `setBackground(Color)`     | Changes background color         |
| `setBorder(Border)`        | Adds border/padding around panel |

---

## 3. JLabel — Text Display

**What it is:** Non-editable text shown in the UI. Used for field labels, output results, and status text.

**Used in:** 02, 03, 04, 06, 07, 08, 10, 11

```java
JLabel label = new JLabel("Name:");
JLabel result = new JLabel("Result: ");
result.setText("Result: 42.0");   // update text later
```

**Key methods:**

| Method                        | What it does                              |
| ----------------------------- | ----------------------------------------- |
| `setText(String)`             | Update displayed text                     |
| `getText()`                   | Get current text                          |
| `setHorizontalAlignment(int)` | Align text (e.g. `SwingConstants.CENTER`) |

---

## 4. JTextField — Text Input

**What it is:** Single-line input box. User types text here.

**Used in:** 02, 03, 04, 06, 07, 08, 10, 11

```java
JTextField nameField = new JTextField();
JTextField fixedWidth = new JTextField(20); // 20 columns wide
String value = nameField.getText().trim();  // read value
nameField.setText("");                       // clear it
```

**Key methods:**

| Method                | What it does                    |
| --------------------- | ------------------------------- |
| `getText()`           | Get typed value                 |
| `setText(String)`     | Set/clear value                 |
| `trim()`              | Removes leading/trailing spaces |
| `isEmpty()`           | Check if nothing typed          |
| `addKeyListener(...)` | React to typing events          |

---

## 5. JPasswordField — Password Input

**What it is:** Same as JTextField but masks characters with dots.

**Used in:** 06, 08

```java
JPasswordField passwordField = new JPasswordField();
String password = new String(passwordField.getPassword()).trim();
```

> Note: Use `getPassword()` which returns `char[]`. Convert to String with `new String(...)`.

---

## 6. JTextArea — Multi-line Text

**What it is:** Multi-line text area. Good for showing history, logs, or long output.

**Used in:** 11

```java
JTextArea historyArea = new JTextArea();
historyArea.setEditable(false);      // read-only
historyArea.setLineWrap(true);       // wrap long lines
historyArea.setWrapStyleWord(true);  // wrap at word boundary
historyArea.append("10 + 5 = 15\n"); // add line
historyArea.setText("");             // clear all
```

**Key methods:**

| Method                 | What it does             |
| ---------------------- | ------------------------ |
| `append(String)`       | Adds text at the end     |
| `setText(String)`      | Replace all text         |
| `setEditable(boolean)` | Allow/block user editing |
| `getText()`            | Get all text             |

---

## 7. JButton — Clickable Button

**What it is:** A button the user can click to trigger an action.

**Used in:** 02, 03, 04, 06, 07, 08, 10, 11

```java
JButton submitButton = new JButton("Submit");
submitButton.addActionListener(e -> doSomething());
submitButton.setEnabled(false); // disable button
submitButton.setEnabled(true);  // enable button
```

**Key methods:**

| Method                   | What it does        |
| ------------------------ | ------------------- |
| `addActionListener(...)` | Attach click event  |
| `setEnabled(boolean)`    | Enable or disable   |
| `setText(String)`        | Change button label |

---

## 8. JCheckBox — Checkbox

**What it is:** A tick box the user can check or uncheck.

**Used in:** 02, 10

```java
JCheckBox javaCheckBox = new JCheckBox("Knows Java");
boolean selected = javaCheckBox.isSelected(); // true if ticked
javaCheckBox.addItemListener(e -> handleChange());
```

**Key methods:**

| Method                 | What it does              |
| ---------------------- | ------------------------- |
| `isSelected()`         | Returns `true` if checked |
| `setSelected(boolean)` | Set checked state         |
| `addItemListener(...)` | React to check/uncheck    |

---

## 9. JComboBox — Dropdown

**What it is:** A dropdown list. User can pick one value from given options.

**Used in:** 02, 04, 10, 11

```java
JComboBox<String> box = new JComboBox<>(new String[]{"+", "-", "*", "/"});
String selected = String.valueOf(box.getSelectedItem());
box.addActionListener(e -> handleSelection());
box.setSelectedIndex(0); // select first item
box.addItem("new option"); // add item at runtime
```

**Key methods:**

| Method                   | What it does                    |
| ------------------------ | ------------------------------- |
| `getSelectedItem()`      | Returns selected value (Object) |
| `getSelectedIndex()`     | Returns index of selected item  |
| `setSelectedIndex(int)`  | Select item by position         |
| `addItem(Object)`        | Add option at runtime           |
| `addActionListener(...)` | React to selection change       |

---

## 10. JList — Scrollable List

**What it is:** A list of items user can click to select one or more.

**Used in:** 02

```java
JList<String> subjectList = new JList<>(new String[]{"Java", "DB", "Web"});
String selected = subjectList.getSelectedValue();
add(new JScrollPane(subjectList)); // always wrap in JScrollPane
```

**Key methods:**

| Method                    | What it does               |
| ------------------------- | -------------------------- |
| `getSelectedValue()`      | Get single selected item   |
| `getSelectedValuesList()` | Get list of selected items |
| `setSelectionMode(int)`   | Single or multi select     |

---

## 11. JTable — Data Table

**What it is:** A grid view to show tabular data (like a spreadsheet). Always backed by a `DefaultTableModel`.

**Used in:** 07, 08

```java
DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name"}, 0);
JTable table = new JTable(model);
add(new JScrollPane(table)); // always wrap in JScrollPane

// read selected row
int row = table.getSelectedRow();
String value = model.getValueAt(row, 1).toString();
```

---

## 12. DefaultTableModel — Table Data Model

**What it is:** Holds and manages the data shown in a `JTable`. Rows are added/removed through it.

**Used in:** 07, 08

```java
DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Price"}, 0);
model.addRow(new Object[]{1, "Pen", 10.0});  // add row
model.setRowCount(0);                         // clear all rows
model.getValueAt(row, column);                // read cell value
```

---

## 13. JScrollPane — Scroll Wrapper

**What it is:** Adds scrollbars to a component (like `JTable`, `JList`, `JTextArea`) when content overflows.

**Used in:** 02, 07, 08, 11

```java
add(new JScrollPane(table));
add(new JScrollPane(historyArea));
```

---

## 14. JOptionPane — Popup Dialog

**What it is:** Built-in dialog boxes for messages, errors, confirmations, and input.

**Used in:** 02, 03, 04, 06, 07, 08, 10, 11

```java
// simple message
JOptionPane.showMessageDialog(this, "Saved!");

// error message
JOptionPane.showMessageDialog(this, "Error!", "Title", JOptionPane.ERROR_MESSAGE);

// yes/no confirm
int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
if (choice == JOptionPane.YES_OPTION) { ... }
```

**Dialog types:**

| Constant                          | Icon shown     |
| --------------------------------- | -------------- |
| `JOptionPane.INFORMATION_MESSAGE` | Info icon      |
| `JOptionPane.ERROR_MESSAGE`       | Error icon     |
| `JOptionPane.WARNING_MESSAGE`     | Warning icon   |
| `JOptionPane.YES_NO_OPTION`       | Yes/No buttons |

---

## 15. Layouts

**What they are:** Rules that control how components are positioned inside a frame or panel.

**Used in:** 02, 03, 04, 06, 07, 08, 10, 11

### GridLayout

Places components in a fixed grid of rows and columns.

```java
setLayout(new GridLayout(rows, cols, hGap, vGap));

// example: 4 rows, 2 columns, 10px gaps
setLayout(new GridLayout(4, 2, 10, 10));
```

### BorderLayout

Divides space into 5 zones: NORTH, SOUTH, EAST, WEST, CENTER.

```java
setLayout(new BorderLayout(hGap, vGap));
add(topPanel,    BorderLayout.NORTH);
add(table,       BorderLayout.CENTER);
add(searchPanel, BorderLayout.SOUTH);
```

### FlowLayout

Places components left-to-right, wraps to next line when full.

```java
JPanel panel = new JPanel(new FlowLayout());
// or: new FlowLayout(FlowLayout.RIGHT)
panel.add(button1);
panel.add(button2);
```

---

## 16. BorderFactory — Borders and Padding

**What it is:** Creates decorative borders or empty padding around components.

**Used in:** 02, 03, 04, 06, 07, 08, 10, 11

```java
// empty padding (top, left, bottom, right)
((JComponent) getContentPane()).setBorder(
    BorderFactory.createEmptyBorder(12, 12, 12, 12)
);

// titled border (visible line with label)
panel.setBorder(BorderFactory.createTitledBorder("Mouse Area"));
```

---

## 17. ActionListener — Button/Dropdown Click

**What it is:** The most common event listener in Swing. It fires whenever an action happens on a component — a button is clicked, a menu item is chosen, or a dropdown selection changes.

**Concept:** `ActionListener` is a functional interface with one method: `actionPerformed(ActionEvent e)`. Because it has only one method, Java allows you to write it as a lambda `e -> ...` instead of a full anonymous class.

**Used in:** 02, 03, 04, 06, 07, 08, 10, 11

```java
// Lambda style (recommended — short and clean)
button.addActionListener(e -> doSomething());

// Multi-line lambda
button.addActionListener(e -> {
    String value = field.getText();
    process(value);
});

// Old anonymous class style (equivalent but verbose)
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        doSomething();
    }
});
```

**What `e` (ActionEvent) gives you:**

```java
button.addActionListener(e -> {
    String command = e.getActionCommand(); // the button label text
    Object source = e.getSource();         // the component that was clicked
});
```

> You usually do not need `e` at all — just write `e -> doSomething()`.

---

## 18. ItemListener — Checkbox State

**What it is:** Fires when a `JCheckBox` or `JRadioButton` changes state (checked/unchecked). Different from `ActionListener` because it also tells you the new state.

**Concept:** `ItemListener` has one method: `itemStateChanged(ItemEvent e)`. It is also a functional interface, so lambda syntax works.

**Used in:** 10

```java
checkBox.addItemListener(e -> {
    boolean ticked = checkBox.isSelected();
    button.setEnabled(ticked);
});

// Reading state from the event object directly
checkBox.addItemListener(e -> {
    if (e.getStateChange() == ItemEvent.SELECTED) {
        System.out.println("Checked");
    } else {
        System.out.println("Unchecked");
    }
});
```

---

## 18a. Adapter Classes — What, Why, How

### The Problem Adapter Classes Solve

In Java, many event listeners are **interfaces** with multiple methods you must implement — even the ones you don't need.

For example, `KeyListener` is an interface with 3 methods:

```java
public interface KeyListener {
    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);
    void keyTyped(KeyEvent e);
}
```

If you only care about `keyReleased`, you still have to write the other two methods, even if they do nothing:

```java
// WITHOUT adapter — messy, repetitive
field.addKeyListener(new KeyListener() {
    @Override
    public void keyPressed(KeyEvent e) {}  // forced empty method
    @Override
    public void keyReleased(KeyEvent e) {
        // only this one matters
        label.setText("Typed: " + field.getText().length());
    }
    @Override
    public void keyTyped(KeyEvent e) {}    // forced empty method
});
```

This is noisy and confusing.

### What an Adapter Class Does

An Adapter class is a **pre-built class** that already implements the listener interface with **empty method bodies**. You just extend it and override only the method(s) you need.

```java
// WITH adapter — clean
field.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        label.setText("Typed: " + field.getText().length());
    }
    // no need to write keyPressed or keyTyped
});
```

### Adapter Classes Available in Swing

| Adapter Class   | Replaces Interface | Methods it covers                                                                                                               |
| --------------- | ------------------ | ------------------------------------------------------------------------------------------------------------------------------- |
| `KeyAdapter`    | `KeyListener`      | `keyPressed`, `keyReleased`, `keyTyped`                                                                                         |
| `MouseAdapter`  | `MouseListener`    | `mouseClicked`, `mousePressed`, `mouseReleased`, `mouseEntered`, `mouseExited`                                                  |
| `WindowAdapter` | `WindowListener`   | `windowOpened`, `windowClosing`, `windowClosed`, `windowIconified`, `windowDeiconified`, `windowActivated`, `windowDeactivated` |
| `FocusAdapter`  | `FocusListener`    | `focusGained`, `focusLost`                                                                                                      |

### Summary

- **Interface**: you must implement ALL methods
- **Adapter**: pre-fills all methods as empty — you override only what you need
- **Rule of thumb**: whenever a listener interface has more than 1 method and you only care about 1 or 2, use the Adapter class

---

## 19. KeyAdapter — Keyboard Typing

**What it is:** Responds to keyboard events on a `JTextField` or any focusable component.

**Why KeyAdapter and not KeyListener:** `KeyListener` has 3 methods. `KeyAdapter` lets you override only the one you need.

**Used in:** 10

```java
nameField.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        // fires every time user releases a key
        int count = nameField.getText().length();
        label.setText("Typed: " + count);
    }
});
```

**How to get specific key info from `KeyEvent e`:**

```java
int keyCode = e.getKeyCode();          // e.g. KeyEvent.VK_ENTER
char keyChar = e.getKeyChar();         // actual character typed
boolean isEnter = keyCode == KeyEvent.VK_ENTER;
```

**Methods you can override:**

| Method                    | When it fires                                    |
| ------------------------- | ------------------------------------------------ |
| `keyPressed(KeyEvent e)`  | When key is pressed down                         |
| `keyReleased(KeyEvent e)` | When key is released (use this for live updates) |
| `keyTyped(KeyEvent e)`    | When a printable character is typed              |

> Use `keyReleased` for live character counting because at that point the text field already has the new value.

---

## 20. MouseAdapter — Mouse Events

**What it is:** Responds to mouse actions on a panel or any component.

**Why MouseAdapter and not MouseListener:** `MouseListener` has 5 methods. `MouseAdapter` lets you override only the ones you care about.

**Used in:** 10

```java
panel.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        // fires when mouse button is pressed and released at same position
        label.setText("Clicked at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // fires when mouse cursor enters the component area
        label.setText("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // fires when mouse cursor leaves the component area
        label.setText("Mouse exited");
    }
});
```

**How to read position from `MouseEvent e`:**

```java
int x = e.getX();          // X position relative to component
int y = e.getY();          // Y position relative to component
int button = e.getButton(); // which mouse button: BUTTON1=left, BUTTON3=right
int clicks = e.getClickCount(); // single or double click
```

**All overridable methods:**

| Method                        | When it fires                     |
| ----------------------------- | --------------------------------- |
| `mouseClicked(MouseEvent e)`  | Pressed and released at same spot |
| `mousePressed(MouseEvent e)`  | Mouse button pressed down         |
| `mouseReleased(MouseEvent e)` | Mouse button released             |
| `mouseEntered(MouseEvent e)`  | Cursor enters component area      |
| `mouseExited(MouseEvent e)`   | Cursor leaves component area      |

---

## 21. WindowAdapter — Window Events

**What it is:** Listens for window lifecycle events (open, close, minimize, restore).

**Why WindowAdapter and not WindowListener:** `WindowListener` has 7 methods. `WindowAdapter` lets you override only `windowClosing` without writing 6 empty methods.

**Used in:** 10

**Important setup:** You must set `DO_NOTHING_ON_CLOSE` so Java does not close the window automatically before your confirmation dialog runs.

```java
// Required: tell JFrame to do nothing by itself on close
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

// Then handle it yourself
addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
        // fires when user clicks the X button
        int choice = JOptionPane.showConfirmDialog(
            null, "Exit?", "Confirm", JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) {
            dispose(); // close just this window
            // or: System.exit(0); // close entire app
        }
    }
});
```

**All overridable methods:**

| Method                             | When it fires                 |
| ---------------------------------- | ----------------------------- |
| `windowOpened(WindowEvent e)`      | Window appears for first time |
| `windowClosing(WindowEvent e)`     | User clicks X (before close)  |
| `windowClosed(WindowEvent e)`      | Window has been fully closed  |
| `windowIconified(WindowEvent e)`   | Window minimized to taskbar   |
| `windowDeiconified(WindowEvent e)` | Window restored from taskbar  |
| `windowActivated(WindowEvent e)`   | Window gets focus             |
| `windowDeactivated(WindowEvent e)` | Window loses focus            |

```java
// Required: set DO_NOTHING_ON_CLOSE so you can handle it yourself
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
        int choice = JOptionPane.showConfirmDialog(
            null, "Exit?", "Confirm", JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
});
```

---

## 22. ListSelectionListener — Table Row Selection

**What it is:** Fires when the user clicks a row in a `JTable`. Because the event fires multiple times per click (for internal selection changes), you always guard with `row >= 0`.

**Used in:** 07, 08

**Concept:** You attach this to the table's selection model (not the table itself), because the table separates its display (`JTable`) from its selection tracking (`ListSelectionModel`).

```java
table.getSelectionModel().addListSelectionListener(e -> {
    int row = table.getSelectedRow();
    if (row >= 0) { // guard: -1 means nothing selected
        // fill form from selected row values
        nameField.setText(model.getValueAt(row, 1).toString());
        priceField.setText(model.getValueAt(row, 2).toString());
        selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
    }
});
```

---

## 23. SwingUtilities.invokeLater — Safe UI Start

**What it is:** Schedules code to run on Swing's Event Dispatch Thread (EDT).

**Concept — Why it matters:**
Swing is not thread-safe. This means if two threads update the UI at the same time, you get flickers, incorrect rendering, or crashes. Java solves this by using a single dedicated thread for all UI work — called the **Event Dispatch Thread (EDT)**.

When your app starts, `main` runs on the main thread — NOT the EDT. If you create a `JFrame` directly in `main`, you are building the UI on the wrong thread. That works most of the time, but can randomly break.

`invokeLater` says: "schedule this code to run on the EDT when it is ready".

```java
// Correct — always do this
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new MyApp().setVisible(true));
}

// What invokeLater actually does (simplified):
// EDT queue: [... existing events ... | → your UI creation ]
// EDT runs your code when it reaches it
```

**Used in:** 02, 03, 04, 06, 07, 08, 10, 11

> Rule: Always wrap UI creation in `invokeLater` inside `main`.

---

## 24. JDBC — DriverManager and Connection

**What it is:** Java Database Connectivity (JDBC) is the standard Java API for talking to any relational database (MySQL, PostgreSQL, SQLite, etc.).

**Concept — How the pieces fit together:**

```
Java Code
    ↓
DriverManager.getConnection(url, user, pass)
    ↓  creates a
Connection  (active session to the database)
    ↓  used to create a
PreparedStatement  (parameterized SQL query)
    ↓  executes to produce a
ResultSet  (rows returned from SELECT)
```

**Used in:** 05, 06, 07, 08

```java
String URL  = "jdbc:mysql://localhost:3306/mydb";
String USER = "root";
String PASS = "";

Connection conn = DriverManager.getConnection(URL, USER, PASS);
```

**DBConnection pattern (used in 06, 07, 08):**

```java
public class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
```

**try-with-resources (auto-close connection):**

```java
try (Connection conn = DBConnection.getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {
    // use ps
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, ex.getMessage());
}
```

---

## 25. PreparedStatement — Safe SQL

**What it is:** Executes SQL queries with `?` placeholder tokens instead of pasting values directly into the SQL string.

**Concept — Why not just build SQL as a String?**

Dangerous approach (do NOT do this):

```java
// If username is:  admin' OR '1'='1
// This SQL becomes: SELECT * FROM users WHERE username = 'admin' OR '1'='1'
// That logs in anyone — SQL Injection attack!
String sql = "SELECT * FROM users WHERE username = '" + username + "'";
```

Safe approach with PreparedStatement:

```java
// Java sends the SQL structure and values separately to the database
// The database treats the value as plain data, never as SQL code
String sql = "SELECT * FROM users WHERE username = ?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, username); // safe — cannot be injected
```

**Used in:** 05, 06, 07, 08

```java
String sql = "INSERT INTO products(name, price) VALUES (?, ?)";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, "Pen");     // set 1st ?
ps.setDouble(2, 9.99);      // set 2nd ?
int rows = ps.executeUpdate(); // run INSERT/UPDATE/DELETE

String sql2 = "SELECT * FROM products WHERE name LIKE ?";
ps.setString(1, "%" + keyword + "%");
ResultSet rs = ps.executeQuery(); // run SELECT
```

**Methods:**

| Method                    | Use for                  |
| ------------------------- | ------------------------ |
| `setString(index, value)` | String value             |
| `setInt(index, value)`    | Integer value            |
| `setDouble(index, value)` | Decimal value            |
| `executeUpdate()`         | INSERT / UPDATE / DELETE |
| `executeQuery()`          | SELECT                   |

---

## 26. ResultSet — Read DB Rows

**What it is:** Holds rows returned from a SELECT query. You loop through it row by row.

**Used in:** 06, 07, 08

```java
ResultSet rs = ps.executeQuery();
while (rs.next()) {
    int id       = rs.getInt("id");
    String name  = rs.getString("name");
    double price = rs.getDouble("price");
}
```

**Key methods:**

| Method                | What it returns           |
| --------------------- | ------------------------- |
| `rs.next()`           | `true` if next row exists |
| `rs.getString("col")` | String column value       |
| `rs.getInt("col")`    | Int column value          |
| `rs.getDouble("col")` | Double column value       |

---

## 27. Exception Handling — try/catch/finally

**What it is:** A structured way to handle errors that happen at runtime, so the program shows a friendly message instead of crashing.

**Concept — Two types of errors:**

1. **Compile error:** typo or syntax mistake — Java won't even run the code.
2. **Runtime exception:** code compiles fine but crashes during execution (e.g. user types text where number is expected).

`try/catch` handles runtime exceptions gracefully.

**Flow:**

```
try block runs
    ↓
  if error throws → matching catch block runs → continues after catch
  if no error    → skips catch → continues after try
    ↓ (either way)
finally block always runs (even if catch runs)
```

**Used in:** 01, 03, 04, 05, 06, 07, 08, 11

```java
try {
    int result = 10 / 0;             // may throw
} catch (ArithmeticException ex) {
    System.out.println("Divide by zero!");
} catch (Exception ex) {
    System.out.println("Unknown: " + ex.getMessage());
} finally {
    System.out.println("Always runs");  // cleanup
}
```

**Common exception types used:**

| Exception               | Cause                      |
| ----------------------- | -------------------------- |
| `NumberFormatException` | Parsing non-numeric text   |
| `ArithmeticException`   | Divide by zero             |
| `NullPointerException`  | Method call on null object |
| `SQLException`          | Database error             |

---

## 28. Scanner — Console Input

**What it is:** Reads user input from the console (terminal).

**Used in:** 01

```java
Scanner scanner = new Scanner(System.in);
System.out.print("Enter number: ");
String line = scanner.nextLine();
int number = Integer.parseInt(line);
scanner.close(); // close when done
```

---

## 29. String Methods

**Used across all projects.**

| Method           | What it does                   | Example                                 |
| ---------------- | ------------------------------ | --------------------------------------- |
| `trim()`         | Remove leading/trailing spaces | `"  hi  ".trim()` → `"hi"`              |
| `isEmpty()`      | True if empty string           | `"".isEmpty()` → `true`                 |
| `contains(str)`  | True if substring exists       | `"a@b".contains("@")` → `true`          |
| `matches(regex)` | Validate with regex            | `"01711".matches("\\d{11,}")`           |
| `valueOf(obj)`   | Convert object to String       | `String.valueOf(box.getSelectedItem())` |
| `length()`       | Number of characters           | `"hello".length()` → `5`                |

---

## 30. Type Conversion Methods

**Used in:** 01, 03, 04, 05, 06, 07, 08, 11

| Method                       | Converts             | Throws if invalid       |
| ---------------------------- | -------------------- | ----------------------- |
| `Integer.parseInt(String)`   | Text to `int`        | `NumberFormatException` |
| `Double.parseDouble(String)` | Text to `double`     | `NumberFormatException` |
| `String.valueOf(Object)`     | Anything to `String` | —                       |
| `new String(char[])`         | `char[]` to `String` | —                       |

```java
int age    = Integer.parseInt("25");
double price = Double.parseDouble("9.99");
String text  = String.valueOf(42);
```

---

## 31. Math Class

**Used in:** 11

```java
Math.pow(2, 10);   // 2^10 = 1024.0
Math.sqrt(16);     // 4.0
Math.abs(-5);      // 5
Math.round(3.7);   // 4
Math.max(5, 10);   // 10
Math.min(5, 10);   // 5
```

---

## 32. HttpServlet — Web Servlet

**What it is:** Base class for Java web endpoints. Handles HTTP requests from a browser.

**Used in:** 09

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello!</h1>");
    }
}
```

**Key concepts:**

| Concept                        | Description                      |
| ------------------------------ | -------------------------------- |
| `@WebServlet("/path")`         | Maps URL path to this servlet    |
| `doGet(...)`                   | Handles HTTP GET request         |
| `doPost(...)`                  | Handles HTTP POST request        |
| `response.setContentType(...)` | Set response type (html, json)   |
| `response.getWriter()`         | Get writer to send response text |
| `request.getParameter("name")` | Read URL parameter               |

---

## Quick Cheat Sheet

| Need to...         | Use                               |
| ------------------ | --------------------------------- |
| Create window      | `JFrame`                          |
| Group components   | `JPanel`                          |
| Show text          | `JLabel`                          |
| Type text          | `JTextField`                      |
| Type password      | `JPasswordField`                  |
| Multi-line text    | `JTextArea`                       |
| Click a button     | `JButton` + `ActionListener`      |
| Tick/untick        | `JCheckBox` + `ItemListener`      |
| Pick from list     | `JComboBox` + `ActionListener`    |
| Show a table       | `JTable` + `DefaultTableModel`    |
| Scroll content     | `JScrollPane`                     |
| Show popup         | `JOptionPane`                     |
| Grid layout        | `GridLayout`                      |
| Zone layout        | `BorderLayout`                    |
| Flow layout        | `FlowLayout`                      |
| Inner padding      | `BorderFactory.createEmptyBorder` |
| Typing event       | `KeyAdapter.keyReleased`          |
| Mouse event        | `MouseAdapter`                    |
| Window close       | `WindowAdapter.windowClosing`     |
| Row click          | `ListSelectionListener`           |
| Start Swing safely | `SwingUtilities.invokeLater`      |
| Connect to DB      | `DriverManager.getConnection`     |
| Run SQL safely     | `PreparedStatement`               |
| Read DB rows       | `ResultSet`                       |
| Handle errors      | `try/catch/finally`               |
| Web endpoint       | `HttpServlet`                     |
