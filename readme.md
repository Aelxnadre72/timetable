[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2139/gr2139)

# timetable 
Timetable [timetable](timetable) is the main folder for our group project in the subject IT1901. 
The goal for our app is for the user to add events, with title, a date, time of day and a description. The event will then appear in a timetable. The user can also choose between several weeks, not just one week. 
In timetable there is a folder for the logic of the app ([core](timetable/core)), and a folder for the user interface ([fxui](timetable/fxui)). 

# Core 
The core logic is implemented in [Timetable.java](timetable/core/src/main/java/timetable/core/Timetable.java). It has methods for adding and removing events from the lists of events.
[Event.java](timetable/core/src/main/java/timetable/core/Event.java) is the event class, and contains gets and sets for an event object. And has methods for making event-objects with title, description, time and day.

[User.java](timetable/core/src/main/java/timetable/core/User.java) .........

## Json, serializers and deserializers
Writing and reading from Json files is implemented in [Json.java](timetable/core/src/main/java/timetable/core/Json.java), with the methods read and write. With the read method, the user reads all existing events and timetables with a hierarchy of deserializers; user [UserDeserializer.java](timetable/core/src/main/java/timetable/core/UserDeserializer.java), timetable [TimetableDeserializer.java](timetable/core/src/main/java/timetable/core/TimetableDeserializer.java), event [EventDeserializer.java](timetable/core/src/main/java/timetable/core/EventDeserializer.java). The deserializers gets initialized in [TimetableModuleRead.java](timetable/core/src/main/java/timetable/core/TimetableModuleRead.java). 
The write-method writes all timetables and events for the specific user using a hierarchy of serializers: user [UserSerializer.java](timetable/core/src/main/java/timetable/core/UserSerializer.java), timetable [TimetableSerializer.java](timetable/core/src/main/java/timetable/core/TimetableSerializer.java), event [EventSerializer.java](timetable/core/src/main/java/timetable/core/EventSerializer.java) The serializers gets initialized in [TimetableModuleWrite.java](timetable/core/src/main/java/timetable/core/TimetableModuleWrite.java).
These methods for reading and writing gets called in the controller, and can be visible in the timetable gui.




# fxui 

## Controller 
[AppController.java](timetable/fxui/src/main/java/timetable/ui/AppController.java) is the controller, which combines the core logic with the ui.

## fxml
In [App.fxml](timetable/fxui/src/main/resources/timetable/ui/App.fxml) the GUI for the timetable is implemented. The timetable is made with a pane and ListView for each coloumn. The coloumns represents the hours of the day and days of the week. It also have buttons for each week of the year and it's contained in a scrollpane to save space.  
There are Textfield-boxes for the user to fill in a title and description. There is a datepicker for choosing a date, and a choicebox to pick start and end time. At the bottom there is a button "Add event", which adds the event to the timetable. Each of these items have an id, which is used in the controller.
At the right hand side, an event will show up when it's clicked on on the the timetable. It shows the title, date, time and description. The defaulot text is "None". 

# Tests
[EventTest.java](timetable/core/src/test/java/timetable/core/EventTest.java) have Junit tests that tests the event class (the constructor and the setters) and it also has exception tests.

We also implemnted other tools to check code quality:
* Spotbugs
* Checkstyle
* Jacoco 

We used these tools to check for bugs, errors and test coverage to optimalize the code quality. 

# Trying it out
To try out the projects, cd into the corrosponding folder.
* compile with mvn compile
* test with mvn test
* run mvn javfx:run (inside fxui)
* check code quality with mvn verify
