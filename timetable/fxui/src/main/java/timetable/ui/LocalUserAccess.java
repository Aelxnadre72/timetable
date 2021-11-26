package timetable.ui;

import timetable.core.Event;
import timetable.core.Timetable;
import timetable.core.User;

/**
 * Gives access to local methods.
 */
public class LocalUserAccess implements UserAccess {
  private final User user;

  public LocalUserAccess() {
    this.user = new User();
  }

  @Override
  public boolean hasTimetable(String weekYear) {
    return this.user.hasTimetable(weekYear);
  }

  @Override
  public void addTimetable(Timetable timetable) {
    this.user.addTimetable(timetable);
  }

  @Override
  public Timetable getTimetable(String weekYear) {
    return this.user.getTimetable(weekYear);
  }

  @Override
  public void removeTimetable(String weekYear) {
    this.user.removeTimetable(weekYear);
  }

  @Override
  public void removeEvent(Timetable timetable, Event event) {
    timetable.removeEvent(event);
  }
}
