package timetable.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class UserTest {

    Timetable timetable;
    User user;

    
    @BeforeEach
    public void initialize(){
        timetable = new Timetable(48, 2021);
        user = new User();
        user.addTimetable(timetable);
    }
    
    @Test
    public void testAddTimeTable(){
        Assertions.assertEquals(timetable, user.getTimetable("482021"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.addTimetable(timetable));
    }

    @Test
    public void testGetTimeTable(){
        Assertions.assertEquals(timetable, user.getTimetable("482021"));
        Assertions.assertEquals(null, user.getTimetable("502021"));
    }

    @Test
    public void testGetTimetableList(){
        Assertions.assertEquals(timetable, user.getTimetableList().get(0));
    }

}
