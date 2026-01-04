                     # FP-SP3 Api
This is a simple project, where you can see a product, and then the receipt to the product, you will also be able to see what you should bring on your product, so you will also get a packing list, if you dont have the things on the packing list, you will be provided with link and price to the diffrent items, which you should bring along.


## Tech
- Build with Java -- Version Amazon Corretto 17
- Maven
- Javalin(Web Framework)
- Postgres
- JPA/Hibernate


## How to run the project
1. Clone the project from my github
2. This is the link you should use when cloning the project https://github.com/KnogleMau/FP-SP3.git
3. When the project is cloned, you start the Application in Main.java
4. For the project to work correctly you should make a config.properties with these env in the
    -  SECRET_KEY=("Need to be 32 long something like this (ASD123WE3123SD123SDADS321HJB23P) Just make sure its 32 characters long")
    -  ISSUER=("Your name")
    -  TOKEN_EXPIRE_TIME=1800000 (Keep the Token Expire time to what it is, it should me 30 min before the time runs out)
    -  DB_NAME=(Databasen name)
    -  DB_USERNAME=(Your Username)
    -  DB_PASSWORD=(Your password)
5. Once you have your databasen running and the enviroments in your config.properties, you should be able to run the project


## API Endpoints

Base URL:  
**http://localhost:7007/api**

> All protected routes require a valid **JWT Bearer Token** in the `Authorization` header.
>
> **Roles:**
> -  **ADMIN** – full access (create, update, delete)
> -  **USER** – read-only access to trips and guides

---

### Security Endpoints

| Method | Endpoint | Description | Requires Token | Allowed Roles |
|---------|-----------|--------------|----------------|----------------|
| **POST** | `/auth/register` | Register a new user | ❌ | Public |
| **POST** | `/auth/login` | Log in and receive a JWT token | ❌ | Public |
| **GET** | `/protected/user_demo` | Test route for users | ✅ | USER |
| **GET** | `/protected/admin_demo` | Test route for admins | ✅ | ADMIN |

---

### Trip Endpoints

| Method | Endpoint         | Description | Requires Token | Allowed Roles |
|---------|------------------|--------------|----------------|----------------|
| **GET** | `/products`      | Get all trips or filter by `?category=CITY` | ✅ | USER, ADMIN |
| **GET** | `/product/{id}`  | Get a specific product by ID | ✅ | USER, ADMIN |
| **POST** | `/products`      | Create a new product | ✅ | ADMIN |
| **PUT** | `/products/{id}` | Update an existing product | ✅ | ADMIN |
| **DELETE** | `/products/{id}` | Delete a product | ✅ | ADMIN |

---

### Guide Endpoints

| Method   | Endpoint    | Description           | Requires Token | Allowed Roles |
|----------|-------------|-----------------------|----------------|-------------|
| **GET**  | `/receipts` | Retrieve all receipts | ✅ | USER, ADMIN |
| **POST** | `/receipts` | Retrieve all receipts | ✅ | ADMIN       |

### Json Format

```json
When you want to POST/UPDATE a product you need the correct format to do it.

Trip Json format
{
"name": "Copenhagen",
"startTime": "2026-04-20T09:00",
"endTime": "2026-04-20T17:00",
"location": "Denmark",
"price": 1200,
"category": "CITY"
}


If you want to register a new user or login this is the format you will use.
Register/Login Json format
{
    "username": "user",
    "password": "pass12345"
}