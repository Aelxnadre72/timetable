package timetable.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import timetable.core.User;
import timetable.json.serialization.TimetableModule;
import timetable.core.Timetable;
import timetable.core.Event;



public class TimetablePersistenceTest {
    private TimetablePersistence timetablePersistence = new TimetablePersistence();

    private User createSamplUser(){
        User user = new User("1");
        Timetable timetable = new Timetable(47, 2021);
        Event event1 = new Event("study", "school", "study for exam", "12:00", "14:00", "25.11.2021");
        Event event2 = new Event("study2", "school", "study for exam", "10:00", "12:00", "26.11.2021");
        timetable.addEvent(event1);
        timetable.addEvent(event2);
        user.addTimetable(timetable);
        timetable.removeEvent(event1);
        //user.getTimetable("1");
        //user.hasTimetable("1");

        return user;
    }

    private void checkSampleUser(User user){
        Iterator<Timetable> it = user.getTimetableList().iterator();
        assertTrue(it.hasNext());

        Timetable timetable = it.next(); //usikker
        Iterator<Event> ev = timetable.getEventList().iterator();
        assertTrue(ev.hasNext());




    }


}
