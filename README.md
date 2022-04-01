# Running the application

## This flowable springboot application uses the following technologies:
- Java version 11
- Spring Boot Start Parent 2.6.3
- Flowable Spring Boot Start 6.7.2

## Current local development setup
### H2 Database Setup - temporary storage
- Ensure to have the H2 database dependency set for runtime and any other database dependencies commented out.

```		
    <dependency>
  		<groupId>com.h2database</groupId>
  		<artifactId>h2</artifactId>
  		<scope>runtime</scope>
  	</dependency>
```

- Ensure the following properties and values are in the `application.properties` file
    - Note: The value for the datasource.url is a temporary in memory storage that can be changed if desired.
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=<create a username>
spring.datasource.password=<create a password>
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```
- Once the application starts, the H2 database can be viewed at `http://localhost:8080/h2-console`
    - Login in with values provided in the `applications.properties` file.

### Postman Setup
- Create a local environment variable for the testing endpoint
    - On the left-hand side of Postman find `Environments`
    - Create a new variable with a name meaningful to your work
         - variable value: `endpoint`
         - Initial value: `http://localhost:8080/`
    - Save the variable
    - To use this value for reference in the collection requests, select it by name in the upper right-hand corner
        - Should be adjacent to the eye icon
- Import Postman collection from this repository 

## Steps for testing existing workflow
- May need to run `mvn install -DskipTests` initially
- Run the FloweablebootApplication
- Execute the following Postman collection requests:
   - Create User (adjust values in request body if desired) - this will be the assignee in future steps
     - The new user should appear in the Person table in the database
   - Start Workflow (body needs to include assignee from previous registration)
     - Pay attention to the console for manual input of assignee
   - All Tasks by Assignee - parameter value should match assignment task
   - Submit User Task - JSON body should be `id` and `name` from GET request for tasks
   - All Tasks by Assignee - task name should have changed to `analyst status`
   - Submit Status - `status` should be `COMPLETE`
   - All Tasks by Assignee - last task
   - Submit User Task - JSON body should be result of previous request
   - All Tasks by Assignee - should no longer include last task
   