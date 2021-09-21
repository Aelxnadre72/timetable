package core;
import java.time.LocalDateTime;

public class Event {
    private String eventName;
    private LocalDateTime eventTimeStart; //getDayOfWeek(); function
    private LocalDateTime eventTimeEnd;
    private String text;
    private LocalDateTime eventDate;
    
    public String getEventName(){
        return eventName;
    }

    public void setEventName(String eventName){
        this.eventName = eventName;
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

    public void setEventStart(LocalDateTime eventTimeStart){
        this.eventTimeStart = eventTimeStart;
    }

    public LocalDateTime getEventEnd(){
        return eventTimeEnd;
    }

    public void setEventEnd(LocalDateTime eventTimeEnd){
        this.eventTimeEnd = eventTimeEnd;
    }

    public void setEventDate(LocalDateTime eventDate){
        this.eventDate = eventDate;
    }

    public LocalDateTime getEventDate(){
        return eventDate;
    }

}
