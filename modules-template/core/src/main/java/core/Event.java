package core;
import java.time.LocalDateTime;

public class Event {
    private String title;
    private String description;
    private LocalDateTime timeStart; //getDayOfWeek(); function
    private LocalDateTime timeEnd;
    private LocalDateTime date;
    
    public String getTitle(){
        return eventName;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public LocalDateTime getEventTimeStart(){
        return eventTimeStart;
    }

    public void setEventTimeStart(LocalDateTime eventTimeStart){
        this.eventTimeStart = eventTimeStart;
    }

    public LocalDateTime getEventTimeEnd(){
        return eventTimeEnd;
    }

    public void setEventTimeEnd(LocalDateTime eventTimeEnd){
        this.eventTimeEnd = eventTimeEnd;
    }

    public void setEventDate(LocalDateTime eventDate){
        this.eventDate = eventDate;
    }

    public LocalDateTime getEventDate(){
        return eventDate;
    }

}
