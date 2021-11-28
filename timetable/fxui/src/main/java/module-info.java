module timetableModule.ui {
  requires timetableModule.core;
  requires java.net.http;
  requires transitive com.fasterxml.jackson.databind;
  requires transitive com.fasterxml.jackson.core;
  requires javafx.controls;
  requires javafx.fxml;

  opens timetable.ui to
      javafx.graphics,
      javafx.fxml;
}
