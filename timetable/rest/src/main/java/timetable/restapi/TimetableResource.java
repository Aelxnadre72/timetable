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

    private void checkTimetable(){ //check timetable or user?
        if (this.timetable == null) {
            throw new IllegalArgumentException("No timetable has id \"" + id + "\"");
        }
    }

    
    

    @GET
    public Timetable getTimetable(){ //timetable eller user?
        checkTimetable();
        LOG.debug("getTimetable({})", id);
        return this.timetable; //timetable/user?
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
   * Replaces or adds a timetable (?)
   * 
   * @param userArg timelist to add
   * @return true if it was added, false if it replaced
   */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean putTimetable(Timetable timetableArg){
        LOG.debug("putTimetable({})", timetableArg);     //putTodoList(): Replaces an existing TodoList with the same name, or adds it.
        Timetable oldTimetable = this.user.putTimetable(timetableArg);     //putUser(userArg);
        autoSaveUser();
        return oldTimetable == null;
        
    }

   /**
    * Adds a timetable, if it doesn't exist already.
    *
    * @return true if it was added, false if it replaced
    */
    @PUT
    public boolean putTimetable(){
        if (timetable != null) {
            return putTimetable(timetable);
        }
        else {  
            //split id into numbers
            String[] idArray = id.split("", 3);
            int week = Integer.parseInt(idArray[0]);
            int year = Integer.parseInt(idArray[1] + idArray[2]);
            return putTimetable(new Timetable(week, year));
        }
    }

    /**
   * Removes the TodoList.
   */
  @DELETE
  public boolean removeTodoList() {
    checkTimetable();
    this.user.removeTimetable(id);
    autoSaveUser();
    return true;
  }

  
  



}
