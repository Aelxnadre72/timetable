package core;
import java.time.LocalDateTime;

public class Event {
    private String title;
    private String description;
    private LocalDateTime timeStart; //getDayOfWeek(); function
    private LocalDateTime timeEnd;
    private LocalDateTime date;
    
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public LocalDateTime getTimeStart(){
        return timeStart;
    }

    public void setEvStart(String timeStart){
        //this.timeStart = timeStart;
    }

    public LocalDateTime getETimeEnd(){
        return timeEnd;
    }

    public void setEventTimeEnd(String eventEnd){
        //this.eventTimeEnd = eventTimeEnd;
    }

    public void setEventDate(String eventDate){
       // this.eventDate = eventDate;
    }

    public LocalDateTime getEventDate(){
        return date;
    }

}
