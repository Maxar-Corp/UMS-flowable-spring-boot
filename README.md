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
- Once the application starts, the H2 database can be viewed at `http://localhost:8443/ums-api/h2-console`
    - Login in with values provided in the `applications.properties` file.

## Current worrkflow for testing workflow
- Run the FloweablebootApplication
- Add a user by hitting the /user endpoint
    - curl -H "Content-Type: application/json" -d '{"username" : "tester", "firstName":"test_first", "lastName":"test_last", "birthDate":"1999-07-23"}' http://localhost:8080/user
- Kick off a new workflow process and assign a user
    - curl -H "Content-Type: application/json" -d '{"assignee" : "PEDROSORTO"}' http://localhost:8080/process
    - IDE console/Terminal will ask for user task asignee, enter username and hit enter
- Query for all tasks assigned to asignee
    - curl http://localhost:8080/tasks\?assignee\=<username>
    - take note of the id value returned, this is the task ID at the "analyst status" step in the workflow
- At this step the "analyst" must update the status of the task
    - curl -H "Content-Type: application/json" -d '{"status":"COMPLETE", "id":"4a21a85b-a945-11ec-8c96-acde48001122"}' http://localhost:8080/status
- If the status of the task is set to complete, the workflow will continue to the "last" task in the workflow
- Retrieve the id for the last task
    - curl http://localhost:8080/tasks\?assignee\=PEDROSORTO
    - returns: [{"id":"1c256402-a946-11ec-8c96-acde48001122","name":"last"}]
- The last step is a "usertask" and can be completed by hitting the user task endpoint
    - curl -H "Content-Type: application/json" -d '{"taskName":"last", "taskId":"1c256402-a946-11ec-8c96-acde48001122"}' http://localhost:8080/usertask
- Upon sending the curl request the tasks endpoint should return an empty list of tasks
    - curl http://localhost:8080/tasks\?assignee\=PEDROSORTO
    - []
    
## Working with UMS API
- Currently there are CRUD endpoints for UMS Tasks at `~/ums-api/umsTasks`
- There is a small set of tasks generated and available on application startup.
- Postman Requests for UMS API provide examples that may be used for functional testing. 
**Note**: These endpoints do not currently include the integration with Spring Security and may allow permissions
 that may not be available in the future
 - For local testing, the Postman environment variable should be set to: `http://localhost:8443/ums-api/`
