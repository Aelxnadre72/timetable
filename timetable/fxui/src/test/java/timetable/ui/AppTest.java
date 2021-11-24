package timetable.ui;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import timetable.core.Event;

public class AppTest extends ApplicationTest{

    private AppController controller;
    private Map<ListView<String>, List<Event>> eventMap;
    private List<ListView<String>> days;
    private Event event1, event2, event3, event4;
    private int currentYear;
    private int currentHour;
    
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("AppTest.fxml"));
        final Parent parent = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
        this.eventMap = controller.getEventMap();
        this.days = controller.getDays();
        controller.clearUserForTest();
        stage.setScene(new Scene(parent));
        stage.show();
    }
    
    @BeforeEach
    public void setup() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
        currentYear = cal.get(Calendar.YEAR);
        currentHour = cal.get(Calendar.HOUR_OF_DAY);
        event1 = new Event("Night shift", "work", "At the hospital","00:00","06:00","09.03.2022");
        event2 = new Event("Workout", "exercise", "At the gym","05:00","07:00","10.03.2022");
        clickOn("#weekTen");
        if(currentYear != 2022){
            selectChoiceBox("#year",2022, currentYear);
            clickOn("#goButton");
        }
    }

    @Test
    public void testAddEvent1(){
        //Test for event nr1
        assertEquals(true, checkEmptyTimetable());
        clickOn("#newTitle").write("Night shift");
        clickOn("#newCategory");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#newDate");
        type(KeyCode.BACK_SPACE, 10);
        clickOn(((DatePicker)lookup("#newDate").query()).getEditor()).write("09.03.2022\n");
        selectChoiceBox("#newStartTime",0, currentHour);
        selectChoiceBox("#newEndTime", 6, currentHour+1);
        clickOn("#newDescription").write("At the hospital");
        clickOn("#addEventButton");
        assertEquals(false, checkEmptyTimetable());
        checkEvent(event1);
    }

    @Test
    public void testAddEvent2(){
        //Test for event nr2
        assertEquals(true, checkEmptyTimetable());
        clickOn("#newTitle").write("Workout");
        clickOn("#newCategory");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#newDate");
        type(KeyCode.BACK_SPACE, 10);
        clickOn(((DatePicker)lookup("#newDate").query()).getEditor()).write("10.03.2022\n");
        selectChoiceBox("#newStartTime",5, currentHour);
        selectChoiceBox("#newEndTime", 7, currentHour+1);
        clickOn("#newDescription").write("At the gym");
        clickOn("#addEventButton");
        assertEquals(false, checkEmptyTimetable());
        checkEvent(event2);
    }

    @Test
    public void testNavigatingWeeks(){
        for (int i = 0; i < 11; i++) {
            clickOn("#prevWeekButton");
          }
          for (int i = 0; i < 2; i++) {
            clickOn("#nextWeekButton");
          }
        
        event3 = new Event("Morning yoga", "exercise", "At the studio","05:00","07:00","04.01.2022");
        assertEquals(true, checkEmptyTimetable());
        clickOn("#newTitle").write("Morning yoga");
        clickOn("#newCategory");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#newDate");
        type(KeyCode.BACK_SPACE, 10);
        clickOn(((DatePicker)lookup("#newDate").query()).getEditor()).write("04.01.2022\n");
        selectChoiceBox("#newStartTime",5, currentHour);
        selectChoiceBox("#newEndTime", 7, currentHour+1);
        clickOn("#newDescription").write("At the studio");
        clickOn("#addEventButton");
        assertEquals(false, checkEmptyTimetable());
        checkEvent(event3);
    }

    @Test
    public void testAddEventRestrictions(){
       
        assertEquals(true, checkEmptyTimetable());
        clickOn("#newTitle").write("Workout");
        clickOn("#newCategory");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#newDate");
        type(KeyCode.BACK_SPACE, 10);
        clickOn(((DatePicker)lookup("#newDate").query()).getEditor()).write("10.03.2022\n");
        selectChoiceBox("#newStartTime",5, currentHour);
        selectChoiceBox("#newEndTime", 7, currentHour+1);
        clickOn("#addEventButton");
        assertEquals(true, checkEmptyTimetable());

        clickOn("#newCategory");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#newDate");
        type(KeyCode.BACK_SPACE, 10);
        clickOn(((DatePicker)lookup("#newDate").query()).getEditor()).write("11.03.2022\n");
        selectChoiceBox("#newStartTime",5, currentHour);
        selectChoiceBox("#newEndTime", 7, currentHour+1);
        clickOn("#newDescription").write("At the gym");
        clickOn("#addEventButton");
        assertEquals(true, checkEmptyTimetable());
        
        clickOn("#newTitle").write("Workout");
        clickOn("#newCategory");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#newDate");
        type(KeyCode.BACK_SPACE, 10);
        clickOn(((DatePicker)lookup("#newDate").query()).getEditor()).write("10.03.2022\n");
        selectChoiceBox("#newStartTime",7, currentHour);
        selectChoiceBox("#newEndTime", 5, currentHour+1);
        clickOn("#newDescription").write("At the gym");
        clickOn("#addEventButton");
        assertEquals(true, checkEmptyTimetable());

    }

    @Test
    public void testWeekFiftyThree() throws InterruptedException{
        selectChoiceBox("#year",2026, 2022);
        clickOn("#goButton");
        for (int i = 0; i < 10; i++) {
            clickOn("#prevWeekButton");
          }
        event4 = new Event("Wedding", "social", "At the beach","18:00","00:00","03.01.2027");
        clickOn("#newTitle").write("Wedding");
        clickOn("#newCategory");
        type(KeyCode.ENTER);
        clickOn("#newDate");
        type(KeyCode.BACK_SPACE, 10);
        clickOn(((DatePicker)lookup("#newDate").query()).getEditor()).write("03.01.2027\n");
        selectChoiceBox("#newStartTime",18, currentHour);
        selectChoiceBox("#newEndTime", 00, currentHour+1);
        clickOn("#newDescription").write("At the beach");
        clickOn("#addEventButton");
        assertEquals(false, checkEmptyTimetable());
        checkEvent(event4);
        selectChoiceBox("#year", 2027, 2026);
    }

    @Test
    public void testSelectAndDelete(){
        assertEquals(true, checkEmptyTimetable());
        clickOn("#newTitle").write("Night shift");
        clickOn("#newCategory");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#newDate");
        type(KeyCode.BACK_SPACE, 10);
        clickOn(((DatePicker)lookup("#newDate").query()).getEditor()).write("09.03.2022\n");
        selectChoiceBox("#newStartTime",0, currentHour);
        selectChoiceBox("#newEndTime", 6, currentHour+1);
        clickOn("#newDescription").write("At the hospital");
        clickOn("#addEventButton");

        assertEquals(false, checkEmptyTimetable());
        checkEvent(event1);

        type(KeyCode.TAB, 60);
        type(KeyCode.DOWN);
        clickOn("#deleteButton");

        assertEquals(true, checkEmptyTimetable());
    }

    private Boolean checkEmptyTimetable(){
        for(ListView<String> day : days){
            for(String title : day.getItems()){
                if(!title.equals("")){
                    return false;
                }
            }
        }
        for(List<Event> l : eventMap.values()){
            for(Event e : l){
                if(e != null){
                    return false;
                }
            }
        }
        return true;
    }
    

    private void checkEvent(Event event){
        for(String title : days.get(event.getDayOfWeek() - 1).getItems()){
            if(!title.equals("")){
                assertEquals(event.getCategory() + ": " + event.getTitle(), title);
            }
        }
        for(Event e : eventMap.get(days.get(event.getDayOfWeek() - 1))){
            if(e != null){
                assertEquals(event.getTitle(), e.getTitle());
                assertEquals(event.getCategory(), e.getCategory());
                assertEquals(event.getDate(), e.getDate());
                assertEquals(event.getTimeStart(), e.getTimeStart());
                assertEquals(event.getTimeEnd(), e.getTimeEnd());
                assertEquals(event.getDescription(), e.getDescription());
            }
        }
    }


    private void selectChoiceBox(String id, int target, int current){
        clickOn(id);
        int diff = target - current;
        if(diff > 0){
            type(KeyCode.DOWN, diff);
        }
        else{
            type(KeyCode.UP, -1*diff);
        }
        type(KeyCode.ENTER);
    }
    
    
}
