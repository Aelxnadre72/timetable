package timetable.restserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Iterator;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timetable.core.Timetable;
import timetable.core.User;
import timetable.restapi.UserService;

public class UserServiceTest extends JerseyTest {

  protected boolean shouldLog() {
    return true;
  }

  @Override
  public ResourceConfig configure() {
    final Config config = new Config();
    if (shouldLog()) {
      enable(TestProperties.LOG_TRAFFIC);
      enable(TestProperties.DUMP_ENTITY);
      config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "WARNING");
    }
    return config;
  }

  static ObjectMapper mapper;

  @BeforeEach
  @Override
  public void setUp() throws Exception {
    super.setUp();

    mapper = new UserMapperProvider().getContext(getClass());
  }

  @AfterEach
  @Override
  public void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void restTest() {
    Response getResponse =
        target(UserService.USER_SERVICE_PATH)
            .request(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
            .get();
    assertEquals(200, getResponse.getStatus());
    try {
      User user = mapper.readValue(getResponse.readEntity(String.class), User.class);
      Iterator<Timetable> timetables = user.getTimetableList().iterator();
      assertTrue(timetables.hasNext());
      Timetable timetable1 = timetables.next();
      assertEquals(39, timetable1.getWeek());
      assertTrue(timetables.hasNext());

      Timetable timetable2 = timetables.next();
      assertFalse(timetables.hasNext());

      assertEquals(39, timetable1.getWeek());
      assertEquals(47, timetable2.getWeek());

    } catch (JsonProcessingException e) {
      fail(e.getMessage());
    }
  }
}
