package timetable.json.serialization;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import timetable.core.Event;
import timetable.core.Timetable;
import timetable.core.User;

/**
 * Module for serializing and deserializing events.
 */
public class TimetableModule extends SimpleModule {
  private static final String NAME = "TimetableModule";

  /**
   * Initializing serializers and deserializers.
   *
   * @param d is true
   */
  public TimetableModule(boolean d) {
    super(NAME, Version.unknownVersion());

    addDeserializer(Event.class, new EventDeserializer());
    addSerializer(Event.class, new EventSerializer());

    addDeserializer(Timetable.class, new TimetableDeserializer());
    addSerializer(Timetable.class, new TimetableSerializer());

    addDeserializer(User.class, new UserDeserializer());
    addSerializer(User.class, new UserSerializer());
  }
}
