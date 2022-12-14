package timetable.ui;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import timetable.core.Event;
import timetable.core.Timetable;

/** controller for the timetable application. */
public class AppController {

  @FXML private ListView<String> hours;

  @FXML private ListView<String> monday;

  @FXML private ListView<String> tuesday;

  @FXML private ListView<String> wednesday;

  @FXML private ListView<String> thursday;

  @FXML private ListView<String> friday;

  @FXML private ListView<String> saturday;

  @FXML private ListView<String> sunday;

  @FXML private TextField newTitle;

  @FXML private ChoiceBox<String> newCategory;

  @FXML private DatePicker newDate;

  @FXML private ChoiceBox<String> newStartTime;

  @FXML private ChoiceBox<String> newEndTime;

  @FXML private TextField newDescription;

  @FXML private Text eventInfo;

  @FXML private Text title;

  @FXML private Text category;

  @FXML private Text date;

  @FXML private Text time;

  @FXML private Text description;

  @FXML private Button deleteButton;

  @FXML private Button addEventButton;

  @FXML private Button prevWeekButton;

  @FXML private Button nextWeekButton;

  @FXML private Button goButton;

  @FXML private Text weekNumber;

  @FXML private Button extraWeek;

  @FXML private ChoiceBox<String> year;

  @FXML private Text addEventWarning;

  @FXML String endpointUri;

  private ListView<String> selectedDay;

  // list containing all of the listview-days
  private List<ListView<String>> days;

  // overview over events to show the selected event information
  private Map<ListView<String>, List<Event>> eventMap = new HashMap<>();

  UserAccess userAccess;

  /** Sets userAccess as remote if endpointUri exists, locally if else. */
  @FXML
  void initialize() {
    if (endpointUri != null) {
      RemoteUserAccess remoteAccess = null;
      try {
        System.out.println("Using remote endpoint @ " + endpointUri);
        remoteAccess = new RemoteUserAccess(new URI(endpointUri));
        remoteAccess.getInitialUser();
        userAccess = remoteAccess;

      } catch (URISyntaxException e) {
        System.err.println(e);
      }
    } else {
      userAccess = new LocalUserAccess();
    }

    // converts the format of the datepicker
    convertDatePicker();
    days = Arrays.asList(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
    // adds listeners to the days-listviews selectionmodels
    initializeListViewListeners();
    // reads all the events and sets user
    // initializeEvents();
    // initalizes start time, end time and category choiceboxes
    initializeChoiceboxes();
    // initializes years in choicebox
    initializeYear();
    // initializes week number text
    initializeWeekNumber();
    // initializes hours for listview "hours"
    initializeHoursListView();
    // resets all the different day-listviews and loads the events for the week

    updateTimetableView();
  }

  // sets the chosen week and updates the timetableview to show events for that week
  @FXML
  void handleWeeks(ActionEvent event) {
    if (event.getSource() instanceof Labeled w) {
      weekNumber.setText(w.getText());
      updateTimetableView();
      clearSelectedEventInfo();
    }
  }

  // shows the previous week
  @FXML
  void handlePrevWeek(ActionEvent event) {
    // if first week of the year, go to the last week of the year (52 or 53)
    if (weekNumber.getText().equals("1")) {
      if (extraWeek.isVisible()) {
        weekNumber.setText("53");
      } else {
        weekNumber.setText("52");
      }
    } else { // else go to previous week
      weekNumber.setText(String.valueOf(Integer.parseInt(weekNumber.getText()) - 1));
    }

    updateTimetableView();

    clearSelectedEventInfo();
  }

  // shows the next week
  @FXML
  void handleNextWeek(ActionEvent event) {
    // if last week of the year, go to first week of the year
    if (extraWeek.isVisible() && weekNumber.getText().equals("53")
        || !extraWeek.isVisible() && weekNumber.getText().equals("52")) {
      weekNumber.setText("1");
    } else { // else go to next week
      weekNumber.setText(String.valueOf(Integer.parseInt(weekNumber.getText()) + 1));
    }

    updateTimetableView();

    clearSelectedEventInfo();
  }

  // updates the tableview to show the same week as before, but the new chosen year. Shows 52 or 53
  // weeks depending on the year
  @FXML
  void handleYear(ActionEvent event) {
    initializeNumberOfWeeks(year.getSelectionModel().getSelectedItem());
    updateTimetableView();

    clearSelectedEventInfo();
  }

  // adding a new event. Checks if time is valid and the new event does not overlap an existing
  // event. Creates a new timetable for the week if does not already exist.
  @FXML
  void handleAddEvent(ActionEvent event) {
    // hides the warning
    addEventWarning.setVisible(false);
    try {
      // if end time is before or equal to start time, excluding when both start and end is 00:00,
      // then set appropriate error message to addEventWarning
      if (Integer.parseInt(newEndTime.getValue().substring(0, 2))
              <= Integer.parseInt(newStartTime.getValue().substring(0, 2))
          && !newEndTime.getValue().equals("00:00")) {
        addEventWarning.setText("The chosen timeframe of the event is invalid!");
        throw new IllegalArgumentException("The chosen timeframe of the event is invalid!");
      } else if (newTitle.getText().equals("") || newDescription.getText().equals("")) {
        // if title or description fields are empty{
        addEventWarning.setText("Fields can not be empty!");
        throw new IllegalArgumentException("One or more of the fields are empty!");
      } else { // if timetable.addEvent throws exception because of overlapping events
        addEventWarning.setText("The event can not overlap an existing event!");
      }

      Event ev =
          new Event(
              newTitle.getText(),
              newCategory.getValue(),
              newDescription.getText(),
              newStartTime.getValue(),
              newEndTime.getValue(),
              newDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

      Timetable timetable =
          userAccess.getTimetable(String.valueOf(ev.getWeek()) + String.valueOf(ev.getYear()));
      // if the timetable for the week of the new event does not exist, then creates it and adds the
      // timetable to user
      if (timetable == null) {
        timetable = new Timetable(ev.getWeek(), ev.getYear());
        timetable.addEvent(ev);
        userAccess.addTimetable(timetable);
        if (ev.getWeek() == 53 && Integer.parseInt(ev.getDate().substring(0, 2)) <= 7) {
          Timetable fiftythree =
              userAccess.getTimetable(
                  String.valueOf(ev.getWeek()) + String.valueOf(ev.getYear() - 1));
          if (fiftythree == null) {
            fiftythree = new Timetable(ev.getWeek(), ev.getYear() - 1);
            userAccess.addTimetable(fiftythree);
          }
        }
      } else {
        timetable.addEvent(ev);
        userAccess.addTimetable(timetable);
      }
    } catch (IllegalArgumentException e) {
      // shows the warning
      addEventWarning.setVisible(true);
      e.printStackTrace();
    }
    // resets the choiceboxes and datepicker
    initializeChoiceboxes();
    newTitle.clear();
    newCategory.getSelectionModel().selectFirst();
    newDescription.clear();
    // updates the listviews for all the days

    updateTimetableView();
  }

  // Disables selecting a hours-cell (example 08:00-09:00) in the timetable
  @FXML
  void handleClickedHours(MouseEvent event) {
    hours.getSelectionModel().clearSelection();
  }

  // clears the event info that is shown when selecting a valid event
  private void clearSelectedEventInfo() {
    eventInfo.setText("Click on an event to get more information");
    title.setText("");
    category.setText("");
    date.setText("");
    time.setText("");
    description.setText("");
    deleteButton.setVisible(false);
  }

  // deletes the selected event, updates the timetable and removes the event information and hides
  // the delete button
  @FXML
  void handleDeleteEvent(ActionEvent event) {
    Event selectedEvent =
        eventMap.get(selectedDay).get(selectedDay.getSelectionModel().getSelectedIndex());
    Timetable timetable =
        userAccess.getTimetable(
            String.valueOf(selectedEvent.getWeek()) + String.valueOf(selectedEvent.getYear()));
    userAccess.removeEvent(timetable, selectedEvent);

    // updates the listView-days and the eventMap that keep track of the events to show info of
    // selected event
    updateTimetableView();

    // clears the information to the selected event that got deleted
    clearSelectedEventInfo();
  }

  // adds a listener to the listview days that listens to the selectionmodel
  // also connects all the different listview days so only 1 cell is selected between all of the
  // listviews. Shows the event info of the selected cell.
  private void initializeListViewListeners() {
    ChangeListener<? super String> cl =
        (obs, oldSelection, newSelection) -> {
          for (ListView<String> day : days) {
            if (day.getSelectionModel().getSelectedItem() != null) {
              if (day != selectedDay) {
                selectedDay.getSelectionModel().clearSelection();
                selectedDay = day;
                continue;
              } else {
                continue;
              }
            }
            day.getSelectionModel().clearSelection();
          }

          // display the event info of the selected event-cell
          if (selectedDay.getSelectionModel().getSelectedItem() != null) {
            if (!selectedDay.getSelectionModel().getSelectedItem().equals("")) {
              eventInfo.setText("Event information:");
              // get the selected event from eventMap and display the information under Event
              // information in ui
              Event selectedEvent =
                  eventMap.get(selectedDay).get(selectedDay.getSelectionModel().getSelectedIndex());
              title.setText(selectedEvent.getTitle());
              category.setText(selectedEvent.getCategory());
              date.setText(selectedEvent.getDate());
              time.setText(selectedEvent.getTimeStart() + "-" + selectedEvent.getTimeEnd());
              description.setText(selectedEvent.getDescription());
              deleteButton.setVisible(true);
              return;
            }
          }
          clearSelectedEventInfo();
        };

    // adds the listener to every listview day
    for (ListView<String> day : days) {
      day.getSelectionModel().selectedItemProperty().addListener(cl);
    }
  }

  // converts the format of the datepicker
  private void convertDatePicker() {
    String pattern = "dd.MM.yyyy";
    StringConverter<LocalDate> converter =
        new StringConverter<LocalDate>() {
          DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

          @Override
          public String toString(LocalDate date) {
            if (date != null) {
              return dateFormatter.format(date);
            } else {
              return "";
            }
          }

          @Override
          public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
              return LocalDate.parse(string, dateFormatter);
            } else {
              return null;
            }
          }
        };
    newDate.setConverter(converter);
  }

  // adds all the hours into the hours-listview
  private void initializeHoursListView() {
    if (hours.getItems().isEmpty()) {
      for (int i = 0; i < 23; i++) {
        if (i > 9) {
          hours.getItems().add(Integer.toString(i) + ":00-" + Integer.toString(i + 1) + ":00");
        } else {
          if (i < 9) {
            hours
                .getItems()
                .add("0" + Integer.toString(i) + ":00-0" + Integer.toString(i + 1) + ":00");
          } else {
            hours
                .getItems()
                .add("0" + Integer.toString(i) + ":00-" + Integer.toString(i + 1) + ":00");
          }
        }
      }
      hours.getItems().add("23:00-00:00");
    }
  }

  // clears all the different day-listviews and eventMap(-keeps track of events to display event
  // info when selecting an event)
  private void resetDaysListView() {
    eventMap.clear();
    for (ListView<String> day : days) {
      day.getItems().clear();
      day.getItems()
          .addAll(
              "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
              "", "", "");
      eventMap.put(
          day,
          Arrays.asList(
              null, null, null, null, null, null, null, null, null, null, null, null, null, null,
              null, null, null, null, null, null, null, null, null, null));
    }
    selectedDay = monday;
  }

  // resets and updates the listview-days and eventMap
  private void updateTimetableView() {
    resetDaysListView();

    // get timetable with key weeknumber+year
    Timetable chosenWeek =
        userAccess.getTimetable(weekNumber.getText() + year.getSelectionModel().getSelectedItem());

    if (chosenWeek != null) {
      // get a copy of a list with all the events in the chosen week
      List<Event> chosenWeekEventList = chosenWeek.getEventList();

      if (chosenWeek.getWeek() == 53) {
        // get the timetable for week 53 for the next year because if a year has 53 weeks than
        // roughly half of
        // that week will technically be in the next year. Example: week 53 in year 2020 does have
        // dates that
        // is in 2021.
        Timetable weekNextYear =
            userAccess.getTimetable(
                String.valueOf(chosenWeek.getWeek()) + String.valueOf(chosenWeek.getYear() + 1));
        if (weekNextYear != null) {
          // solves problem by showing the events for week 53 for both the year with 53 weeks and
          // the next year
          chosenWeekEventList.addAll(weekNextYear.getEventList());
        }
      }

      for (Event event : chosenWeekEventList) {
        int eventTimeStart = Integer.parseInt(event.getTimeStart().substring(0, 2));
        int eventTimeEnd = Integer.parseInt(event.getTimeEnd().substring(0, 2));
        if (eventTimeEnd == 0) {
          eventTimeEnd = 24;
        }

        // the day of of the current event from chosenWeekEventList
        for (int i = eventTimeStart; i < eventTimeEnd; i++) {
          days.get(event.getDayOfWeek() - 1)
              .getItems()
              .set(i, event.getCategory() + ": " + event.getTitle());
          eventMap.get(days.get(event.getDayOfWeek() - 1)).set(i, event);
        }
      }
    }
  }

  // add the years 2020 to 2030 to the year-choicebox
  private void initializeYear() {
    for (int i = 2020; i < 2031; i++) {
      year.getItems().add(Integer.toString(i));
    }
    // sets the default value of the year-choicebox to the current year
    Calendar cal = Calendar.getInstance();
    cal.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
    String currentYear = Integer.toString(cal.get(Calendar.YEAR));
    year.setValue(currentYear);
    // shows the correct number of weeks of the current year (52 or 53)
    initializeNumberOfWeeks(currentYear);
  }

  // shows 52 or 53 week-buttons in the scrollbar at the bottom depending on if the chosen year has
  // 52 or 53 weeks
  private void initializeNumberOfWeeks(String year) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
    cal.set(Integer.parseInt(year), 11, 31);
    if (cal.get(Calendar.WEEK_OF_YEAR) == 52) {
      extraWeek.setVisible(false);
      if (weekNumber.getText().equals("53")) {
        weekNumber.setText("52");
      }
    } else {
      extraWeek.setVisible(true);
    }
  }

  // sets the current week as the chosen week. The week number is displayed under the TIMETABLE
  // header
  private void initializeWeekNumber() {
    LocalDate ld = LocalDate.now();
    TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
    weekNumber.setText(String.valueOf(ld.get(woy)));
  }

  // adds the hours to startTime and endTime combobox and adds the categories to the newCategory
  // choicebox
  private void initializeChoiceboxes() {

    // set default locale for datepicker and default value to todays date
    Locale.setDefault(new Locale("no", "NO"));
    newDate.setValue(LocalDate.now(ZoneId.of("Europe/Oslo")));

    // sets the categories to choose from in the choicebox
    newCategory
        .getItems()
        .setAll("social", "work", "exercise", "eat", "chore", "appointment", "school", "other");
    newCategory.setValue("social");

    if (newStartTime.valueProperty().getValue() == null) {
      for (int i = 0; i < 23; i++) {
        if (i > 9) {
          newStartTime.getItems().add(Integer.toString(i) + ":00");
          newEndTime.getItems().add(Integer.toString(i + 1) + ":00");
        } else {
          if (i < 9) {
            newStartTime.getItems().add("0" + Integer.toString(i) + ":00");
            newEndTime.getItems().add("0" + Integer.toString(i + 1) + ":00");
          } else {
            newStartTime.getItems().add("0" + Integer.toString(i) + ":00");
            newEndTime.getItems().add(Integer.toString(i + 1) + ":00");
          }
        }
      }
      newStartTime.getItems().add("23:00");
      newEndTime.getItems().add("00:00");
    }

    // set the timezone, current time and current time + 1 hour as the default value for the
    // time-choiceboxes
    Calendar cal = Calendar.getInstance();
    cal.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
    int h = cal.get(Calendar.HOUR_OF_DAY);

    if (h > 9) {
      if (h == 23) {
        newStartTime.setValue("23:00");
        newEndTime.setValue("00:00");
      } else {
        newStartTime.setValue(Integer.toString(h) + ":00");
        newEndTime.setValue(Integer.toString(h + 1) + ":00");
      }
    } else {
      if (h < 9) {
        newStartTime.setValue("0" + Integer.toString(h) + ":00");
        newEndTime.setValue("0" + Integer.toString(h + 1) + ":00");
      } else {
        newStartTime.setValue("0" + Integer.toString(h) + ":00");
        newEndTime.setValue(Integer.toString(h + 1) + ":00");
      }
    }
  }

  // Method for testing UI. Get map of events.
  Map<ListView<String>, List<Event>> getEventMap() {
    return eventMap;
  }

  // Method for testing UI. Get listviews of days (also used as keys in eventMap).
  List<ListView<String>> getDays() {
    return days;
  }

  // Method for testing UI. Sets a user without any events and updates the view to clear any old
  // events
  void clearUserForTest() {
    updateTimetableView();
  }
}
