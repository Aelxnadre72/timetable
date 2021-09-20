package core;
import java.time.LocalDateTime;

public class Event {
    private String eventName;
    private LocalDateTime eventStart; // getDayOfWeek(); function
    private LocalDateTime eventEnd;
    private String text;
    
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

    public LocalDateTime getEventStart(){
        return eventStart;
    }

    public void setEventStart(LocalDateTime eventStart){
        this.eventStart = eventStart;
    }

    public LocalDateTime getEventEnd(){
        return eventEnd;
    }

    public void setEventEnd(LocalDateTime eventEnd){
        this.eventEnd = eventEnd;
    }
}
