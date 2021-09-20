package core;

public class Event {
    private String eventName;
    private LocalDateTime eventStart; //get day of week
    private LocalDateTime eventEnd;
    private String text;
    
    public String getEventName(){
        return eventName;
    }

    public String setEventName(String eventName){
        this.eventName = eventName;
    }

    public String getText(){
        return text;
    }

    public String setText(String text){
        this.text = text;
    }

    public LocalDateTime getEventStart(){
        return eventStart;
    }

    public LocalDateTime setEventStart(private LocalDateTime eventStart){
        this.eventStart = eventStart;
    }

    public LocalDateTime getEventEnd(){
        return eventEnd;
    }

    public LocalDateTime setEventEnd(private LocalDateTime eventEnd){
        this.eventEnd = eventEnd;
    }
}
