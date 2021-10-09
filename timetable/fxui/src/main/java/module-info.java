module timetableModule.ui {
    requires timetableModule.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens timetable.ui to javafx.graphics, javafx.fxml;
}
