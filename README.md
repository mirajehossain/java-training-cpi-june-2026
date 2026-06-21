# Java Two-Day Training Projects

Open each folder separately as a Maven project.

## Master Reference

All components, events, layouts, JDBC, and Java concepts used across all projects explained in one file:
[JAVA-REFERENCE.md](JAVA-REFERENCE.md)

## Project list

1. 01-exception-handling-demo
2. 02-swing-components-form
3. 03-form-validation-demo
4. 04-basic-calculator
5. 05-jdbc-insert-demo
6. 06-login-system
7. 07-database-crud-demo
8. 08-phonebook-management-system
9. 09-servlet-hello-demo
10. 10-event-handling-demo
11. 11-advanced-calculator

## How to run normal projects

```bash
mvn clean compile
mvn exec:java
```

## For database projects

Run `database.sql` first.

Example:

```bash
mysql -u root -p < database.sql
```

Default MySQL setting in code:

```text
username: root
password: empty
```

If your MySQL has password, update `PASSWORD` in DBConnection.java.

## For Servlet project

```bash
mvn clean package
```

Then deploy `target/servlet-hello-demo.war` to Tomcat 10+.
