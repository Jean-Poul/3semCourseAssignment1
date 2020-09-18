# [Course Assignment 1](https://docs.google.com/document/d/1XT94iw0TpKjK2c1hXhCj8wPPOwogTVME0hsONbEf4F4/edit) [![Build Status](https://travis-ci.org/Jean-Poul/3semCourseAssignment1.svg?branch=master)](https://travis-ci.org/Jean-Poul/3semCourseAssignment1)

## Project deployed on droplet below:
[micklarsen.com/CA1](https://micklarsen.com/CA1/)

## Members of Class E, Group 8:
  #### Jean-Poul Wilhelm Luplau Leth-Møller [Github](https://github.com/AlexanderPihl)
  #### Morten Rahbek Rasmussen [GitHub](https://github.com/Amazingh0rse)
  #### Mick Kristian Strellner Larsen [GitHub](https://github.com/MivleDK)
  #### Per Kringelbach [Github](https://github.com/cph-pk)
  #### Alexander Pihl [Github](https://github.com/AlexanderPihl)
#

## Daily Log:
### **Monday**
- Introduction from teacher to Course Assigment 1.
- Initial setup:
  - Github repository
  - Travis integration
  - Trying to setup access to droplet for everyone. Not solved that day due to public keys denied for everyone except droplet owner.
### **Tuesday**
- Making sure everybody has a working start project and are able to push to github and deploy through travis. Which was more difficult then it sounds because of issues with travis not deplying and the project not building for everyone in the group. Eventually it worked out.
- Project classes structure setup.
  - members entity
  - members DTO
  - joke entity
  - jokes DTO
  - cars entity
  - cars DTO
- index.html design setup.
- README file setup.
### **Wensday**
- Member assignment:
  - Members part of the assignment was worked on by implementing:
      - getCount()
      - getAllMembers()
      - getStudentId(String studentId)
  - Main method was also createt for putting data in the database.
  - implementing proper rest endpoints.
    - @Path("count")
    - @Path("all")
    - @Path("/{studentId}")
  - Suitable test for both facade and rest endpoint to make sure everything was working before being pushed to master branch.
    - MemberResource.java
      - testMemberCount()
      - testGetAllMembers()
      - testGetMemberById()
      - testMemberHasFavoriteShow()
      - getMemberByName()
    - MemberResourceTest.java
        - testGetAll()
        - testFindByStudentId()
        - testFindByTitleNotFound()
    - FacadeTest also including main method for putting data in test databases.
  - JavaScript for Members part of the assignment also done. 
-  Car assignment:
    - Car part of the assignment was also worked on by implementing:
      - getCount()
      - getAllCars()
      - getCarsByMaker(String maker)
      - getCarsByYear(int year)
    - Main method was also createt for putting data in the database.
    - implementing proper rest endpoints.
      - @Path("count")
      - @Path("all")
      - @Path("/{maker}")
      - @Path("year/{year}")
     - Suitable test for both facade and rest endpoint to make sure everything was working before being pushed to master branch.
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
       - FacadeTest also including main method for putting data in test databases.
- All frontend setup also done and is now ready to be connected with backend.

### **Thursday**
-  Jokes assignment:
    - Jokes part of the assignment was also worked on by implementing:
      - getCount()
      - getAllJokes()
      - getJokeById(long id)
      - getRandomJokes()
    - Main method was also createt for putting data in the database.
    - implementing proper rest endpoints.
      - @Path("count")
      - @Path("all")
      - @Path("/{id}")
      - @Path("random")
     - Suitable test for both facade and rest endpoint to make sure everything was working before being pushed to master branch.
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
- Frontend is in working process of being connected to backend part of the Course Assignment 1. 
### **Friday**
- Member assignment.
  - Member part of the assignment is now fully implementet with JavaScript for the frontend. 
- Car assignment.
  - Car part of the assignment is now fully implementet with JavaScript for the frontend.
- Joke assignment.
  - Joke part of the assignment is now fully implementet with JavaScript for the frontend.
  - Joke has two parts both implementet with JavaScript for the frontend.
    - jokes.html - which fetches from https://api.chucknorris.io and is therefor our external fetching. 
    - jokes2.html - which fetches from our own database with which is filled with jokes and is therefor our internal fetching.

