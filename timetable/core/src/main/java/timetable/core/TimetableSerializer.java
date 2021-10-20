package timetable.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import timetable.core.Timetable;
import timetable.core.Event;


class TimetableSerializer extends JsonSerializer<Timetable> {
    
    
    public void serialize(Timetable timetable, JsonGenerator jGenerator, SerializerProvider provider)
    throws IOException {
        jGenerator.writeStartObject();
        jGenerator.writeNumberField("week", timetable.getWeek());
        jGenerator.writeNumberField("year", timetable.getYear());
        if (timetable instanceof Timetable) {
            jGenerator.writeArrayFieldStart("events");
            for (Event event : timetable.getEventList()) {
                jGenerator.writeObject(event);
            }
            jGenerator.writeEndArray();
        }
        jGenerator.writeEndObject();
    }
    
}
