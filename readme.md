[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2139/gr2139)

# timetable 
Timetable [timetable](timetable) is the main folder for our group project in the subject IT1901. 
The goal for our app is for the user to add events, with title, a date, time of day and a description. The event will then appear in a timetable. The user can also choose between several weeks, not just plan for one week. 
In timetable there is a folder for the logic of the app ([core](timetable/core)), and a folder for the user interface ([fxui](timetable/fxui)). 

# Core 
The core logic is implemented in [Timetable.java](timetable/core/src/main/java/timetable/core/Timetable.java). It has methods for adding and removing events from the lists of events.
[Event.java](timetable/core/src/main/java/timetable/core/Event.java) is the event class, and contains gets and sets for an event object. And has methods for making event-objects with title, description, time and day. 

# json
[EventIO.java](timetable/core/src/main/java/timetable/core/EventIO.java) saves the event information the user added, which can be read and become an event-object. The even-object can then appear in the timetable gui.  


# fxui 

## Controller
[AppController.java](timetable/fxui/src/main/java/timetable/ui/AppController.java) is the controller, which combines the core logic with the ui.

## fxml
In [App.fxml](timetable/fxui/src/main/resources/timetable/ui/App.fxml) the GUI for the timetable is implemented. The timetable is made with a pane and ListView for each coloumn. The coloumns represents the hours of the day and days of the week. There are Textfield-boxes for the user to fill in a title and description. And Choiceboxes to pick a day and time. Each of these items have an id, which is used in the controller.

# Tests
[EventTest.java](timetable/core/src/test/java/timetable/core/EventTest.java) have Junit tests that tests the event class (the constructor and the setters) and it also has exception tests.

## Spotbugs
## Checkstyle 

# Trying it out
To try out the projects, cd into the corrosponding folder.
* compile with mvn compile
* test with mvn test
* run mvn javfx:run (inside fxui)
