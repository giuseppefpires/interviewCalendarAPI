XGEEKS Code Challenge

Author: Giuseppe Fregapane Pires - giuseppefpires@gmail.com

### Description
This is an Interview Calendar application where the user can see the matching date(s) between a candidate and one or more interviewers. 

### Building and Running
In order to build and run the solution you will need JDK 8+ and Maven installed. Then, follow the steps: 
 - Clone the repository
 - Through a terminal go to the main directory of the source and run the commands:
 - `mvn clean package `
 - `java -jar target/XgeeksProject-0.0.1-SNAPSHOT.jar`
 - In the root  of the sistem i put the file ApiXGEEKS.postman_collection.json that can be import into postman with some examples of requests.Feel free to use if you want.

### Design Decisions
- For this Rest Application, i used spring boot to facilitate the creation of the api
- I use the H2 as a database to simplify the persistence layer.
- No autentication and authorization was implemented.
- I have created some extra endpoint(not required in the question description) but didnt provide test for them. I have only tested the main endpoints.
- The way that i felt best suit this solution was to organized the code like the  MVC model.

### The Algorithm 
For every date i created an array where i will increase for ever inteval.
Then after given a loop for eache user i will return the hours that match the sum of all users.

I also based in on SOLID principles, decoupling classes, referencing interfaces/abstract classes instead of child classes.

### Packages

##### Model
 This package contains the application models.Users, Availability, Role, RequestInterview and ErroResponse are classes.
 
##### Exception
 This package contains the Exception classes of the api.Every exception that is throw is inside this package.
 
##### Repository
 This package contains the repository. The repository classes uses the H2 database.
 
 ##### Service
 This package contains the Interface and implementation of the service.
 The class is responsible to provide the methods for the controller.
 
##### Controller
 This package contains the controller and the exception handler.
 The controller provide the endpoins of the Api and the exception handler will handle all the exception of the sistem.
 
##### Util
 This package contains the UserHelper class.
 This class help to validate the users.
 
 


XGeeksApplication
This is the main class (main method).


