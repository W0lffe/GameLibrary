# Game Library Management System

### What is it?

In its simplicity, this application is a game management system. Users are able to add games to the system and to their own collection along with user-related information. 
Like how many hours is spent on the game, and did the like it. System tracks data of the game, such as developer, release year and genre.
Currently this system can only be ran locally.

### Why did I create it?

I created this system for a course project in school. Course was about database programming, so in basics I was learning to use JPA Hibernate in this project.
Additionally I wanted to give a new try to JavaFX and FXML, which I used to create the user interface.

### Plans?

Currently, no more plans for this application. Of course I might do something here and there, but mostly it was just for the course project.

#### How to run it?

Easiest way is with Eclipse IDE. But you also need MySQL server.
Source files contains gamelib-sql file, that is possible to import into MySQL.
In persistence.xml you just have to change details to match your own server.

In Eclipse:

1. Click RUN on top options bar
2. Click Run configurations... and new window should pop up
3. In the Run Configurations window, on left side is another options bar with file icons, 
	press "new launch configuration"
4. In configuration, click Workspace and select Gamelib -> ok
5. copy paste to goals: javafx:run
6. Select new configuration and click Run