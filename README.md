# kitchen-sink - Members Management Spring Boot Application

This is a Spring Boot application for managing members with RESTful endpoints to list members, get a member by ID, and create a new member.

## Table of Contents

- [Requirements](#requirements)
- [Setup](#setup)
- [Endpoints](#endpoints)
- [Project Structure](#project-structure)
- [Running the Application](#running-the-application)
- [License](#license)

## Requirements

- Java 22
- Spring Boot 3.3.2
- Maven 4.0.0

## Setup

1. **Clone the repository:**

   ```sh
   git clone https://github.com/poojabhosale555/kitchen-sink.git

1. **Build the project:**

   ```sh
   mvn clean verify
   mvn clean install 

1. **Run the application:**
After setting up the project, you can run the application using the following command:

   ```sh
   mvn spring-boot:run

This will start the application on http://localhost:8080.

## Endpoints

### Get List of Members
- **URL:** `/members/list`
- **Method:** `GET`
- **Response:** `200 OK` with the list of members in JSON format.

### Get Member by ID
- **URL:** `/members/{memberId}`
- **Method:** `GET`
- **Path Variable:**
  - `memberId` (String): ID of the member to retrieve.
- **Response:** `200 OK` with the member details in JSON format or `404 Not Found` if the member does not exist.

### Create a New Member
- **URL:** `/members/create`
- **Method:** `POST`
- **Request Body:** JSON representation of the member.
- **Response:** `200 OK` with the created member details in JSON format or `400 Bad Request` if a member with the same email already exists.

#### Example Request Body:
```json
{
  "id": "123",
  "name": "John Doe",
  "email": "john.doe@example.com"
}

