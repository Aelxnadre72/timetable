package timetable.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


import java.io.IOException;

public class EventSerializer extends JsonSerializer<Event> {

    
    @Override
    public void serialize(Event event, JsonGenerator jsonGenerator, SerializerProvider provider) 
    throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("title", event.getTitle());
        jsonGenerator.writeStringField("time-start", event.getTimeStart());
        jsonGenerator.writeStringField("time-end", event.getTimeEnd());
        jsonGenerator.writeStringField("description", event.getDescription());
        jsonGenerator.writeStringField("date", event.getDate());
        jsonGenerator.writeEndObject();
    
    }
}