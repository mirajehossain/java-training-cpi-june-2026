# Database CRUD Demo

## Objective

Build a full desktop CRUD app (Create, Read, Update, Delete) with Swing + JDBC.

## Setup

1. Create DB/table:

```bash
mysql -u root -p < database.sql
```

2. Check DB settings in `DBConnection`:
   - URL: `jdbc:mysql://localhost:3306/crud_demo_db`
   - User: `admin`
   - Password: `testpass1`

## Run

```bash
mvn clean compile
mvn exec:java
```

## Classes and Responsibilities

1. `Main`: starts Swing UI on event thread.
2. `ProductCrudFrame` (`JFrame`): UI + CRUD + search logic.
3. `DBConnection`: provides JDBC connection.

## Swing Components Used

1. `JTextField`: name, price, search inputs.
2. `JButton`: add, update, delete, clear, search, refresh.
3. `JTable` + `DefaultTableModel`: product table view and data model.
4. `JScrollPane`: table scrolling.
5. `JPanel` with `BorderLayout`, `GridLayout`, `FlowLayout`.
6. `JOptionPane`: user messages and errors.

## Event Handling

1. Button events call `addProduct()`, `updateProduct()`, `deleteProduct()`, `clearForm()`, `searchProducts()`, `loadProducts()`.
2. Table row selection event calls `fillFormFromSelectedRow()`.

## Core Functions

1. `validateForm()`: required name + numeric positive price.
2. `addProduct()`: insert new product.
3. `loadProducts()`: read all products into table.
4. `updateProduct()`: update selected row by `id`.
5. `deleteProduct()`: remove selected row by `id`.
6. `searchProducts()`: filter by product name with SQL `LIKE`.
7. `clearForm()`: reset selection and input fields.

## JDBC Pattern Used

1. Open connection in try-with-resources.
2. Use `PreparedStatement` placeholders (`?`).
3. Execute SQL.
4. Map `ResultSet` to table model.
5. Handle exceptions with user-friendly popup.
