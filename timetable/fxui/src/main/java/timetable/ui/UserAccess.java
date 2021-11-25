package timetable.ui;

import timetable.core.Event;
import timetable.core.Timetable;

public interface UserAccess {
    
    /**
     * 
     * check if user already has a timetable for current week&year
     * 
     * @return true if it exists, false if not
     */
    public boolean hasTimetable(String weekYear);

    /**
     * Adds timetable if it does not exist,
     *  updates timetable if it exists.
     * 
     */ 
    public void addTimetable(Timetable timetable);

    /** 
     * get timetable with @year and @week
     * @return timetable
     */ 
    public Timetable getTimetable(String weekYear);

    /**
     * Private method for removing timetable
     * Used to overwrite existing old timetables
     * 
     */
    void removeTimetable(String weekYear);
    /**
     * Initilizes putTimetable when timetable has been edited
     * 
     */
    public void removeEvent(Timetable timetable, Event event);
}
