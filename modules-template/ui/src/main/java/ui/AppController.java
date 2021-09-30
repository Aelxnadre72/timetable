package ui;

import core.Timetable;

import java.util.Arrays;

import core.Event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AppController {

    @FXML
    private ChoiceBox<String> day;

    @FXML
    private TextField description;

    @FXML
    private ListView<String> friday;

    @FXML
    private ListView<String> hours;

    @FXML
    private ListView<String> monday;

    @FXML
    private ListView<String> thursday;

    @FXML
    private ChoiceBox<String> time;

    @FXML
    private TextField title;

    @FXML
    private ListView<String> tuesday;

    @FXML
    private ListView<String> wednesday;

    private Timetable timetable;

    @FXML
    void initialize() {
        timetable = new Timetable();
        initializeDay();
        initializeTime();
        initializeListViewTable();
        initializeSavedEvents();
    }

    @FXML
    void handleAdd(ActionEvent event) {
        try{
            timetable.addEvent(new Event(title.getText().toLowerCase(), description.getText(), time.getValue().substring(0, 5), time.getValue().substring(6, 11), day.getValue().toLowerCase()));
        }catch(Exception e){
            // Make the notification of the error visible in the ui later.
            System.out.println("The time and date does already have an event.");
            e.printStackTrace();
        }
        initializeListViewTable();
        initializeSavedEvents();
        title.clear();
        description.clear();
        time.setValue("08:00-09:00");
        day.setValue("Monday");
    }

    private void initializeListViewTable(){
        if(hours.getItems().isEmpty()){
            hours.getItems().addAll(time.getItems());
        }
        for(ListView<String> lv : Arrays.asList(monday, tuesday, wednesday, thursday, friday)){
            lv.getItems().clear();
            lv.getItems().addAll("", "", "", "", "", "", "", "");
        }
    }

    private void initializeSavedEvents(){
        for(Event event : timetable.getEventList()){
            switch(event.getDay()) {
                case "monday": // causes overflow if the description is too long. Will improve ui later.
                    monday.getItems().set(Integer.parseInt(event.getTimeStart().substring(0, 2))-8, event.getTitle() + ": " + event.getDescription());
                    break;
                case "tuesday":
                    tuesday.getItems().set(Integer.parseInt(event.getTimeStart().substring(0, 2))-8, event.getTitle() + ": " + event.getDescription());
                    break;
                case "wednesday":
                    wednesday.getItems().set(Integer.parseInt(event.getTimeStart().substring(0, 2))-8, event.getTitle() + ": " + event.getDescription());
                    break;
                case "thursday":
                    thursday.getItems().set(Integer.parseInt(event.getTimeStart().substring(0, 2))-8, event.getTitle() + ": " + event.getDescription());
                    break;
                case "friday":
                    friday.getItems().set(Integer.parseInt(event.getTimeStart().substring(0, 2))-8, event.getTitle() + ": " + event.getDescription());   
                    break;
                default:
                    break;
              }
        }
    }

    private void initializeDay(){
        day.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        day.setValue("Monday");
    }

    private void initializeTime(){
        for(int i = 8; i<16; i++){
            if(i>9){
                time.getItems().add(Integer.toString(i) + ":00-" + Integer.toString(i+1) + ":00");
            }
            else{
                if(i == 8){
                    time.getItems().add("0" + Integer.toString(i) + ":00-0" + Integer.toString(i+1) + ":00");
                }
                else{
                    time.getItems().add("0" + Integer.toString(i) + ":00-" + Integer.toString(i+1) + ":00");
                }
            }
        }
        time.setValue("08:00-09:00");
    }

}
