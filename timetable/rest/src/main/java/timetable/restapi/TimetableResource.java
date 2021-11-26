package timetable.restapi;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timetable.core.Timetable;
import timetable.core.User;
import timetable.json.TimetablePersistence;

/**
 * Resource for managing timetables.
 */
@Produces(MediaType.APPLICATION_JSON)
public class TimetableResource {
  private static final Logger LOG = LoggerFactory.getLogger(TimetableResource.class);
  private final User user;
  private final String id;
  private final Timetable timetable;

  @Context private TimetablePersistence timetablePersistence;

  public void setTimetablePersistence(TimetablePersistence timetablePersistence) {
    this.timetablePersistence = timetablePersistence;
  }

  /**
   * Sets timetable, user and id.
   *
   * @param timetable timetable-object
   * @param user user-object
   * @param id timetable-id
   */
  public TimetableResource(Timetable timetable, User user, String id) {
    this.timetable = timetable;
    this.user = user;
    this.id = id;
  }

  private void checkTimetable() { 
    if (this.timetable == null) {
      throw new IllegalArgumentException("No timetable has id \"" + id + "\"");
    }
  }

  /**
   * Gets timetable.
   *
   * @return timetable
   */
  @GET
  public Timetable getTimetable() { 
    checkTimetable();
    LOG.debug("getTimetable({})", id);
    return this.timetable;
  }

  private void autoSaveUser() {
    if (timetablePersistence != null) {
      try {
        timetablePersistence.saveUser(user);
      } catch (IllegalStateException | IOException e) {
        System.err.println("Failed to save timetable: " + e);
      }
    }
  }

  /**
   * Adds a timetable.
   *
   * @param timetableArg timelist to add
   * @return true if it was added, false if it replaced
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public boolean addTimetable(Timetable timetableArg) {
    LOG.debug(
        "addTimetable({})",
        timetableArg); // timetable(): Replaces an existing timetable with the same name, or adds
    // it.
    Timetable oldTimetable = this.user.addTimetable(timetableArg); // putUser(userArg);
    autoSaveUser();
    return oldTimetable == null;
  }

  /**
   * Adds a timetable, if it doesn't exist already.
   *
   * @return true if it was added, false if it replaced
   */
  @PUT
  public boolean addTimetable() {
    if (timetable != null) {
      return addTimetable(timetable);
    } else {
      // split id into numbers
      String[] idArray = id.split("", 3);
      int week = Integer.parseInt(idArray[0]);
      int year = Integer.parseInt(idArray[1] + idArray[2]);
      return addTimetable(new Timetable(week, year));
    }
  }

  /** 
   * Removes the timetable. 
   */
  @DELETE
  public boolean removeTimetable() {
    checkTimetable();
    this.user.removeTimetable(id);
    autoSaveUser();
    return true;
  }
}
