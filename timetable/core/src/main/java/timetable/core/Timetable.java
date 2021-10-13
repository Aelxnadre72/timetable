package timetable.core;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    
    private List<Event> eventList = new ArrayList<>();
    private int week;
    private EventIO eventIO;

    public Timetable(int week) {
        // initialize an EventIO object
        eventIO = new EventIO(this);
        // reads and creates events from json and 
        eventIO.read();
        // set week
        if(week<1 && week>52){
            throw new IllegalArgumentException("The week number is invalid.");
        }
        this.week = week;
    }

    // return complete list with events
    public List<Event> getEventList() {
        return eventList;
    }

    // return last event in EventList
    // used in eventIO.java after adding a new event from the ui
    public Event getLastEvent(){
        if(eventList.isEmpty()){
            throw new IllegalStateException("The eventList is empty.");
        }
        return eventList.get(eventList.size()-1);
    }

    // add new event in timetable
    public void addEvent(Event event) {
        if(isDuplicateEvent(event)){
            throw new IllegalArgumentException("The event given to addEvent already exist.");
        }
        this.eventList.add(event);
    }

    // writes the last event to the json file
    public void writeEvent(){
        eventIO.write();
    }

    // remove event from timetable
    public void removeEvent(Event event) {
        this.eventList.remove(event);
    }

    // check if the event already exists in eventList
    private boolean isDuplicateEvent(Event event){
        return eventList.stream().anyMatch(e -> e.getDate().equals(event.getDate()) && e.getTimeStart().equals(event.getTimeStart()));
    }

    public int getWeek(){
        return week;
    }

    public static void main(String[] args) {
        
    }
}
