package timetable.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Timetable {
    
    private List<Event> eventList = new ArrayList<>();
    private int week;
    private int year;

    public Timetable(int week, int year) {
        // set week (53 because some years have 53 weeks)
        if(week<1 || week>53){
            throw new IllegalArgumentException("The week number is invalid.");
        }
        if(year<2020 || year>2030){
            throw new IllegalArgumentException("The year is invalid.");
        }
        this.week = week;
        this.year = year;
    }

    
    /**
     * @return return a copy of EventList with all the events
     */
    public List<Event> getEventList() {
        return new ArrayList<Event>(eventList);
    }

    /**
     * add new event in timetable
     * @param event
     */
    public void addEvent(Event event) {
        if(isDuplicateEvent(event)){
            throw new IllegalArgumentException("The event given to addEvent is colliding (time and date) with an existing event.");
        }
        this.eventList.add(event);
    }

    /**
     * removes event with specific title, start time and date from eventList
     * @param event
     */ 
    public void removeEvent(Event event) {
        List<Event> ev = eventList.stream()
        .filter(e -> e.getDayOfWeek() == event.getDayOfWeek() && e.getTimeStart().equals(event.getTimeStart())).collect(Collectors.toList());
        eventList.remove(ev.get(0));
    }

    /**
     * checks if the event already exists in eventList
     * @return returns true if it's in the list and false if it's not
     */
    private boolean isDuplicateEvent(Event event){
        int eventStartTime = Integer.parseInt(event.getTimeStart().substring(0, 2));
        int eventEndTime = Integer.parseInt(event.getTimeEnd().substring(0, 2));
        if(eventEndTime == 0){
            eventEndTime = 24;
        }
        String eventDate = event.getDate();
        for(Event e:eventList){
            int eStartTime = Integer.parseInt(e.getTimeStart().substring(0, 2));
            int eEndTime = Integer.parseInt(e.getTimeEnd().substring(0, 2));
            if(eEndTime == 0){
                eEndTime = 24;
            }

            if(eventDate.equals(e.getDate())){
                if(eventEndTime > eStartTime && eventEndTime <= eEndTime){
                    return true;
                }
                else if(eventStartTime < eEndTime && eventStartTime >= eStartTime){
                    return true;
                }
                else if(eventStartTime < eStartTime && eventEndTime > eEndTime){
                    return true;
                }
            }
        }
        return false;
    }

    
    /**
     * get week of timetable
     * @return week 
     */
    public int getWeek(){
        return week;
    }

    /**
     * get year of timetable
     * @return year
     */
    public int getYear(){
        return year;
    }

}
