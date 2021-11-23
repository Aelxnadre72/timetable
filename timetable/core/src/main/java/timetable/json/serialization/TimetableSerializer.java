package timetable.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


import timetable.core.Event;
import timetable.core.Timetable;



class TimetableSerializer extends JsonSerializer<Timetable> {
    
    
    public void serialize(Timetable timetable, JsonGenerator jGenerator, SerializerProvider provider)
    throws IOException {
        jGenerator.writeStartObject();
        jGenerator.writeNumberField("week", timetable.getWeek());
        jGenerator.writeNumberField("year", timetable.getYear());

/*         spotbugs ga bug på linjen timetable instanceof Timetable om at den var unødvendig, kommenterer den derfor ut:
        [ERROR] Medium: instanceof will always return true for all non-null 
        values in timetable.core.TimetableSerializer.serialize(Timetable, JsonGenerator, SerializerProvider), 
        since all timetable.core.Timetable are instances of timetable.core.Timetable [timetable.core.TimetableSerializer] 
        At TimetableSerializer.java:[line 19] BC_VACUOUS_INSTANCEOF */

        //if (timetable instanceof Timetable) {
            jGenerator.writeArrayFieldStart("events");
            for (Event event : timetable.getEventList()) {
                jGenerator.writeObject(event);
            }
            jGenerator.writeEndArray();
        //}
        jGenerator.writeEndObject();
    }
    
}
