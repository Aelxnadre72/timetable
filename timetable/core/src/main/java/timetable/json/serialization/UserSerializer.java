package timetable.json.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


import timetable.core.Timetable;
import timetable.core.User;

import java.io.IOException;


public class UserSerializer extends JsonSerializer<User>{
    
    @Override
    public void serialize(User user, JsonGenerator jGenerator, SerializerProvider provider) 
    throws IOException {
        jGenerator.writeStartObject();
        jGenerator.writeArrayFieldStart("timetables");
        for (Timetable timetable : user.getTimetableList()) {
            jGenerator.writeObject(timetable);
        }
        jGenerator.writeEndArray();
        jGenerator.writeEndObject();
        
    }
}
