Ecommerce Shop Admin Dashboard
==============================

The **Ecommerce Shop Admin Dashboard** is a Spring Boot application for managing an online store’s back-office. It provides an intuitive UI (using Mustache templates) alongside RESTful APIs for data operations. Key functionalities include product management, category and variant management, order monitoring, and simple sales reporting. This project leverages Spring Boot (a popular Java backend framework[flatirons.com](https://flatirons.com/blog/what-is-spring-boot-in-java/#:~:text=Spring Boot has emerged as,streamlined approach to application development)), Mustache (a “logic-less” templating engine for rendering HTML[baeldung.com](https://www.baeldung.com/spring-boot-mustache#:~:text=In this article, we’ll focus,content in Spring Boot applications)), and MySQL for data storage. Data access uses Spring Data JPA, which “makes it easy to... implement JPA-based repositories”[spring.io](https://spring.io/projects/spring-data-jpa/#:~:text=Spring Data JPA, part of,that use data access technologies)[spring.io](https://spring.io/guides/gs/accessing-data-mysql/#:~:text=This guide walks you through,could use plain Spring JDBC). For example, Spring’s official guide shows connecting Spring Boot to MySQL via Spring Data JPA[spring.io](https://spring.io/guides/gs/accessing-data-mysql/#:~:text=This guide walks you through,could use plain Spring JDBC). Overall, the architecture follows a typical MVC pattern: controllers return Mustache views or JSON, services implement business logic, and repositories handle persistence. The result is an admin console that tracks sales metrics and product data – aligning with common e-commerce dashboard designs (e.g. emphasizing revenue, orders, and top-selling products)[blog.coupler.io](https://blog.coupler.io/ecommerce-dashboards/#:~:text=,and repeat customer rates, customer).

Features
--------

*   **Product Management:** Add, update, and delete products with attributes like name, description, price, category, and variants.
    
*   **Category Management:** Create and organize product categories (e.g. “Apparel”, “Electronics”).
    
*   **Variant Management:** Define product variants (e.g. size, color) for inventory details.
    
*   **Order Monitoring:** View and manage customer orders (statuses, totals, items).
    
*   **Sales Reports:** Generate summary reports (e.g. total revenue, number of orders over a period).
    

These features align with typical e-commerce admin dashboards, which focus on sales and product insights[blog.coupler.io](https://blog.coupler.io/ecommerce-dashboards/#:~:text=,and repeat customer rates, customer).

Technologies Used
-----------------

*   **Java & Spring Boot:** Core framework (Java 17+) for building the backend services and REST APIs. Spring Boot simplifies configuration and setup[flatirons.com](https://flatirons.com/blog/what-is-spring-boot-in-java/#:~:text=Spring Boot has emerged as,streamlined approach to application development).
    
*   **Mustache:** Server-side templating engine for dynamic HTML views[baeldung.com](https://www.baeldung.com/spring-boot-mustache#:~:text=In this article, we’ll focus,content in Spring Boot applications). Spring Boot auto-configures JMustache if you add spring-boot-starter-mustache[spring.io](https://spring.io/blog/2016/11/21/the-joy-of-mustache-server-side-templates-for-the-jvm/#:~:text=Getting Started).
    
*   **Spring Data JPA:** Simplifies database access via JPA/Hibernate (auto-implemented repository interfaces)[spring.io](https://spring.io/projects/spring-data-jpa/#:~:text=Spring Data JPA, part of,that use data access technologies).
    
*   **MySQL:** Relational database for data persistence (open-source RDBMS)[quest.com](https://www.quest.com/learn/what-is-my-sql.aspx#:~:text=MySQL is an open source,,is popular for use in). Data is stored in MySQL tables (configured via application.properties).
    
*   **Spring Web & REST:** Spring MVC/Web for controllers and REST endpoints (using standard HTTP methods GET, POST, PUT, DELETE[geeksforgeeks.org](https://www.geeksforgeeks.org/java/how-to-create-a-rest-api-using-java-spring-boot/#:~:text=Representational State Transfer ,GET, POST, PUT, and DELETE)).
    
*   **Maven:** Build and dependency management tool (pom.xml) to pull in Spring Boot, Mustache, MySQL connector, etc.
    

Project Structure
-----------------

The code follows a standard Maven/Spring Boot layout:

*   src/main/java/... – Java source packages, typically organized by layer (e.g. **controller**, **service**, **repository**, **model/entity**). 
    
*   src/main/resources/templates/ – Mustache HTML templates (views). By convention, Spring Boot renders a template named index from src/main/resources/templates/index.html when a controller returns "index".
    
*   src/main/resources/static/ – Static assets (CSS, JavaScript, images) served to clients.
    
*   src/main/resources/application.properties – Configuration file (e.g. database URL, credentials). For instance, set ```spring.datasource.url=jdbc:mysql://localhost:3306/your\_db```
    
*   pom.xml – Maven build file listing dependencies (Spring Boot starters, Mustache, MySQL connector, etc.).
    

Development Setup
-----------------

1.  **Clone the repository**\
    
    ```git clone https://github.com/NafisFuad16101267/Ecommerce-Shop-Admin-Dashboard.git```
    
3.  **Install prerequisites:**
    
    *   **Java:** JDK 17 or newer.
        
    *   **MySQL:** Install MySQL Server (or compatible MariaDB). Create a database (e.g. ecom\_db).
        
4.  **Configure the database:**
    
    *   Create a new MySQL database (e.g. run CREATE DATABASE ecom\_db;).
        
    *   ```propertiesCopyEditspring.datasource.url=jdbc:mysql://localhost:3306/ecom\_db```\
        ```spring.datasource.username=YOUR DB USER```\
        ```spring.datasource.password=YOUR DB PASSWORD```\
        ```spring.jpa.hibernate.ddl-auto=update```
        
5.  ``mvn clean install`` This will compile the code and download all dependencies.
    
6.  **Run the application:**
    
    *   Via Maven: ``mvn spring-boot:run``
        
    *   Or run the main method in the Spring Boot application class from your IDE.Upon startup, Spring Boot will listen on port 8080 by default.
        
API Overview
------------

This project also exposes RESTful APIs for programmatic access. In REST terms, each entity (products, categories, variants, orders, reports) has its own endpoint. Standard HTTP methods (GET, POST, PUT, DELETE) are used for CRUD operations

*   GET /api/products – List all products.
    
*   POST /api/products – Create a new product (JSON payload with product details).
    
*   GET /api/products/{id} – Retrieve a single product by ID.
    
*   PUT /api/products/{id} – Update an existing product.
    
*   DELETE /api/products/{id} – Delete a product.
    
*   GET /api/categories – List all product categories.
    
*   POST /api/categories – Create a new category.
    
*   GET /api/categories/{id} – Retrieve a category.
    
*   PUT /api/categories/{id} – Update a category.
    
*   DELETE /api/categories/{id} – Delete a category.
    
*   GET /api/variants – List all product variants.
    
*   POST /api/variants – Create a new variant.
    
*   PUT /api/variants/{id} – Update a variant.
    
*   DELETE /api/variants/{id} – Delete a variant.
    
*   GET /api/orders – List all user orders (with status, total, etc.).
    
*   GET /api/orders/{id} – Order details by order ID.
    
*   (Optionally GET /api/users/{userId}/orders to list orders for a specific user.)
    
*   GET /api/reports/sales – Generate sales report (e.g. with query parameters like ?start=2025-07-01&end=2025-07-31 returning total revenue and order count in that period).
    

Each endpoint returns JSON data by default.

Screenshots
-----------

![image](https://github.com/user-attachments/assets/d8d2a95f-42ee-4305-ae07-02c8a8c0a415)

![image](https://github.com/user-attachments/assets/daf2f8e5-fae5-4e74-b69f-3e86f9227019)

    
License
-------

This project is released under the **MIT License**. The MIT License is a permissive open-source license that “puts very few restrictions on reuse”[en.wikipedia.org](https://en.wikipedia.org/wiki/MIT_License#:~:text=The MIT License is a,9). In practice this means anyone can use, modify, and distribute the code (even in proprietary projects) as long as the original license text and copyright notice are included.
