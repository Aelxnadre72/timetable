package timetable.json;

import java.io.File;
import java.io.IOException;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;

import com.fasterxml.jackson.databind.ObjectMapper;


import timetable.core.Event;
import timetable.core.Timetable;
import timetable.core.User;
import timetable.json.serialization.TimetableModule;




public class Json {
    
    // Method for reading from json file
    public User read(User user) {
        User tempUser = new User("1");
        ObjectMapper objectMapper = new ObjectMapper();
        TimetableModule readModule = new TimetableModule(true); 
        objectMapper.registerModule(readModule); 
        try {
            // getClass().getClassLoader().getResource("Events.json").getFile() ?
            tempUser = objectMapper.readValue(new File("timetable\\core\\src\\main\\resources\\Events.json"), User.class);
            
        } catch (JsonProcessingException e) { 
            System.out.println(e);

        } catch (IOException e) {
            System.out.println(e);
        }
        
        for (Timetable timetable : tempUser.getTimetableList()) {
            int size = timetable.getEventList().size();
            if ( size != 0) {
                user.addTimetable(timetable);
            }
        }
        
        return user;
    }
    
    // Method for writing to json file
    public void write(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        TimetableModule writeModule = new TimetableModule(true);
        objectMapper.registerModule(writeModule);
        try {
            objectMapper.writeValue(new File("timetable\\core\\src\\main\\resources\\Events.json"), user);

        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        }
     }

    
    
