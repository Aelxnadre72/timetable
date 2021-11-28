package timetable.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Create timetable with attributes. */
public class Timetable {
  private List<Event> eventList = new ArrayList<>();
  private int week;
  private int year;

  /**
   * Constructor to set attributes.
   *
   * @param week of timetable
   * @param year of timetable
   * @throws IllegalArgumentException if week or year is invalid
   */
  public Timetable(int week, int year) {
    // set week (53 because some years have 53 weeks)
    if (week < 1 || week > 53) {
      throw new IllegalArgumentException("The week number is invalid.");
    }
    if (year < 2020 || year > 2030) {
      throw new IllegalArgumentException("The year is invalid.");
    }
    this.week = week;
    this.year = year;
  }

  public List<Event> getEventList() {
    return new ArrayList<Event>(eventList);
  }

  /**
   * Add new event in timetable.
   *
   * @param event event in timetable
   * @throws IllegalArgumentException if event will colide with existing event
   */
  public void addEvent(Event event) {
    if (isDuplicateEvent(event)) {
      throw new IllegalArgumentException(
          "The event given to addEvent is colliding (time and date) with an existing event.");
    }
    this.eventList.add(event);
  }

  /**
   * Removes event with specific title, start time and date from eventList.
   *
   * @param event event in timetable
   */
  public void removeEvent(Event event) {
    List<Event> ev =
        eventList.stream()
            .filter(
                e ->
                    e.getDayOfWeek() == event.getDayOfWeek()
                        && e.getTimeStart().equals(event.getTimeStart()))
            .collect(Collectors.toList());
    eventList.remove(ev.get(0));
  }

  /**
   * Checks if the event already exists in eventList.
   *
   * @param event event in timetable
   * @return returns true if it's in the list and false if it's not
   */
  private boolean isDuplicateEvent(Event event) {
    int eventStartTime = Integer.parseInt(event.getTimeStart().substring(0, 2));
    int eventEndTime = Integer.parseInt(event.getTimeEnd().substring(0, 2));
    if (eventEndTime == 0) {
      eventEndTime = 24;
    }
    String eventDate = event.getDate();
    for (Event ev : eventList) {
      int evStartTime = Integer.parseInt(ev.getTimeStart().substring(0, 2));
      int evEndTime = Integer.parseInt(ev.getTimeEnd().substring(0, 2));
      if (evEndTime == 0) {
        evEndTime = 24;
      }

      if (eventDate.equals(ev.getDate())) {
        if (eventEndTime > evStartTime && eventEndTime <= evEndTime) {
          return true;
        } else if (eventStartTime < evEndTime && eventStartTime >= evStartTime) {
          return true;
        } else if (eventStartTime < evStartTime && eventEndTime > evEndTime) {
          return true;
        }
      }
    }
    return false;
  }

  public int getWeek() {
    return week;
  }

  public int getYear() {
    return year;
  }
}
