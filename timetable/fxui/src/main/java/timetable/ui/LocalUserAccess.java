package timetable.ui;

import timetable.core.Timetable;
import timetable.core.User;

public class LocalUserAccess implements UserAccess {
    private final User user;

    public LocalUserAccess(User user) {
        this.user = user;
    }
    
    @Override
    public boolean hasTimetable(String weekYear) {
        return this.user.hasTimetable(weekYear);
    }

    @Override
    public void putTimetable(Timetable timetable) {
        this.user.putTimetable(timetable);
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
    public void notifyTimetableChanged(Timetable timetable) {
        putTimetable(timetable);
    }
}
