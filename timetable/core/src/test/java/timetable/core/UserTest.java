package timetable.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {
    
    @Test
    public void testAddTimeTable(){
        Timetable timetable1 = new Timetable(48, 2021);
        User user = new User("Test");
        user.addTimetable(timetable1);
        Assertions.assertEquals(timetable1,user.getTimetable("482021"));
    }
}
