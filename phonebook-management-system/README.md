# Phonebook Management System

## Objective

Learn a two-screen Swing app with login + contact management CRUD.

## Setup

1. Create database tables and seed data:

```bash
mysql -u root -p < database.sql
```

2. Check DB settings in `DBConnection`:
   - URL: `jdbc:mysql://localhost:3306/phonebook_db`
   - User: `admin`
   - Password: `testpass1`

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

1. `Main`: starts login window.
2. `LoginFrame` (`JFrame`): authenticates user.
3. `PhonebookFrame` (`JFrame`): contact CRUD + search table.
4. `DBConnection`: creates database connection.

## Swing Components Used

1. `JTextField`: name, phone, email, address, search, username.
2. `JPasswordField`: password input.
3. `JButton`: login/exit and CRUD/search actions.
4. `JTable` + `DefaultTableModel`: contact list display.
5. `JScrollPane`: table scroll support.
6. `JOptionPane`: feedback/errors.

## Event Handling

1. Login screen buttons call `login()` and `System.exit(0)`.
2. CRUD buttons call `addContact()`, `updateContact()`, `deleteContact()`, `clearForm()`.
3. Search buttons call `searchContacts()` and `loadContacts()`.
4. Table selection listener fills form from selected row.

## Core Functions

1. `validateForm()`: checks required name and phone.
2. `addContact()`: inserts contact.
3. `loadContacts()`: loads all contacts into table.
4. `fillFormFromSelectedRow()`: sets input fields from selected table row.
5. `updateContact()`: updates by selected `id`.
6. `deleteContact()`: deletes by selected `id`.
7. `searchContacts()`: searches by name/phone/email.
8. `clearForm()`: resets form and selection.

## Key API Notes

- `LIKE ?` + `%keyword%` supports partial search.
- `selectedId` tracks currently selected DB row for update/delete.
