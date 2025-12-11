📦 E-Commerce Microservices Application

This project is a Microservices-based E-Commerce System built using Spring Boot.
The architecture follows a distributed service pattern with API Gateway, Eureka Server, Auth Service, Product Service, and Order Service.

🚀 Architecture Overview

✔ Eureka Server

Service registry where all microservices register dynamically.

✔ API Gateway

Handles routing, load-balancing, authentication forwarding, and acts as the single entry point.

✔ Auth Security Service

Responsible for:

User registration & login

JWT token generation

Authentication/authorization checks

✔ Product Service

Manages product operations:

Add / Update / Delete product

View product list

Search products

✔ Order Service

Handles:

Create order

Fetch user orders

Update order status

📂 Project Structure

E-Commerce/

│── APIGateway/

│── Auth Security/Auth-Security/

│── Eureka_Server/Eureka_Server/

│── ProductService/ProductService/

│── OrderService/OrderService/

│── README.md

└── .idea/      (ignored)

▶️ How to Run the Project
1️⃣ Start Eureka Server
cd Eureka_Server
mvn spring-boot:run

2️⃣ Start API Gateway
cd APIGateway
mvn spring-boot:run

3️⃣ Start Auth Service
cd Auth Security/Auth-Security
mvn spring-boot:run

4️⃣ Start Product Service
cd ProductService/ProductService
mvn spring-boot:run

5️⃣ Start Order Service
cd OrderService/OrderService
mvn spring-boot:run

🔐 Authentication Flow

User registers or logs in via Auth Service

Auth service issues JWT token

API Gateway validates the token on every request

Request is forwarded to internal microservices

📡 Service Ports (example)
Service	Port
Eureka Server	8761
API Gateway	8080
Auth Service	8081
Product Service	8082
Order Service	8083

(You can adjust these in application.properties)

📝 Future Enhancements

Cart Service

Payment Service

Notification Service

Docker & Kubernetes deployment

API documentation using Swagger

👨‍💻 Author

Hariharan Kumar
Microservices-based E-Commerce Project
