
# College Management System

Here I have designed and developed College management system.
I have followed MVC design method to create this project.

    - Technologies & Tools
        - Spring boot
        - Java
        - Database - MongoDB
        - Postman to test apis
        - Spring Security
        - Swagger for documentation
        - Inteij idea

## Database Connection

spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=college
server.port=8080



  
## Features

- Admin
    
    Admin Manages whole system,
    - can create users(Students, Teachers) 
    - can create subject
    - assign subject to the users(Students, Teachers) 

- Teachers

    - can view their profile data
    - can check assigned subjects
    - can create Students only
    - can give marks to the students

- Students

    - can view their profile data
    - can check assigned subjects
    - can check marks given by Teachers

- Role based authentication system

    - Implemented JWT authentication
    - User can login using username(email) and password
    - Roles
        - Admin
        - Student
        - Teacher

    
  
## FAQ
#### Swagger

    http://localhost:8080/swagger-ui/#/

#### Credentials for ADMIN

    {
    "email" : "admin",
    "password" : "admin"
    }

#### Credentials for Student
    
    {
    "email" : "studentone@gmail.com",
    "password" : "test"
    }

#### Credentials for Teacher
    
    {
    "email" : "teacher3@gmail.com",
    "password" : "test"
    }

#### What is Authentication Token
    
    - Only example this token will not work.

    {
    "authenticationToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZWFjaGVyM0BnbWFpbC5jb20ifQ.dRRZknFpQ6IwT_F6cTok44AQHs87BwZzXf1qFgNZwSA",
    "username": "teacher3@gmail.com"
    }

    - Once you login you will receive authenticationToken
    after that you have to set it as AUTHENCATION HEADER


  
## Documentation

[Documentation using postman](https://documenter.getpostman.com/view/17189133/UUxxh8gQ)

## 🚀 About Me
I'm a fresher java back end developer , completed bachelor's degree in computer engineering in 2020.

Finding intership opptunities.

  
