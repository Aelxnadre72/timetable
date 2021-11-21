package timetable.restapi;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timetable.core.Timetable;
import timetable.core.User;
import timetable.json.TimetablePersistence;


@Produces(MediaType.APPLICATION_JSON)
public class TimetableResource {

    private static final Logger LOG = LoggerFactory.getLogger(TimetableResource.class);

    private final User user;
    private final String id;
    private final Timetable timetable;

    @Context
    private TimetablePersistence timetablePersistence;

    public void setTimetablePersistence(TimetablePersistence timetablePersistence){
        this.timetablePersistence = timetablePersistence;
    }


    public TimetableResource(Timetable timetable, User user, String id){
        this.timetable = timetable;
        this.user = user;
        this.id = id; 
    }

    private void checkUser(){ //check timetable or user?
        if (this.user == null) {
            throw new IllegalArgumentException("No timetable has id \"" + id + "\"");
        }
    }


    @GET
    public User getUSer(){ //timetable eller user?
        checkUser();
        LOG.debug("getUser({})", id);
        return this.user; //timetable/user?
    }

    private void autoSaveUser(){
        if (timetablePersistence != null){
            try {
                timetablePersistence.saveUser(user); 
            } catch (IllegalStateException | IOException e) {
                System.err.println("Failed to save timetable: " + e);
            }
        }
    }

  /**
   * Replaces or adds a User/timetable (?)
   * IKKE FERDIG
   * @param userArg the todoList to add
   * @return true if it was added, false if it replaced
   */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean putUser(User userArg){
        LOG.debug("putUser({})", userArg);     //putTodoList(): Replaces an existing TodoList with the same name, or adds it.
        //User oldUser = this.user.putUser(userArg);     //putUser(userArg);
        autoSaveUser();
        //return oldUser == null;
        return false;
    }

   /**
    * Adds a User with the given id, if it doesn't exist already.
    *
    * @return true if it was added, false if it replaced
    */
    @PUT
    public boolean putUser(){
        return putUser(new User(id));
    }

  /**
   * Renames the User/Timetable.
   * skal vi ha dette?
   * @param newName the new name
   */
  



 /**
   * Removes the TodoList.
   * skal vi ha delete?
   */

  /* @DELETE
    public boolean removeTodoList() {
        checkTodoList();
        this.todoModel.removeTodoList(this.todoList);
        autoSaveTodoModel();
        return true;
  }

   */

}
