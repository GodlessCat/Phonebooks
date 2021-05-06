# Phonebooks

## Launch  
To launch run PhonebooksApplication in IDE.  
Or run the following command in a terminal window (in the complete) directory:  

    ./mvnw spring-boot:run

## List of API commands:
* Create Users - POST: http://localhost:8080/users
* Get All Users - GET: http://localhost:8080/users or http://localhost:8080/users?name={string}
* Get User By Id - GET: http://localhost:8080/users/{user_id}
* Update User By Id - PUT: http://localhost:8080/users/{user_id}
* Delete User By Id - DELETE: http://localhost:8080/users/{user_id}
* Create Entry - POST: http://localhost:8080/users/{user_id}/entries
* Get All Entries - GET: http://localhost:8080/entries or http://localhost:8080/entries?number={number}
* Get Entry By Id - GET: http://localhost:8080/entries/{entry_id}
* Get All Entries By User Id - GET: http://localhost:8080/users/{user_id}/entries
* Update Entry By Id - PUT: http://localhost:8080/entries/{entry_id}
* Delete Entry By Id - DELETE: http://localhost:8080/entries/{entry_id}
* Shutdown Server - POST: http://localhost:8080/actuator/shutdown

## DataBase
The application has a database stored in the test.mv.db file
