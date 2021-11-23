package timetable.core;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

public class TimetableTest {
    
    @Test
    public void testAddEvent(){
        Event event = new Event("Homework", "work", "At home", "14:00", "16:00", "22.11.2021");
        Timetable timetable = new Timetable(47, 2021);
        timetable.addEvent(event);
        Assertions.assertEquals(event, timetable.getEventList().get(0));
        Assertions.assertEquals(1, timetable.getEventList().size());
    }

    @Test
    public void testConstructor(){
        Timetable timetable = new Timetable(47, 2021);
        Assertions.assertEquals(47, timetable.getWeek());
        Assertions.assertEquals(2021, timetable.getYear());
    }

}
