package core;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    
    
    private List<Event> eventList = new ArrayList<>();

    public Timetable(List<Event> events) {
        for (Event e : events) {
            this.eventList.add(e);
         }
    }

    // return complete list with events
    public List<Event> getEventList() {
        return eventList;
    }

    // add new event in timetable
    public void addEvent(Event event) {
        if(isDuplicateEvent(event)){
            throw new IllegalArgumentException("The event given to addEvent already exist.");
        }
        this.eventList.add(event);
    }

    // remove event from timetable
    public void removeEvent(Event event) {
        this.eventList.remove(event);
    }

    // check if the event already exists in eventList
    private boolean isDuplicateEvent(Event event){
        return eventList.stream().anyMatch(e -> e.getDayOfWeek() == event.getDayOfWeek() && e.getTimeStart().equals(event.getTimeStart()));
    }
}
