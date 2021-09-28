package timetable.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private String description;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private String day;
    
    public Event(String title, String description, String timeStart, String timeEnd, String day){
        if(!isCorrectTimeFormat(timeStart) || !isCorrectTimeFormat(timeEnd)){
            throw new IllegalArgumentException("Time is in incorrect format.");
        }
        this.title = title;
        this.description = description;
        this.timeStart = timeParser(timeStart);
        this.timeEnd = timeParser(timeEnd);
        this.day = day;
    }

    // get title
    public String getTitle(){
        return title;
    }

    // set title
    public void setTitle(String title){
        this.title = title;
    }

    // get description
    public String getDescription(){
        return description;
    }

    // set description
    public void setDescription(String description){
        this.description = description;
    }

    // get start time of event
    public String getTimeStart(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return timeStart.format(formatter);
    }

    // set start time of event
    public void setTimeStart(String startString){
        if(!isCorrectTimeFormat(startString)){
            throw new IllegalArgumentException("setTimeStart has been given an argument of incorrect format.");
        }
        timeStart = timeParser(startString);
    }

    // get ending time of event
    public String getTimeEnd(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return timeEnd.format(formatter);
    }

    // set ending time of event
    public void setTimeEnd(String endString){
        if(!isCorrectTimeFormat(endString)){
            throw new IllegalArgumentException("setTimeEnd has been given an argument of incorrect format.");
        }
        timeEnd = timeParser(endString);
    }

    // set date of event
    public void setDay(String day){
        this.day = day;
    }

    // get date of event
    public String getDay(){
        return day;
    }

    // converts from string to LocalTime format
    private static LocalTime timeParser(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(s, formatter);
    }
    
    // check that the time is in the correct format
    private boolean isCorrectTimeFormat(String s){
        // checks if String s is of length 5, contains a colon at index 2 and every other character is a digit
        return s.length() == 5 && s.substring(2, 3).equals(":") && s.replace(":", "").matches("[0-9]+");
    }

    public static void main(String[] args) {
        Event test = new Event("title", "desc", "09:00", "10:00", "Monday");
        System.out.println(test.getDay());
    }

}
