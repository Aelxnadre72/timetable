package timetable.restapi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timetable.core.User;
import timetable.core.Timetable;
import timetable.json.TimetablePersistance;


/**
 * The top-level rest service for timetable/user.
 */
@Path(UserService.USER_SERVICE_PATH)
@Produces(MediaType.APPLICATION_JSON)

public class UserService {

  public static final String USER_SERVICE_PATH = "user"; //todo 

  private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

  @Context
  private User user;  //todoModel

  @Context
  private TimetablePersistance timetablePersistence;

/**
   * The root resource, i.e. /timetable
   *
   * @return the timetable (User??)   //todomodel
   */
  @GET
  public User getUser() {    //getTodoModel
    LOG.debug("getUser: " + user);  //henter fra UserResource 
    return user;   //return todomodel
  }

  
  /**
   * USIKKER, har ikke denne modulen
   *The root resource, i.e. /timetable    //todo
   *
   * @return the timetable        //TodoModel
   */
   /*
  @Path("/settings")
  public TodoSettingsResource getTodoSettings() {
    LOG.debug("Sub-resource for TodoSettings");
    return new TodoSettingsResource(todoModel);
  }
  */


/**
   * Returns the Timetable/User with the provided id
   * (as a resource to support chaining path elements).
   * This supports all requests referring to Timetable/User by id.
   * Note that the TodoList needn't exist, since it can be a PUT. ???
   *
   * @param id the id of the user/timetable
   */
  @Path("/{id}")  //"/list/{@week+@year}"
  public TimetableResource getUser(@PathParam("id") String id) {
    Timetable timetable = getUser().getTimetable(id);    //AbstractTodoList todoList = getTodoModel().getTodoList(name);
    LOG.debug("Sub-resource for User " + id + ": " + user);
    TimetableResource timetableResource = new TimetableResource(timetable, user, id); //Timetable timetable, User user, String id
    timetableResource.setTimetablePersistence(timetablePersistence);
    return timetableResource;
  }

}