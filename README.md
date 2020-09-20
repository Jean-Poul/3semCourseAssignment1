# [Course Assignment 1](https://docs.google.com/document/d/1XT94iw0TpKjK2c1hXhCj8wPPOwogTVME0hsONbEf4F4/edit)  
 [![Build Status](https://travis-ci.org/Jean-Poul/3semCourseAssignment1.svg?branch=master)](https://travis-ci.org/Jean-Poul/3semCourseAssignment1)
  
##### Project was deployed on this droplet: [micklarsen.com/CA1](https://micklarsen.com/CA1/)
  
## Members of Class E, Group 8:
  ##### Jean-Poul Wilhelm Luplau Leth-Møller [Github](https://github.com/Jean-Poul)
  ##### Morten Rahbek Rasmussen [GitHub](https://github.com/Amazingh0rse)
  ##### Mick Kristian Strellner Larsen [GitHub](https://github.com/MivleDK)
  ##### Per Kringelbach [Github](https://github.com/cph-pk)
  ##### Alexander Pihl [Github](https://github.com/AlexanderPihl)
   
 <br>
  
## Daily Log:
### **Monday**
- Introduction from teacher to Course Assigment 1.
- Initial setup:
  - Github repository
  - Travis integration
  - Trying to setup access to droplet for everyone.  
    Not solved that day due to public keys denied for everyone except droplet owner.
      
### **Tuesday**
- Making sure everybody has a working start project and are able to push to github and deploy through travis. Which was more difficult then it sounds because of issues with travis not deplying and the project not building for everyone in the group. Eventually it worked out.
- Project classes structure setup.
  - members entity
  - members DTO
  - joke entity
  - jokes DTO
  - cars entity
  - cars DTO
- Front-end setup with HTML structure, bootstrap & custom stylesheet
- README file setup.
  
### **Wednesday**
- Member assignment:
  - Members part of the assignment was worked on by implementing:
      - getCount()
      - getAllMembers()
      - getStudentId(String studentId)
  - Main method was created in order to put data in the database.
  - Implementing proper rest endpoints.
    - @Path("count")
    - @Path("all")
    - @Path("/{studentId}")
  - Suitable tests for both facade and rest endpoint to make sure everything was working before being pushed to master branch.
  - MemberResource.java
      - testMemberCount()
      - testGetAllMembers()
      - testGetMemberById()
      - testMemberHasFavoriteShow()
      - getMemberByName()
  - MemberResourceTest.java
    - FacadeTest including main method for putting data in test database.
        - testGetAll()
        - testFindByStudentId()
        - testFindByTitleNotFound()
  - JavaScript for Members part of the assignment also done. 
  <br>
-  Car assignment:
    - Car part of the assignment was also worked on by implementing:
      - getCount()
      - getAllCars()
      - getCarsByMaker(String maker)
      - getCarsByYear(int year)
    - Main method was also createt for putting data in the database.
    - Implementing proper rest endpoints.
      - @Path("count")
      - @Path("all")
      - @Path("/{maker}")
      - @Path("year/{year}")
     - Suitable tests for both facade and rest endpoints to make sure everything was working before being pushed to master branch.
       - CarFacadeTest.java
         - testCarsCount()
         - testGetAllMembers()
         - testGetCarsByMaker()
         - testGetCarsByYear()
         - testCarModelByName()
       - CarResourceTest.java
         - testCount()
         - testGetAll()
         - testFindByMaker()
         - testFindByModelNotFound()
       - FacadeTest including main method for putting data in test databases.
- All frontend work was completed, ready for "Connecting" the with backend.

### **Thursday**
-  Jokes assignment:
    - Jokes part of the assignment was also worked on by implementing:
      - getCount()
      - getAllJokes()
      - getJokeById(long id)
      - getRandomJokes()
    - Main method was created for putting data in the database.
    - implementing proper rest endpoints.
      - @Path("count")
      - @Path("all")
      - @Path("/{id}")
      - @Path("random")
     - Suitable tests for both facade and rest endpoint to make sure everything was working before being pushed to master branch.
       - JokeFacadeTest.java
         - testJokeCount()
         - testGetAllJokes()
         - testGetJokeById()
         - testRandomJoke()
       - JokeResourceTest.java
         - testCount()
         - testGetAll()
         - testFindById()
         - testFindByIdNotFound()
         - testGetRandomJoke()
       - FacadeTest also including main method for putting data in test databases.
     - JavaScript for jokes part of the assignment also done. 
- Frontend connection with backend is being worked on. 

### **Friday**
- Member assignment.
  - Member part of the assignment is now fully implemented with JavaScript for the frontend. 
- Car assignment.
  - Car part of the assignment is now fully implemented with JavaScript for the frontend.
- Joke assignment.
  - Joke part of the assignment is now fully implemented with JavaScript for the frontend.
  - Joke has two parts both implemented with JavaScript for the frontend.
    - jokes.html - which fetches from https://api.chucknorris.io and is "External" fetching. 
    - jokes2.html - which fetches from our own database with which is filled with jokes and is our "Internal" fetching.

### **Saturday**
- Walkthrough of full assignment. Does everything still works as intented. 
- Talking about if there's something we missed.
- Final code touch up.
- Planning for review on Monday. Who says what.