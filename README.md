# Sudoku API --- Java 17 + Spring Boot 3

This project provides a RESTful backend service for generating,
validating, and solving Sudoku puzzles.\
It is built with **Java 17**, **Spring Boot 3**, and **Maven**, and is
designed to be consumed by mobile/web clients such as a React Native +
Expo frontend.

------------------------------------------------------------------------

## ✅ Features

### ✔ Generate Sudoku puzzles

`GET /api/sudoku/generate?difficulty=easy|medium|hard`

### ✔ Validate Sudoku boards

`POST /api/sudoku/validate`\
Checks for conflicts and returns a 9×9 boolean matrix of invalid
positions.

### ✔ Solve Sudoku puzzles

`POST /api/sudoku/solve`\
Returns the solved board (if solvable).

### ✔ Health endpoint

`GET /api/health`\
Quick backend liveness check.

------------------------------------------------------------------------

## ✅ Requirements

-   **Java 17+**
-   **Maven 3.8+**
-   Recommended IDE: IntelliJ IDEA (Community or Ultimate)
-   Port **8080** must be available

------------------------------------------------------------------------

## ✅ Project Structure

    src/main/java/com.coolcsn.sudoku_api/
    │
    ├── controller/
    │   └── SudokuController.java     # REST endpoints
    │
    ├── service/
    │   └── SudokuService.java        # Business logic
    │
    ├── logic/
    │   ├── SudokuSolver.java         # Backtracking solver
    │   └── SudokuValidator.java      # Conflict detection
    │
    ├── model/
    │   └── Grid.java                 # Request/response model
    │
    └── SudokuApiApplication.java     # Main entry point

------------------------------------------------------------------------

## ✅ How to Run

### **1. Clone the project**

``` sh
git clone https://github.com/your-username/sudoku-api.git
cd sudoku-api
```

### **2. Build the project**

``` sh
mvn clean install
```

### **3. Run the backend**

``` sh
mvn spring-boot:run
```

Spring Boot starts at:

    http://localhost:8080

------------------------------------------------------------------------

## ✅ API Endpoints

### **Health Check**

    GET /api/health

Response:

    Sudoku API is running

------------------------------------------------------------------------

### **Generate Puzzle**

    GET /api/sudoku/generate?difficulty=easy

Response:

``` json
[
  [0,0,0,2,6,0,7,0,1],
  [6,8,0,0,7,0,0,9,0],
  ...
]
```

------------------------------------------------------------------------

### **Validate Board**

    POST /api/sudoku/validate

Body:

``` json
{
  "cells": [[...9 columns...], [...], ...]
}
```

Response:

``` json
{
  "valid": false,
  "conflicts": [
    [false, true, false, ...],
    ...
  ]
}
```

------------------------------------------------------------------------

### **Solve Puzzle**

    POST /api/sudoku/solve

Body:

``` json
{
  "cells": [[...]]
}
```

Response:

``` json
{
  "solvable": true,
  "cells": [[1,2,3,...], ...]
}
```

------------------------------------------------------------------------

## ✅ Common Development Commands

### Compile and test:

``` sh
mvn clean test
```

### Run with auto-restart (Spring DevTools):

``` sh
mvn spring-boot:run
```

### Build JAR:

``` sh
mvn package
```

The JAR will be created in:

    target/sudoku-api-1.0.0.jar

Run it:

``` sh
java -jar target/sudoku-api-1.0.0.jar
```

------------------------------------------------------------------------

## ✅ Cross-Origin (CORS)

The backend already supports requests from mobile and web clients.

If you want to manually enable CORS:

``` java
@CrossOrigin(origins = "*")
```

------------------------------------------------------------------------

## ✅ Using With React Native Frontend

Set API base:

-   **Android Emulator:** `http://10.0.2.2:8080`
-   **Web Browser:** `http://localhost:8080`
-   **Physical device:** `http://<your-local-LAN-IP>:8080`

------------------------------------------------------------------------

## ✅ License

MIT License --- free to use and modify.

------------------------------------------------------------------------

## ✅ Author

Stoyan Cheresharov\
Plovdiv University "Paisii Hilendarski"\
Department of Software Technologies
