package timetable.restserver;

import java.io.IOException;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import timetable.core.Event;
import timetable.core.Timetable;
import timetable.core.User;
import timetable.json.TimetablePersistence;
import timetable.restapi.UserService;

/**
 * Configuration for user.
 */
public class Config extends ResourceConfig {
  private User user;
  private TimetablePersistence persistence;

  /**
   * Sets user and configurates it for use.
   *
   * @param user user-object to be used
   * @throws IOException if an input/output error has occured
   */
  public Config(User user) {
    setUser(user);
    persistence = new TimetablePersistence();
    persistence.setFilePath("server.json");
    try {
      persistence.saveUser(
          createTestUser()); // legge inn testbruker i serverfilen for å sjekke om det nås fra
      // klienten
    } catch (IOException e) {
      e.printStackTrace();
    }
    register(UserService.class);
    register(UserMapperProvider.class);
    register(JacksonFeature.class);
    register(
        new AbstractBinder() {
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
    Timetable timetable = new Timetable(39, 2021);
    Event event = new Event("math", "school", "description", "09:00", "10:00", "01.10.2021");
    timetable.addEvent(event);
    Event event3 = new Event("norwegian", "school", "description", "09:00", "10:00", "02.10.2021");
    timetable.addEvent(event3);
    Timetable timetable1 = new Timetable(47, 2021);
    Event event1 = new Event("english", "school", "description1", "09:00", "10:00", "24.11.2021");
    timetable1.addEvent(event1);
    Event event2 = new Event("physics", "school", "description1", "09:00", "10:00", "23.11.2021");
    timetable1.addEvent(event2);
    Event event4 = new Event("yoga", "exercise", "description1", "05:00", "07:00", "23.11.2021");
    timetable1.addEvent(event4);
    
    User user = new User();
    user.addTimetable(timetable);
    user.addTimetable(timetable1);
    return user;
  }
}
