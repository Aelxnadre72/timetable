package timetable.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
            "events: " [
                ""
            ]
        }
        """;
}
