module timetable.ui {
<<<<<<< HEAD:timetable/ui/src/main/java/module-info.java
    exports timetable.ui;
    requires timetable.core;
=======
    requires calc.core;
>>>>>>> lastTouches:modules-template/ui/src/main/java/module-info.java
    requires javafx.controls;
    requires javafx.fxml;

    opens timetable.ui to javafx.graphics, javafx.fxml;
}
