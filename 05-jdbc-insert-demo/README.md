# JDBC Insert Demo

## Objective

Learn basic JDBC database insert using `PreparedStatement`.

## Setup

1. Create DB/table from SQL file:

```bash
mysql -u root -p < database.sql
```

2. Verify DB credentials in code:
   - URL: `jdbc:mysql://localhost:3306/java_training_db`
   - User: `admin`
   - Password: `testpass1`

## Run

```bash
mvn clean compile
mvn exec:java
```

## Main Class

- `JdbcInsertDemo`

## JDBC Components Used

1. `DriverManager.getConnection(...)`: opens DB connection.
2. `Connection`: active DB session.
3. `PreparedStatement`: parameterized SQL query object.
4. `executeUpdate()`: runs insert/update/delete and returns affected row count.

## Function Walkthrough

1. Defines SQL template: `INSERT INTO students(name, department) VALUES (?, ?)`.
2. Creates connection and prepared statement in try-with-resources.
3. Binds values using `ps.setString(...)`.
4. Executes insert and checks returned row count.
5. Handles errors in catch block.

## Why PreparedStatement

1. Safer than string concatenation (prevents SQL injection patterns).
2. Cleaner syntax for dynamic values.
3. Better readability and maintainability.

## Practice Ideas

1. Read name/department from console.
2. Add insert for multiple rows in loop.
3. Add query to print inserted data.
