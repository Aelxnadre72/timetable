package timetable.ui;

import timetable.core.Event;
import timetable.core.Timetable;

/**
 * Interface used in RemoteUserAccess and LocalUserAccess.
 */
public interface UserAccess {

  /**
   * Check if user already has a timetable for current week&year.
   *
   * @return true if it exists, false if not
   */
  public boolean hasTimetable(String weekYear);

  /** 
   * Adds timetable if it does not exist, updates timetable if it exists. 
  */
  public void addTimetable(Timetable timetable);

  /**
   * get timetable with @year and @week.
   *
   * @return timetable
   */
  public Timetable getTimetable(String weekYear);

  /** 
   * Private method for removing timetable. 
   */
  void removeTimetable(String weekYear);

  /** 
   * remove event from timetable.
   */
  public void removeEvent(Timetable timetable, Event event);
}
