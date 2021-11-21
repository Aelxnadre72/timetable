package timetable.ui;

import timetable.core.Timetable;
import timetable.core.User;

public class LocalUserAccess implements UserAccess {
    private final User user;

    public LocalUserAccess(User user) {
        this.user = user;
    }
    
    @Override
    public boolean hasTimetable(int week, int year) {
        return user.hasTimetable(String.valueOf(week) + String.valueOf(year));
    }

    @Override
    public void addTimetable(Timetable timetable) {
        this.user.addTimetable(timetable);
    }

    @Override
    public Timetable getTimetable(int week, int year) {
        return user.getTimetable(String.valueOf(week) + String.valueOf(year));
    }
    
    
}
