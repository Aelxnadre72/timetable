module timetable.ui {
    exports timetable.ui;
    requires timetable.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens timetable.ui to javafx.graphics, javafx.fxml;
}
