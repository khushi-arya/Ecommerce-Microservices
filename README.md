Client (Web / Postman)
        │
        ▼
   API Gateway (Spring Cloud Gateway)
        │
        │ 1️⃣ Validates JWT Token
        │ 2️⃣ Applies Security Rules
        ▼
   Eureka Server (Service Discovery)
        │
        ▼
Microservices
│
├── User Service
│      • Register User
│      • Login User
│      • Generate JWT Token
│
├── Product Service
│      • Create Product (Admin)
│      • View Products
│
├── Cart Service
│      • Add product to cart
│      • Update cart
│
├── Inventory Service
│      • Check product stock
│      • Update inventory
│
├── Order Service
│      • Create order
│      • Update order status
│
└── Payment Service
       • Process payment
