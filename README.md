Online Shop - Product Back and Cart & Order Back
This is a Java 17 project for an online shop that consists of two applications: Product Back and Cart & Order Back. The project utilizes various technologies and frameworks such as Spring Boot, Docker, Kafka, Redis, PostgreSQL, Unit Testing, QueryDSL, MongoDB TestContainers, JUnit Jupiter, AssertJ, and FeignClient.

Prerequisites
Make sure you have the following installed on your machine:

Java 17
Docker
Apache Kafka
Redis
PostgreSQL
Project Structure
The project is divided into two applications:

Product Back: This application is responsible for managing the products in the online shop.
Cart & Order Back: This application handles the shopping cart and order management functionality.
Each application has its own directory containing the necessary source code and configuration files.

Setup and Installation
Follow the steps below to set up and run the applications:

Clone the repository to your local machine:

bash
Copy code
git clone <repository_url>
Configure the applications:

Product Back:

Navigate to the product-back directory.
Update the necessary configurations in the application.properties file, such as the database connection details and Kafka settings.
Cart & Order Back:

Navigate to the cart-and-order-back directory.
Update the necessary configurations in the application.properties file, such as the database connection details, Redis settings, and Kafka settings.
Build and run the applications:

Product Back:

Open a terminal and navigate to the product-back directory.
Build the application using Maven:
bash
Copy code
mvn clean install
Run the application:
bash
Copy code
java -jar target/product-back.jar
Cart & Order Back:

Open a terminal and navigate to the cart-and-order-back directory.
Build the application using Maven:
bash
Copy code
mvn clean install
Run the application:
bash
Copy code
java -jar target/cart-and-order-back.jar
Verify that the applications are running:

Open a web browser and access the following URLs:
Product Back: http://localhost:8080
Cart & Order Back: http://localhost:8081
Testing
The project includes unit tests for both applications using JUnit Jupiter and AssertJ. The tests are located in the respective test directories.

To run the tests, execute the following command from the root directory of each application:

bash
Copy code
mvn test
Additional Components
QueryDSL
QueryDSL is used to provide a type-safe querying mechanism for the databases used in the project. The necessary configuration and usage can be found in the codebase of each application.

MongoDB TestContainers
MongoDB TestContainers is utilized to provide a lightweight and isolated MongoDB instance for integration tests. The relevant configuration and test classes can be found in the respective test directories.

FeignClient
FeignClient is used to simplify HTTP client interactions between the applications and external services. The codebase demonstrates the usage of FeignClient to communicate with other microservices or APIs.

Contributing
Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please create a GitHub issue or submit a pull request.