package timetable.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {
  private Event event;

  @BeforeEach
  public void makeEvent() {
    event = new Event("Work", "work", "At the office", "08:00", "09:00", "01.04.2022");
  }

  @Test
  public void testSetTimeStart() {
    event.setTimeStart("07:30");
    Assertions.assertEquals("07:30", event.getTimeStart());
  }

  @Test
  public void testTimeStartExceptionTest() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> event.setTimeStart("99:99"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> event.setTimeStart("100:30"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> event.setTimeStart("fem"));
  }

  @Test
  public void SetTimeEndExceptionTest() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> event.setTimeEnd("99:99"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> event.setTimeEnd("100:30"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> event.setTimeEnd("fem"));
  }

  @Test
  public void testSetTitle() {
    event.setTitle("Gym");
    Assertions.assertEquals("Gym", event.getTitle());
  }

  @Test
  public void testSetCategory() {
    event.setCategory("social");
    Assertions.assertEquals("social", event.getCategory());
  }

  @Test
  public void testDate() {
    event.setDate("08.04.2021");
    Assertions.assertEquals("08.04.2021", event.getDate());
    Assertions.assertThrows(IllegalArgumentException.class, () -> event.setDate("8.4.2021"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> event.setDate("08.04.2018"));
  }

  @Test
  public void testSetDecription() {
    event.setDescription("At home");
    Assertions.assertEquals("At home", event.getDescription());
  }

  @Test
  public void testSetTimeEnd() {
    event.setTimeEnd("10:30");
    Assertions.assertEquals("10:30", event.getTimeEnd());
  }

  @Test
  public void testConstructor() {
    Assertions.assertEquals("08:00", event.getTimeStart());
    Assertions.assertEquals("09:00", event.getTimeEnd());
    Assertions.assertEquals("Work", event.getTitle());
    Assertions.assertEquals("At the office", event.getDescription());
    Assertions.assertEquals("01.04.2022", event.getDate());
    Assertions.assertEquals(5, event.getDayOfWeek());
    Assertions.assertEquals("work", event.getCategory());
    Assertions.assertEquals(2022, event.getYear());
    Assertions.assertEquals(13, event.getWeek());
  }

  @Test
  public void constructorExceptionTest() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "09:00", "01.04.2018"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "20004:00", "01.04.2022"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "81:81", "09:00", "01.04.2022"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "1:000", "01.04.2022"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "five", "01.04.2022"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "four", "20:00", "01.04.2022"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "99:00", "01.04.2022"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "09:00", "2022.04.02"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "09:00", "1.4.18"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "09:00", "01.11.2050"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "09:61", "01.11.2022"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "09:30", "33.11.2022"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new Event("Work", "work", "At the office", "08:00", "09:00", "13.13.2022"));
  }
}
