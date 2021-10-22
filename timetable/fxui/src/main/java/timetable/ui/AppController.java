package timetable.ui;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import timetable.core.Event;
import timetable.core.Json;
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
    private Text eventInfo;

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

    @FXML
    private Text addEventWarning;

    private User user;

    private ListView<String> selectedDay;

    private Json RW;

    @FXML
    void initialize() {
        // reads all the events and sets user
        initializeEvents();
        // initalizes start time and end time in choiceboxes
        initializeTimes();
        // initializes years in choicebox
        initializeYear();
        // initializes week number text
        initializeWeekNumber();
        // initializes hours for listview "hours"
        initializeHoursListView();
        // resets all the different day-listviews and loads the events for the week
        updateTimetableView();
    }

    @FXML
    void handleWeeks(ActionEvent event) {
        //should show the current year and current week timetable when launching app
        //week 53 is not implemented, needs to check if week 53 exists for that year and set visbility
         if (event.getSource() instanceof Labeled w) {
            weekNumber.setText(w.getText());
            updateTimetableView();
         }
    }

    @FXML
    void handleYear(ActionEvent event) {
        updateTimetableView();
    }

    //må ha en varselmelding dersom dato er satt til år over/under grensen, år 2010-2030
    @FXML
    void handleAddEvent(ActionEvent event) {
        addEventWarning.setVisible(false);
        try{
            Event ev = new Event(newTitle.getText(), newDescription.getText(), newStartTime.getValue(), newEndTime.getValue(), newDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            Timetable timetable = user.getTimetable(String.valueOf(ev.getWeek()) + String.valueOf(ev.getYear()));
            if(timetable == null){
                timetable = new Timetable(Integer.parseInt(String.valueOf(ev.getWeek())), Integer.parseInt(String.valueOf(ev.getYear())));
                user.addTimetable(timetable);
            }
            timetable.addEvent(ev);
        }catch(Exception e){
            //må legge til warning og constraint dersom event med samme navn på samme dag eller ugyldig klokkeslett eller dato
            // burde ha tilbakemelding på at event er lagt til
            addEventWarning.setVisible(true);
            e.printStackTrace();
        }

        initializeTimes();
        newTitle.clear();
        newDescription.clear();
        newDate.getEditor().clear();
        updateTimetableView();

/*         RW.write(user); */
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
        if(!(selectedDay.getSelectionModel().getSelectedItem().equals(""))){
            eventInfo.setText("Event information:");
            // set right event info later
            // user.getTimetable(weekNumber.getText() + year.getSelectionModel().getSelectedItem());
            title.setText(selectedDay.getSelectionModel().getSelectedItem());
        }
        else{
            eventInfo.setText("Click on an event to get more information.");
            title.setText("");
            date.setText("");
            time.setText("");
            description.setText("");
        }
    }

    private void initializeEvents(){
        user = new User("mainUser");
        RW = new Json();
        RW.read(user);
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
            day.getItems().addAll("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        }
        selectedDay = monday;
    }

    private void updateTimetableView(){
        resetDaysListView();
        //get timetable with key weeknumber+year
        Timetable chosenWeek = user.getTimetable(weekNumber.getText() + year.getSelectionModel().getSelectedItem());
        if(chosenWeek != null){
            for(Event event : chosenWeek.getEventList()){
                int eventTimeStart = Integer.parseInt(event.getTimeStart().substring(0, 2));
                int eventTimeEnd = Integer.parseInt(event.getTimeEnd().substring(0, 2));
                if(eventTimeEnd == 0){
                    eventTimeEnd = 24;
                }
                switch(event.getDayOfWeek()) {
                    case 1: // causes overflow if the description is too long. Will improve ui later.
                        for(int i = eventTimeStart; i < eventTimeEnd; i++){
                            monday.getItems().set(i, event.getTitle());
                        }
                        break;
                    case 2:
                        for(int i = eventTimeStart; i < eventTimeEnd; i++){
                            tuesday.getItems().set(i, event.getTitle());
                        }
                        break;
                    case 3:
                        for(int i = eventTimeStart; i < eventTimeEnd; i++){
                            wednesday.getItems().set(i, event.getTitle());
                        }
                        break;
                    case 4:
                        for(int i = eventTimeStart; i < eventTimeEnd; i++){
                            thursday.getItems().set(i, event.getTitle());
                        }
                        break;
                    case 5:
                        for(int i = eventTimeStart; i < eventTimeEnd; i++){
                            friday.getItems().set(i, event.getTitle());
                        }
                        break;
                    case 6:
                        for(int i = eventTimeStart; i < eventTimeEnd; i++){
                            saturday.getItems().set(i, event.getTitle());
                        }
                        break;
                    case 7:
                        for(int i = eventTimeStart; i < eventTimeEnd; i++){
                            sunday.getItems().set(i, event.getTitle());
                        }
                        break;
                    default:
                        break;
                  }
            }
        }
    }

    private void initializeYear(){
        // when choosing a new year, should display the week you were on for that year. week 53 should appear/dissappear 
        // depending on if the year has 53 weeks. if week 53 is selected, then 52 should be diplayed if the new year doesn have 53.
        // or maybe just start on the current week of the current year, but with the newly chosen year instead
        for(int i = 2020; i<2031; i++){
            year.getItems().add(Integer.toString(i));
        }
        //should show the current year and current week timetable when launching app
        year.setValue(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
    }

    private void initializeWeekNumber(){
        weekNumber.setText(Integer.toString(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)));
    }

    //må endre slik at dersom feks 15 er valgt som start så vises kun 16 og senere på slutt. Bruk en onclick på start og slutt. og i lik verdien.
    private void initializeTimes(){
        for(int i = 0; i<23; i++){
            if(i>9){
                newStartTime.getItems().add(Integer.toString(i) + ":00");
                newEndTime.getItems().add(Integer.toString(i+1) + ":00");
            }
            else{
                if(i<9){
                    newStartTime.getItems().add("0" + Integer.toString(i) + ":00");
                    newEndTime.getItems().add("0" + Integer.toString(i+1) + ":00");
                }
                else{
                    newStartTime.getItems().add("0" + Integer.toString(i) + ":00");
                    newEndTime.getItems().add(Integer.toString(i+1) + ":00");
                }
            }
        }
        newStartTime.getItems().add("23:00");
        newEndTime.getItems().add("00:00");

        int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(h>9){
            if(h == 23){
                newStartTime.setValue("23:00");
                newEndTime.setValue("00:00" );
            }
            newStartTime.setValue(Integer.toString(h) + ":00" );
            newEndTime.setValue(Integer.toString(h+1) + ":00");
        }
        else{
            if(h<9){
                newStartTime.setValue("0" + Integer.toString(h) + ":00" );
                newEndTime.setValue("0" + Integer.toString(h+1) + ":00");
            }
            else{
                newStartTime.setValue("0" + Integer.toString(h) + ":00" );
                newEndTime.setValue(Integer.toString(h+1) + ":00");
            }
        }
    }

}
