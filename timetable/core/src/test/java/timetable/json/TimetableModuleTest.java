package timetable.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import timetable.core.User;
import timetable.core.Timetable;
import timetable.core.Event;

public class TimetableModuleTest {
    private static ObjectMapper mapper;

    @BeforeAll
    public static void setUp() {
        mapper = TimetablePersistence.createMapper();
    }

    final static String timetableWithEvents = """
        {
            "timetables": [{"week":47, "year":2021,"events":[
                {
                    "title": "study",
                    "category": "school",
                    "description": "exam",
                    "time-start": "12:00",
                    "time-end": "14:00",
                    "date": "25.11.2021"},
                 {
                    "title": "study2",
                    "category": "school",
                    "description": "exam",
                    "time-start": "10:00",
                    "time-end": "12:00",
                    "date": "26.11.2021"}
                    ]
                }
                ]
            }
        """;
    static User createTimetableWithTwoEvents() {
        User user = new User();
        Timetable timetable = new Timetable(47, 2021);
        user.addTimetable(timetable);
        Event ev = new Event("study", "school", "exam", "12:00", "14:00", "25.11.2021");
        Event ev1 = new Event("study2", "school", "exam", "10:00", "12:00", "26.11.2021");
        timetable.addEvent(ev);
        timetable.addEvent(ev1);
        return user;
    }

    @Test
    public void testSerializers() {
        User user = createTimetableWithTwoEvents();
        try {
            assertEquals(timetableWithEvents.replaceAll("\\s+", ""), mapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeserializers() {
        try {
            User user = mapper.readValue(timetableWithEvents, User.class);
            Iterator<Timetable> timetables = user.getTimetableList().iterator();
            assertTrue(timetables.hasNext());

            Timetable timetable = timetables.next();
            assertEquals(47,timetable.getWeek());

            Iterator<Event> events = timetable.getEventList().iterator();
            assertTrue(events.hasNext());
            
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }

    @Test 
    public void testSerializersAndDeserializers() {
        User user = new User();
        Timetable timetable = new Timetable(47, 2021);
        user.addTimetable(timetable);
        Event ev = new Event("study", "school", "exam", "12:00", "14:00", "25.11.2021");
        Event ev1 = new Event("study2", "school", "exam", "10:00", "12:00", "26.11.2021");
        timetable.addEvent(ev);
        timetable.addEvent(ev1);

        try {
            String json = mapper.writeValueAsString(user);
            User user1 = mapper.readValue(json, User.class);
            Iterator<Timetable> timetables = user1.getTimetableList().iterator();
            assertTrue(timetables.hasNext());

            Timetable timetable1 = timetables.next();
            assertEquals(47, timetable1.getWeek());

            Iterator<Event> events = timetable.getEventList().iterator();
            assertTrue(events.hasNext());
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }
}
