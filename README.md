# BookManagement
Book Management Personal project using Spring Boot

Implement library management system for a commercial library. This software can be used by three types of users.
1. Owner
2. Librarian
3. User

Owner:
Owner owns library. He is interested into tracking assets and revenue.

Librarian:
Owner appoints a librarian for library management. He handles all tasks including data entry, book issue etc.

User:
Members (reader) can find books and check availability.

Tasks:
Owner can appoint librarian. Librarian can assign books to users and book is expected to return in 7 days. If delayed, fine of Rs. 5/- is
applicable and should be taken by Librarian while returning book.

Technologies:
Programming language: Java (version greater than 1.8 )
Database: Postgres 15.3(I'm using 15.3 version)

# Postgres:
Create user:  CREATE ROLE library_usr LOGIN PASSWORD 'library_usr';
Create Database: CREATE DATABASE library;
Grant access : GRANT ALL ON SCHEMA public to library_usr;

# How to run project from command line?
Navigate to the root of the project via command line and execute the below command
mvn spring-boot:run

# Functionalities
Common functionality
1. Sign in
2. Sign up
3. Edit profile
4. Change password
5. Find book

User Functionality
1. Check fine
2. Return book

Librarian Functionality
1. Accept Fine
2. Assign book

Owner Functionality
1. Appoint Librarian
2. Get reports (Membership, Fine)


Token for sonaqube
sqp_1ec39b960a3c909125c36395e143f642dfd86306

mvn clean verify sonar:sonar -Dsonar.projectKey=BookManagement -Dsonar.projectName='BookManagement' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_1ec39b960a3c909125c36395e143f642dfd86306

Link for Swagger
http://localhost:8080/swagger-ui/index.html#/


