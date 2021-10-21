package timetable.ui;

import java.util.Arrays;
import java.util.List;

import timetable.core.Event;
import timetable.core.Timetable;
import timetable.core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.text.Text;

public class AppController {

    @FXML
    private ListView<String> hours;

    @FXML
    private ListView<String> monday;

    @FXML
    private ListView<String> tuesday;

    @FXML
    private ListView<String> wednesday;

    @FXML
    private ListView<String> thursday;

    @FXML
    private ListView<String> friday;

    @FXML
    private ListView<String> saturday;

    @FXML
    private ListView<String> sunday;

    @FXML
    private TextField newTitle;

    @FXML
    private DatePicker newDate;

    @FXML
    private ChoiceBox<String> newStartTime;

    @FXML
    private ChoiceBox<String> newEndTime;

    @FXML
    private TextField newDescription;

    @FXML
    private Text title;

    @FXML
    private Text date;

    @FXML
    private Text time;

    @FXML
    private Text description;

    @FXML
    private Text weekNumber;

    @FXML
    private Button extraWeek;

    @FXML
    private ChoiceBox<String> year;

    private User user;

    private Timetable timetable;

    private ListView<String> selectedDay;

    @FXML
    void initialize() {
//        timetable = new Timetable();
        initializeTimes();
        initializeHoursListView();
        resetDaysListView();        
        monday.getItems().add(0, "trene");
        tuesday.getItems().add(0, "spise");
        //initializeSavedEvents();
    }

    @FXML
    void handleWeeks(ActionEvent event) {
         if (event.getSource() instanceof Labeled w) {
            title.setText(w.getText());
         }
    }

    @FXML
    void handleAddEvent(ActionEvent event) {
/*         try{
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
        day.setValue("Monday"); */
    }

    @FXML
    void handleClickedHours(MouseEvent event) {
        hours.getSelectionModel().clearSelection();
    }

    @FXML
    void handleClickedEvent(MouseEvent event) {
        int index = selectedDay.getSelectionModel().getSelectedIndex();
        Boolean changed = false;
        for(ListView<String> day : Arrays.asList(monday, tuesday, wednesday, thursday, friday, saturday, sunday)){
            if(!(day.getSelectionModel().getSelectedItem() == null)){
                if(selectedDay != day && changed == false){
                    selectedDay = day;
                    changed = true;
                    continue;
                }
            }
            day.getSelectionModel().clearSelection();
        }
        if(changed == false){
            selectedDay.getSelectionModel().select(index);
        }
        if(selectedDay.getSelectionModel().getSelectedItem() != ""){
            title.setText(selectedDay.getSelectionModel().getSelectedItem());
        }
        else{
            //burde ha en display event metode som tar inn en timetable basert på hva som står i selecteditem. Hvis tom så sender inne null og setter alt til None.
            title.setText("None");
        }
    }

    private void initializeHoursListView(){
         if(hours.getItems().isEmpty()){
            for(int i = 0; i<22; i++){
                if(i>9){
                    hours.getItems().add(Integer.toString(i) + ":00-" + Integer.toString(i+1) + ":00");
                }
                else{
                    if(i<9){
                        hours.getItems().add("0" + Integer.toString(i) + ":00-0" + Integer.toString(i+1) + ":00");
                    }
                    else{
                        hours.getItems().add("0" + Integer.toString(i) + ":00-" + Integer.toString(i+1) + ":00");
                    }
                }
            }
            hours.getItems().add("23:00-00:00");
        }
    }

    private void resetDaysListView(){
        for(ListView<String> day : Arrays.asList(monday, tuesday, wednesday, thursday, friday, saturday, sunday)){
            day.getItems().clear();
            day.getItems().addAll("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        }
        selectedDay = monday;
    }

    private void initializeSavedEvents(){
/*         for(Event event : timetable.getEventList()){
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
        } */
    }

    //må endre slik at dersom feks 15 er valgt som start så vises kun 16 og senere på slutt. Bruk en onclick på start og slutt. og i lik verdien.
    private void initializeTimes(){
        for(int i = 0; i<24; i++){
            if(i>9){
                newStartTime.getItems().add(Integer.toString(i) + ":00");
                newEndTime.getItems().add(Integer.toString(i+1) + ":00");
            }
            else{
                newStartTime.getItems().add("0" + Integer.toString(i) + ":00");
                newEndTime.getItems().add("0" + Integer.toString(i+1) + ":00");
            }
        }
        newStartTime.setValue("00:00");
        newEndTime.setValue("01:00");
    }

}
