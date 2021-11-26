package timetable.restapi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timetable.core.Timetable;
import timetable.core.User;
import timetable.json.TimetablePersistence;

/** The top-level rest service for timetable/user. */
@Path(UserService.USER_SERVICE_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

  public static final String USER_SERVICE_PATH = "user";

  private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

  @Context private User user;

  @Context private TimetablePersistence timetablePersistence;

  /**
   * The root resource, i.e. /timetable
   *
   * @return the user
   */
  @GET
  public User getUser() {
    LOG.debug("getUser: " + user);
    return user;
  }

  /**
   * Returns the Timetable with the provided id (as a resource to support chaining path elements).
   * This supports all requests referring to Timetable by id. Note that the timetable needn't exist,
   * since it can be a PUT.
   *
   * @param weekYear id of timetable
   */
  @Path("/timetable/{weekYear}") // "/list/{@week+@year}"
  public TimetableResource getTimetable(@PathParam("weekYear") String weekYear) {
    System.out.println("got " + weekYear); // programflyt
    Timetable timetable = getUser().getTimetable(weekYear);
    LOG.debug("Sub-resource for Timetable " + weekYear + ": " + timetable);
    TimetableResource timetableResource =
        new TimetableResource(
            timetable, user, weekYear); // Timetable timetable, User user, String id
    timetableResource.setTimetablePersistence(timetablePersistence);
    return timetableResource;
  }
}
