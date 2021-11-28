package timetable.json.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import timetable.core.Event;

/** Serializer for Event-object. */
public class EventSerializer extends JsonSerializer<Event> {

  @Override
  public void serialize(Event event, JsonGenerator jsonGenerator, SerializerProvider provider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("title", event.getTitle());
    jsonGenerator.writeStringField(
        "category",
        event.getCategory()); // added this line after adding attribute category in event-class
    jsonGenerator.writeStringField("description", event.getDescription());
    jsonGenerator.writeStringField("time-start", event.getTimeStart());
    jsonGenerator.writeStringField("time-end", event.getTimeEnd());
    jsonGenerator.writeStringField("date", event.getDate());
    jsonGenerator.writeEndObject();
  }
}
