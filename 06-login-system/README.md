# Login System

## Objective

Learn a basic Swing login flow with JDBC authentication and screen navigation.

## Setup

1. Create database and sample data:

```bash
mysql -u root -p < database.sql
```

2. Check DB settings in `DBConnection`:
   - URL: `jdbc:mysql://localhost:3306/login_db`
   - User: `root`
   - Password: empty string by default

## Run

```bash
mvn clean compile
mvn exec:java
```

## Test Credential

```text
username: admin
password: 1234
```

## Classes and Responsibilities

1. `Main`: starts Swing UI using `SwingUtilities.invokeLater(...)`.
2. `LoginFrame` (`JFrame`): login form UI + login button events.
3. `DashboardFrame` (`JFrame`): success screen and logout action.
4. `DBConnection`: central helper to get JDBC `Connection`.

## Swing Components Used

1. `JFrame`: main windows.
2. `JTextField`: username input.
3. `JPasswordField`: password input.
4. `JButton`: login, exit, logout actions.
5. `JLabel`: form labels and welcome text.
6. `JOptionPane`: popup feedback messages.

## Event Handling

1. `loginButton.addActionListener(...)` calls `login()`.
2. `exitButton.addActionListener(...)` exits app.
3. `logoutButton.addActionListener(...)` opens login screen and closes dashboard.

## Function Walkthrough

1. `login()` reads and trims username/password.
2. Validates non-empty values.
3. Prepares SQL: `SELECT * FROM users WHERE username = ? AND password = ?`.
4. Uses `PreparedStatement` to bind values safely.
5. If result exists, opens `DashboardFrame`; otherwise shows invalid message.

## Key API Notes

- `new String(passwordField.getPassword())` converts char array to `String`.
- `dispose()` closes current frame without killing JVM.
- `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)` exits whole app when frame closes.
