<h1 align="center">
    <img src="https://readme-typing-svg.herokuapp.com/?font=Righteous&size=35&center=true&vCenter=true&width=700&height=70&duration=4000&lines=POS+RESTful+API+with+Spring+Framework&color=800080" />
</h1>

### Description
This is a backend implementation of a POS (Point of Sale) Management System using the Spring Framework and other Java-based technologies. The API provides endpoints to manage customers, items, and orders. The project is built using Spring Boot for RESTful services, Hibernate for database interaction, and MySQL as the database. Lombok is used to reduce boilerplate code.

### Technologies Used

- Spring Framework (Web MVC): Backend framework for building RESTful services.
- Hibernate: ORM (Object Relational Mapping) for database interaction.
- Spring Data JPA: Data repository abstraction layer for database operations.
- MySQL: Relational database used for storing POS data.
- Lombok: Used to reduce boilerplate code (getters/setters, constructors) and implement logging using SLF4J.
- SLF4J: Simple Logging Facade for Java used for logging and debugging purposes.

### API Endpoints

#### Customer Management

- **GET** `/customers`: Get all customers.
- **GET** `/customers/{id}`: Get a customer by ID.
- **POST** `/customers`: Add a new customer.
- **PUT** `/customers/{id}`: Update a customer by ID.
- **DELETE** `/customers/{id}`: Delete a customer by ID.

#### Item Management

- **GET** `/items`: Get all items.
- **GET** `/items/{id}`: Get an item by ID.
- **POST** `/items`: Add a new item.
- **PUT** `/items/{id}`: Update an item by ID.
- **DELETE** `/items/{id}`: Delete an item by ID.

#### Order Management

- **GET** `/orders`: Get all orders.
- **GET** `/orders/{id}`: Get an order by ID.
- **POST** `/orders`: Create a new order.

### API Documentation

You can view the detailed API documentation with example requests and responses [here](https://documenter.getpostman.com/view/36189302/2sAXxV4pP3).

### Logging Configuration

The application employs a robust logging mechanism with different logging levels:

- **INFO**: General application flow.
- **DEBUG**: Detailed debugging information.
- **ERROR**: Error events of considerable importance that might still allow the application to continue running.
- **WARN**: Potentially harmful situations.

### Clone Repository
<ul>
  <li>Clone the repository:
    <br>git clone https://github.com/kawodyaarachchige/POS-backend-spring.git
  </li>
  <li>Configure the Database:
    <br>Set up your MySQL database.
  </li>
  <li>Update Hibernate Configuration:
    <br>Update and configure the Hibernate settings for MySQL. Ensure the correct JDBC URL, username, and password are set for your MySQL database.
  </li>
  <li>Update Logger Configuration:
    <br>Update the logger configuration for the application in logback.xml.
  </li>
  <li>Build & Deploy:
    <br>Build the project using Maven.
    <br>Run the Spring application.
  </li>
</ul>

### License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.

 Happy Coding :)
