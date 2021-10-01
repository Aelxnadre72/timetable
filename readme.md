[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2139/gr2139)

# modules-template 
Modules-template [modules-template](modules-template) is the main folder for our group project in the subject IT1901. 
The goal for our app is for the user to add events, with title, day of week, time of day and a description. The event will then appear in a timetable. 
In modules-template there is a folder for the logic of the app ([core](modules-template/core)), and a folder for the user interface ([ui](modules-template/ui)). 

# Core 
The core logic is implemented in [Timetable.java](modules-template/core/src/main/java/core/Timetable.java). It has methods for adding and removing events from the lists of events.
[Event.java](modules-template/core/src/main/java/core/Event.java) is the event class, and contains gets and sets for an event object. And has methods for making event-objects with title, description, time and day. 

# json
[EventIO.java](Timetable/core/src/main/java/timetable/json/EventIO.java) saves the event information the user added, which can be read and become an event-object. The even-object can then appear in the timetable gui.  


# ui
## fxml
In [App.fxml](modules-template/ui/src/main/resources/ui/App.fxml) the GUI for the timetable is implemented. The timetable is made with a pane and ListView for each coloumn. The coloumns represents the hours of the day and days of the week. There are Textfield-boxes for the user to fill in a title and description. And Choiceboxes to pick a day and time. Each of these items have an id, which is used in the controller. 

## Controller
[AppController.java](modules-template/ui/src/main/java/ui/AppController.java) is the controller, which combines the core logic with the ui. 

# Tests
[EventTest.java](modules-template/core/src/test/java/core/EventTest.java) tests the event class (the constructor and the setters) and it also has exception tests. 

# Trying it out
To try out the projects, cd into the corrosponding folder.
* compile with mvn compile
* test with mvn test 
* run mvn javfx:run 
