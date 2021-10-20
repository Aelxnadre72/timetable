package timetable.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

import timetable.core.User;
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
