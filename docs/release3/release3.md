# Release 3
For release 3 we wanted to cotinue to work with the milestone from release 2, because the milestone was too big and overwhelming to work with for just one release. We continued to work on the categorized events, so that the user can add events for different categories. In additon to continue the work from release 2, we implemented a REST api and service, relative to the requirements for this release. 
We also needed to continue to implement (from release2) and extend the funcionality of the app with a feature that shows the event information. So the user can click on an event on the timetable and the detailed information of this event will show up under the timetable. We also wanted to add a feature for deleting events in the timetable. The app should also display both weeks and years, with each year having the correct number of weeks to choose from at the bottom, 52 or 53. 

## core
Added categories to Event.java. Rewrote the method getEventList in Timetable.java to preserve the encapsulation.

The core now has a user.java that has a timetable which contains timetables for each week that has events. The timetables has a eventlist with events in that week.

## ui
Replaced the onclick on listvew-days with a listener that listens to the listview-days selectionmodel.
Shows the right event information when selecting an event in a listview-day according to userstory 3.2. Also shows the delete event button to delete the event. The duration of an event can be longer than 1 hour, and it will show up on the correct cells in the listview. When deleting an event, it also removes the event from every cell that contained it according to userstory 3.3.
Wrote code to calculate and process events that are in week 53 to show them in the correct weak.
Initialized the days list containing the listview-days in the initialize method in AppController.java. Rewrote code to use the days list instead as well as optimizing some parts to reduce the amount of code.
Did not choose to move some of the functionality to another window/view as suggested by the studass because every functionality is tightly bound together. When adding a new event, and you're currently viewing the year and week of the event you're going to add, then it is crucial to see other events in the same week to not overlap some already added events. It is therefore not practical to move the add functionality to a new view/page/window. The show event information when cliccking on an event has to be on the same windows as the listviews/timetable, so there is no point in moving that as well. The delete button is part of the show event information, so there is no functionality in the ui that should be moves to a new window to improve the application.

The ui now has the functionality to add an event and view the event in the correct week and year, even for week 53, as well as the right cells that corresponds to the events duration according to userstory 3.1. Click on an event and get the events information show up on the right side of the add event fields according to userstory 3.2. A delete button that shows up underneath the event information when an event is selected/clicked on in the listview according to userstory 3.3. Navigate to the right year and week with the year choicebox and the scrollbar with the right number of weeks depending on the chosen year, as well as two buttons for next or previous week. The current chosen week number is displayed at the top, beneath the timetable header.


## rest

## Weaknesses
Two members of our group got sick and one member was abroad for some weeks. This made it more challenging to do pair programming and keeping our work routines in the affected periods.

## Code quality
We rewrote methods to reduce the amount of code and replaced methods with more efficient ones. We always made sure there were no checkbug og checkstyle violations. We also had to change our default number of spaces when indenting, as our default number of spaces in vscode was set to 4 when installing vscode, wchich triggered a lot of checkstyle warnings where it expected half as many spaces. We also used mvn jacoco:report to view the test coverage of our tests. This helped us to cover most or all of the methods in the classes that we intended to test.

## Workflow and Workhabits
We have mostly been working in pairs. Each pair has either been working on two different parts of one single task or taking turns coding one single task while the other person was assisting. We also had multiple meetings each week to keep eachoter updated, either at school or on zoom. One group member was abroad the whole month of november, so we had to include that person on zoom while the rest of the group were together. We also had a facebook chat group to communicate our progress or problems.
We made sure to distribute or work hours evenly, and each one of us worked at least 10 hours with the project weekly, either in pairs or alone. Two of the group members lives together, so they had the opportunity to practice pair programming often whenever they were working on the same task. 

We improved our git knowledge and used issues and branches appropriately for release3, contrary to the previous releases (especially release1 when we did not know that branches was a thing). We had meetings to determine our different tasks, and helped each other with dividing our tasks into smaller ones that we created issues and branches for. Whenever one of us was about to merge a branch into master, we would first send a message in our facebook group chat to inform the others of the merge to master, as well as assigning one of the other group members to review the merge request. 
We wrote detailed issues so the other group members would understand its content, as well as creating branches with appropriate names for each issue. When we finished a part of an issue, we would commit and push the progress to the branch related to the issue. We wrote descriptive commit messages and referenced the appropraite issue(s).