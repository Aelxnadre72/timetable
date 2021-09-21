package core;

import java.util.List;

public class Timetable {
    
    private List<Event> eventList = new ArrayList<>();

    public Timetable(ArrayList<Event> eventer) {
        for (Event e : eventer) {
            this.eventList.add(e);
         }
    }

    // Return complete list with events
    public List<Event> getEventList() {
        return eventList;
    }

    // Make new event in timetable
    public void addEvent(Event event) { 
        for (e : eventList) {
            if (e.getDayOfWeek() == event.getDayOfWeek()) and (e.getTimeStart == event.getTimeStart()) {
                // Fault message
                // break
            }
        }
        this.eventList.add(event);
    }

    // Remove event from timetable
    public Event removeEvent(Event event) {
        this.eventList.remove(event);
    }

    
    


}
