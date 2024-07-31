# kitchen-sink - Members Management Spring Boot Application

This is a Spring Boot application for managing members with RESTful endpoints to list members, get a member by ID, and create a new member.

# MongoDB Database
The application uses the MongoDB database. MongoDB is a schema-less database, which means the database can manage data without the need for a blueprint. Data in MongoDB is stored in documents with key-value pairs instead of rows and columns, which makes the data more flexible when compared to SQL databases. As a document database, MongoDB makes it easy for developers to store structured or unstructured data. It uses a JSON-like format to store documents.

### Install and Configure MongoDB with Application

1. **MongoDB Atlas Account:**
   - Sign up for a free account at [MongoDB Atlas](https://www.mongodb.com/cloud/atlas/register).

2. **Create a Cluster:**
   - Follow Atlas UI instructions to create a new cluster.

3. **Get Connection String:**
   - Whitelist your IP address and create a database user.
   - Copy the connection string provided (e.g., `mongodb+srv://<username>:<password>@cluster0.mongodb.net/myFirstDatabase?retryWrites=true&w=majority`).

### Configure Spring Boot Application

1. **Add Dependencies:**
   - In your `pom.xml`:
     ```xml
     <dependencies>
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-data-mongodb</artifactId>
         </dependency>
     </dependencies>
     ```

2. **Update `application.properties`:**
   - In `src/main/resources/application.properties`:
     ```properties
     spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
     ```

## Application Requirements

- Java 22 (Go to the [Java SE Development Kit 22 Downloads](https://www.oracle.com/java/technologies/javase/jdk22-archive-downloads.html) page.) 
- Spring Boot 3.3.2 - (Go to the [Spring Initializr](https://start.spring.io/) website.)
- Maven 4.0.0 (Go to the [Maven Downloads](https://maven.apache.org/download.cgi) page.)

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
- **URL:** `http://localhost:8080/api/v1/members/list`
- **Method:** `GET`
- **Response:** `200 OK` with the list of members in JSON format.

### Get Member by ID
- **URL:** `http://localhost:8080/api/v1/members/{memberId}`
- **Method:** `GET`
- **Path Variable:**
  - `memberId` (String): ID of the member to retrieve.
- **Response:** `200 OK` with the member details in JSON format or `404 Not Found` if the member does not exist.

### Create a New Member
- **URL:** `http://localhost:8080/api/v1/members/create`
- **Method:** `POST`
- **Request Body:** JSON representation of the member.
- **Response:** `200 OK` with the created member details in JSON format or `400 Bad Request` if a member with the same email already exists.

#### Example Request Body:
      
      {
       "migratedId":"6",
       "name":"Cristiano Ronaldo",
       "email":"Cristiano.Ronaldo@mongodb.com",
       "phoneNumber":"22222222222"
      }
