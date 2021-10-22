package timetable.core;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    
    private List<Event> eventList = new ArrayList<>();
    private int week;
    private int year;
    // private EventIO eventIO;

    public Timetable(int week, int year) {
        // initialize an EventIO object
        // eventIO = new EventIO(this);
        // reads and creates events from json and 
        // eventIO.read();
        // set week (53 because some years have 53 weeks)
        if(week<1 && week>53){
            throw new IllegalArgumentException("The week number is invalid.");
        }
        if(year<2020 && year>2030){
            throw new IllegalArgumentException("The year is invalid.");
        }
        this.week = week;
        this.year = year;
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

    // remove event from timetable
    public void removeEvent(Event event) {
        this.eventList.remove(event);
    }

    // check if the event already exists in eventList
    private boolean isDuplicateEvent(Event event){
        int eventStartTime = Integer.parseInt(event.getTimeStart().substring(0, 2));
        int eventEndTime = Integer.parseInt(event.getTimeEnd().substring(0, 2));
        if(eventEndTime == 0){
            eventEndTime = 24;
        }
        String eventDate = event.getDate();
        String eventTitle = event.getTitle();
        for(Event e:eventList){
            int eStartTime = Integer.parseInt(e.getTimeStart().substring(0, 2));
            int eEndTime = Integer.parseInt(e.getTimeEnd().substring(0, 2));
            if(eEndTime == 0){
                eEndTime = 24;
            }

            if(eventDate.equals(e.getDate())){
                return true;
            }
            else if(eventTitle.equals(e.getTitle())){
                return true;
            }
            else if(eventEndTime > eStartTime && eventEndTime <= eEndTime){
                return true;
            }
            else if(eventStartTime < eEndTime && eventStartTime >= eStartTime){
                return true;
            }
            else if(eventStartTime < eStartTime && eventEndTime > eEndTime){
                return true;
            }
        }
        return false;
    }

    // get week of timetable
    public int getWeek(){
        return week;
    }

    // get year of timetable
    public int getYear(){
        return year;
    }

    public static void main(String[] args) {
        
    }
}
