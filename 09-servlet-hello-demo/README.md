# Servlet Hello Demo

## Objective

Understand servlet basics: URL mapping, request handling, and HTTP response writing.

## Build

```bash
mvn clean package
```

## Deploy

Deploy WAR to Tomcat 10+:

```text
target/servlet-hello-demo.war
```

Open:

```text
http://localhost:8080/servlet-hello-demo/hello
```

## Main Class

- `HelloServlet` (extends `HttpServlet`)

## Servlet Concepts Used

1. `@WebServlet("/hello")`: maps URL path to servlet.
2. `doGet(HttpServletRequest, HttpServletResponse)`: handles HTTP GET requests.
3. `response.setContentType("text/html")`: sets response MIME type.
4. `PrintWriter out = response.getWriter()`: writes response body.

## Request/Response Flow

1. Browser requests `/hello`.
2. Tomcat routes request to `HelloServlet`.
3. `doGet(...)` runs and writes HTML lines.
4. Browser renders returned HTML page.

## Practice Ideas

1. Add query parameter support, e.g. `/hello?name=Rahim`.
2. Return JSON with `application/json`.
3. Add a `doPost(...)` example.
