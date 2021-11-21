package timetable.restserver;

import timetable.core.User;
import timetable.json.TimetablePersistence;
import timetable.restapi.UserService;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Config extends ResourceConfig{
    
    private User user;
    private TimetablePersistence persistence;

    public Config(User user) {
        this.user = user;
        persistence = new TimetablePersistence();
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
    

    
}
