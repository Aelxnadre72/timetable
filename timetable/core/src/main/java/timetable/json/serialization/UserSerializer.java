package timetable.json.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import timetable.core.Timetable;
import timetable.core.User;

/**
 * Serializer for user-object.
 */
public class UserSerializer extends JsonSerializer<User> {

  @Override
  public void serialize(User user, JsonGenerator jsGenerator, SerializerProvider provider)
      throws IOException {
    jsGenerator.writeStartObject();
    jsGenerator.writeArrayFieldStart("timetables");
    for (Timetable timetable : user.getTimetableList()) {
      jsGenerator.writeObject(timetable);
    }
    jsGenerator.writeEndArray();
    jsGenerator.writeEndObject();
  }
}
