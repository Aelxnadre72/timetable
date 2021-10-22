[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2139/gr2139)

# timetable 
Timetable [timetable](timetable) is the main folder for our group project in the subject IT1901. 
The goal for our app is for the user to add events, with title, a date, time of day and a description. The event will then appear in a timetable. The user can also choose between several weeks, not just one week. 
In timetable there is a folder for the logic of the app ([core](timetable/core)), and a folder for the user interface ([fxui](timetable/fxui)). 

# Core 
The core logic is implemented in [Timetable.java](timetable/core/src/main/java/timetable/core/Timetable.java). It has methods for adding and removing events from the lists of events.
[Event.java](timetable/core/src/main/java/timetable/core/Event.java) is the event class, and contains gets and sets for an event object. And has methods for making event-objects with title, description, time and day.

[User.java](timetable/core/src/main/java/timetable/core/User.java) .........

## Serializers and deserializers
[EventSerializer.java](timetable/core/src/main/java/timetable/core/EventDeserializer.java).........

[EventDeserializer.java](timetable/core/src/main/java/timetable/core/EventDeserializer.java) .......

[TimetableSerializer.java](timetable/core/src/main/java/timetable/core/EventDeserializer.java) .....

[TimetableDeserializer.java](timetable/core/src/main/java/timetable/core/TimetableDeserializer.java) .....

[UserSerializer.java](timetable/core/src/main/java/timetable/core/UserSerializer.java) ..........

[UserDesializer.java](timetable/core/src/main/java/timetable/core/UserDeserializer.java) .........



# json
[EventIO.java](timetable/core/src/main/java/timetable/core/EventIO.java) saves the event information the user added, which can be read and become an event-object. The even-object can then appear in the timetable gui.  


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
