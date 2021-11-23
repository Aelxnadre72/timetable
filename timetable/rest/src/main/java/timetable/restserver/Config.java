package timetable.restserver;

import timetable.core.User;
import timetable.json.TimetablePersistence;
import timetable.restapi.UserService;

import java.io.Reader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import java.net.URL;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import timetable.core.Event;
import timetable.core.Timetable;
import timetable.core.User;

public class Config extends ResourceConfig{
    
    private User user;
    private TimetablePersistence persistence;

    public Config(User user) {
        setUser(user);
        persistence = new TimetablePersistence();
        //persistence.setFilePath("server.json");
        register(UserService.class);
        register(UserMapperProvider.class);
        register(JacksonFeature.class);
        register(new AbstractBinder() {
        @Override
        protected void configure() {
            bind(Config.this.user);
            bind(Config.this.persistence);
        }
    });
}
    public Config() {
        this(createTestUser());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    private static User createTestUser() {
        Event event = new Event("math", "school", "description", "09:00", "10:00", "01.10.2021" );
        Event event3 = new Event("norwegian", "school","description", "09:00", "10:00", "02.10.2021" );
        Event event1 = new Event("english", "school","description1", "09:00", "10:00", "08.10.2021" );
        Event event2 = new Event("physics", "school","description1", "09:00", "10:00", "09.10.2021" );
        Timetable timetable = new Timetable(39, 2021);
        Timetable timetable1 = new Timetable(40, 2021);
        User user = new User();
        timetable.addEvent(event);
        timetable.addEvent(event3);
        timetable1.addEvent(event1);
        timetable1.addEvent(event2);
        user.addTimetable(timetable);
        user.addTimetable(timetable1);
        return user;
            
        
        }
    
    
    

    
}
