package timetable.ui;

import timetable.core.Timetable;

public interface UserAccess {
    
    // check if user already has a timetable for current week&year
    public boolean hasTimetable(int week, int year);

    // add timetable to user
    void addTimetable(Timetable timetable);

    // get timetable with @year and @week
    Timetable getTimetable(int week, int year);
    
}
