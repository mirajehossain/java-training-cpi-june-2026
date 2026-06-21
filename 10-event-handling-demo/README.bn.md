# Event Handling Demo (বাংলা গাইড)

## উদ্দেশ্য

এই প্রজেক্টে এক স্ক্রিনে Swing-এর গুরুত্বপূর্ণ event handling pattern দেখানো হয়েছে, যাতে beginner রা সহজে বুঝতে পারে কোন event হলে কোন code run হয়।

## Entry Point (যেখান থেকে app চালু হয়)

- `com.training.eventhandling.Main`
- বিকল্প main class: `com.training.eventhandling.EventHandlingDemo`

## Run করার নিয়ম

```bash
mvn clean compile
mvn exec:java
```

## UI-তে কোন কোন Swing Component আছে

1. `JTextField`:
   - নাম টাইপ করার input box
2. `JComboBox<String>`:
   - dropdown (Default/Red/Green/Blue)
3. `JCheckBox`:
   - button enable/disable করা
4. `JButton`:
   - click event trigger করে
5. `JPanel`:
   - mouse event দেখানোর এলাকা
6. `JLabel`:
   - status message এবং typed character count দেখায়

## কোন কোন Event Listener ব্যবহার হয়েছে

1. `ActionListener`
   - button click
   - dropdown selection change
2. `ItemListener`
   - checkbox checked/unchecked
3. `KeyListener` (via `KeyAdapter`)
   - typing হলে count update
4. `MouseListener` (via `MouseAdapter`)
   - mouse entered/exited/clicked
5. `WindowListener` (via `WindowAdapter`)
   - window close করার আগে confirmation

## Function গুলো কী কাজ করে

1. `EventHandlingDemo()` constructor
   - পুরো UI বানায়
   - panel/layout/component সেট করে
   - শেষে `registerEvents()` call করে

2. `registerEvents()`
   - সব event listener bind করে
   - button, checkbox, textfield, dropdown, mouse area, window close সব event এখানে attach করা

3. `applySelectedColor()`
   - dropdown থেকে color নিয়ে mouseArea background color change করে

4. `main(String[] args)`
   - `SwingUtilities.invokeLater(...)` দিয়ে UI thread-এ window open করে

## সহজ ভাষায় Event Flow

1. App চালু হলে window দেখায়
2. Button click করলে status text update হয়
3. Checkbox off করলে button disable হয়
4. Name field-এ টাইপ করলে character count বাড়ে/কমে
5. Dropdown color change করলে panel color বদলায়
6. Window close করলে confirm dialog আসে

## Adapter Class কেন ব্যবহার করা হয়েছে

`KeyAdapter`, `MouseAdapter`, `WindowAdapter` ব্যবহার করলে listener interface-এর সব method লিখতে হয় না। প্রয়োজনীয় method-গুলোই override করলেই হয়।

## IntelliJ-তে Run button না দেখালে

1. `pom.xml` Maven project হিসেবে import/reload করুন
2. Project SDK/JDK = 17 দিন
3. `src/main/java` কে Sources Root করুন
4. `Main.java` খুলে run দিন

## Quick Revision (1 minute)

- `ActionListener` = click/select
- `ItemListener` = checkbox state
- `KeyAdapter` = typing event
- `MouseAdapter` = mouse movement/click
- `WindowAdapter` = close confirmation
- `invokeLater` = Swing UI thread safe start
