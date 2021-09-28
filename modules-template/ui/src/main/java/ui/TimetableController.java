package ui;

<<<<<<< HEAD
import java.net.URL;
import java.util.ResourceBundle;

import core.Event;
import core.Timetable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TimetableController  {

    ObservableList<String> weekDayList = FXCollections
        .observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    ObservableList<String> timeList = FXCollections
        .observableArrayList("08:00-09:00", "09:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00", "13:00-14:00"
        , "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00");
    @FXML
    private TextField title;

    @FXML 
    private TextField description;

    @FXML
    private ComboBox weekDay;

    @FXML
    private ComboBox timeOfDay;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        
        
        
        weekDay.setValue("Monday");
        weekDay.setItems(weekDayList);

        timeOfDay.setValue("08:00-09:00");
        timeOfDay.setItems(timeList);
=======
import core.Timetable;

public class TimetableController {
    
    public TimetableController() {
        Timetable timetable = new Timetable(null);
>>>>>>> core
    }

    @FXML
    public void handleAdd() {
        // Fetch name, day, time and text from fxml file, and make Event object
        time = splittimeOfDay.getSelectionModel().getSelectedItem();
        String[] timeArray = time.split("-")
        
        Event event = new Event(title.getText(), description.getText(), timeArray[0], timeArray[1], weekDay.getSelectionModel().getSelectedItem());
        
        if (isAvailable(event) {
            timetable.addEvent(event);
        }
        else {
            // Occupied space, notify user
        }

        timetable.writeEvent();
        updateTimetableView();
    }

    public void updateTimetableView() {
        
    }

    public boolean isAvailable(Event event) {
        for (e : timetable.getEventList()) {
            if ((event.getDayOfWeek() == e.getDayOfWeek()) and (event.getTimeStart() == e.getTimeStart()) {
                return false;
            }
        }
        return true;
    }


    // Method for later release
    @FXML
    public void handleRemove() {
    }
}
