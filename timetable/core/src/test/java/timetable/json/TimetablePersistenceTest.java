package timetable.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import timetable.core.Event;
import timetable.core.Timetable;
import timetable.core.User;

public class TimetablePersistenceTest {
  private TimetablePersistence timetablePersistence = new TimetablePersistence();

  private User createSamplUser() {
    User user = new User();
    Timetable timetable = new Timetable(47, 2021);
    Event event1 = new Event("study", "school", "exam", "12:00", "14:00", "25.11.2021");
    Event event2 = new Event("study2", "school", "exam", "10:00", "12:00", "26.11.2021");
    timetable.addEvent(event1);
    timetable.addEvent(event2);
    user.addTimetable(timetable);
    timetable.removeEvent(event1);

    return user;
  }

  private void checkSampleUser(User user, User user1) {
    Iterator<Timetable> time = user.getTimetableList().iterator();
    Iterator<Timetable> time1 = user1.getTimetableList().iterator();
    assertTrue(time.hasNext());
    assertTrue(time1.hasNext());

    Timetable timetable = time.next();
    Timetable timetable1 = time1.next();
    assertEquals(timetable.getWeek(), timetable1.getWeek());
    Iterator<Event> ev = timetable.getEventList().iterator();
    Iterator<Event> ev1 = timetable1.getEventList().iterator();
    assertTrue(ev.hasNext());
    assertTrue(ev1.hasNext());
  }

  @Test
  public void testSerializersDeserializers_StringWriter() {
    User user = createSamplUser();
    try {
      StringWriter writer = new StringWriter();
      timetablePersistence.writeUser(user, writer);
      String json = writer.toString();
      User user1 = timetablePersistence.readUser(new StringReader(json));
      checkSampleUser(user, user1);
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testSerializersDeserializers_SaveFiles() {
    User user = createSamplUser();

    timetablePersistence.setFilePath("user-" + System.currentTimeMillis() + ".json");
    Path savePath = timetablePersistence.getSaveFilePath();
    try {
      timetablePersistence.saveUser(user);
      assertTrue(Files.exists(savePath));
      User user1 = timetablePersistence.loadUser();
      checkSampleUser(user, user1);
    } catch (IOException e) {
      fail(e.getMessage());
    } finally {
      try {
        Files.deleteIfExists(savePath);
      } catch (IOException e) {

      }
    }
  }
}
