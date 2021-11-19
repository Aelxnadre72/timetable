package timetable.restserver;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import timetable.json.TimetablePersistence;
import timetable.core.Timetable;
import timetable.json.Json;
/**
 * Jackson-module provider for serialization
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserMapperProvider implements ContextResolver<ObjectMapper> {

  private final ObjectMapper mapper;

  public UserMapperProvider() {
    mapper = TimetablePersistence.createMapper();
  }

  @Override
  public ObjectMapper getContext(final Class<?> type) {
    return mapper;
  }
}
