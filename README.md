# Calculator Service

A RESTful API-based calculator application designed for performing basic arithmetic operations such as addition, subtraction, multiplication, and division. The application is built with Java and Spring Boot and includes structured logging, robust error handling, and support for a variety of numeric types.

---

## **Features**
- **RESTful APIs** for performing arithmetic operations:
  - Create, Read, Update, Delete (CRUD) operations.
- **Supported operations**:
  - Addition (`ADD`), Subtraction (`SUBTRACT`), Multiplication (`MULTIPLY`), Division (`DIVIDE`).
- **Structured logging**:
  - Provides clear and structured log output for debugging and monitoring.
- **Graceful error handling**:
  - Returns user-friendly error messages for invalid inputs or edge cases.
- **User precision control**:
  - Allow users to specify the precision settings.
 
## **Sample Requests**
```shell
curl --location 'localhost:8080/api/calculator/calculate' \
--header 'Content-Type: application/json' \
--data '{
   "operation": "ADD",
   "num1": "10",
   "num2": "5",
   "precisionSettings": {
      "scale": 2,
      "roundingMode": "HALF_UP"
   }
  }'
```

```shell
curl --location 'localhost:8080/api/calculator/chain' \
--header 'Content-Type: application/json' \
--data '{
    "initialValue": "7",
    "precisionSettings": {
      "scale": 2,
      "roundingMode": "HALF_UP"
    },
    "operations": [
      { "operation": "ADD", "operand": "3" },
      { "operation": "DIVIDE", "operand": "10" },
      { "operation": "MULTIPLY", "operand": "5" }
    ]
  }'
```
## **Future improvements**
- Support for more Operators and Numeric Types. (square, square root, logarithmic, exponential).
- Support for brackets (e.g (7 + 8) * 10).

