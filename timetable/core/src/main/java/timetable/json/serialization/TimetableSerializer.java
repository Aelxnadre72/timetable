package timetable.json.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import timetable.core.Event;
import timetable.core.Timetable;

class TimetableSerializer extends JsonSerializer<Timetable> {

  public void serialize(Timetable timetable, JsonGenerator jsGenerator, SerializerProvider provider)
      throws IOException {
    jsGenerator.writeStartObject();
    jsGenerator.writeNumberField("week", timetable.getWeek());
    jsGenerator.writeNumberField("year", timetable.getYear());

    jsGenerator.writeArrayFieldStart("events");
    for (Event event : timetable.getEventList()) {
      jsGenerator.writeObject(event);
    }
    jsGenerator.writeEndArray();
    jsGenerator.writeEndObject();
  }
}
