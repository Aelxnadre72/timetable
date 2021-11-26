package timetable.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimetableTest {

  Timetable timetable;

  @BeforeEach
  public void initialize() {
    timetable = new Timetable(48, 2021);
  }

  @Test
  public void testAddEvent() {
    Event event = new Event("Homework", "school", "At home", "14:00", "17:00", "22.11.2021");
    timetable.addEvent(event);
    Assertions.assertEquals(event, timetable.getEventList().get(0));
    Assertions.assertEquals(1, timetable.getEventList().size());
    Event event2 = new Event("Workout", "exercise", "At the gym", "14:00", "17:00", "22.11.2021");
    Assertions.assertThrows(IllegalArgumentException.class, () -> timetable.addEvent(event2));
    Event event3 = new Event("Seminar", "work", "At the office", "15:00", "00:00", "23.11.2021");
    timetable.addEvent(event3);
    Event event4 = new Event("Seminar", "work", "At the office", "15:00", "00:00", "24.11.2021");
    timetable.addEvent(event4);
    Event event5 = new Event("Workout", "exercise", "At the gym", "13:00", "18:00", "22.11.2021");
    Assertions.assertThrows(IllegalArgumentException.class, () -> timetable.addEvent(event5));
    Event event6 = new Event("Gym", "exercise", "At School", "13:00", "15:00", "22.11.2021");
    Assertions.assertThrows(IllegalArgumentException.class, () -> timetable.addEvent(event6));
    Event event7 = new Event("Skiing", "social", "Hurdal", "15:00", "18:00", "22.11.2021");
    Assertions.assertThrows(IllegalArgumentException.class, () -> timetable.addEvent(event7));
    Event event8 = new Event("Get pizza", "eat", "At home", "15:00", "16:00", "22.11.2021");
    Assertions.assertThrows(IllegalArgumentException.class, () -> timetable.addEvent(event8));
  }

  @Test
  public void testConstructor() {
    Assertions.assertEquals(48, timetable.getWeek());
    Assertions.assertEquals(2021, timetable.getYear());
    Assertions.assertThrows(IllegalArgumentException.class, () -> new Timetable(54, 2021));
    Assertions.assertThrows(IllegalArgumentException.class, () -> new Timetable(0, 2022));
    Assertions.assertThrows(IllegalArgumentException.class, () -> new Timetable(34, 2019));
    Assertions.assertThrows(IllegalArgumentException.class, () -> new Timetable(14, 2040));
  }

  @Test
  public void testRemoveEvent() {
    Event event = new Event("Homework", "school", "At home", "14:00", "16:00", "23.11.2021");
    timetable.addEvent(event);
    Assertions.assertEquals(1, timetable.getEventList().size());
    timetable.removeEvent(event);
    Assertions.assertEquals(0, timetable.getEventList().size());
  }
}
