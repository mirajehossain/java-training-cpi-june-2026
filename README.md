# Java Two-Day Training Projects

Open each folder separately as a Maven project.

## Master Reference

All components, events, layouts, JDBC, and Java concepts used across all projects explained in one file:
[JAVA-REFERENCE.md](JAVA-REFERENCE.md)

## Project list

1. form-validation-demo
2. basic-calculator
3. login-system
4. database-crud-demo
5. phonebook-management-system
6. event-handling-demo
7. advanced-calculator

## How to run normal projects

```bash
mvn clean compile
mvn exec:java
```

## For database projects

Run `database.sql` first to create the database and tables.

### Option 1 — Terminal (MySQL CLI)

```bash
mysql -u root -p < database.sql
```

### Option 2 — XAMPP

1. Start **Apache** and **MySQL** from the XAMPP Control Panel.
2. Open your browser and go to `http://localhost/phpmyadmin`.
3. Click **Import** in the top menu.
4. Click **Choose File** and select the `database.sql` file from the project folder.
5. Click **Go** to run the import.

### Default MySQL settings in code

```text
username: root
password: empty (leave blank)
```

If your MySQL has a password set (e.g. via XAMPP), update `PASSWORD` in `DBConnection.java`.

## For Servlet project

```bash
mvn clean package
```

Then deploy `target/servlet-hello-demo.war` to Tomcat 10+.
