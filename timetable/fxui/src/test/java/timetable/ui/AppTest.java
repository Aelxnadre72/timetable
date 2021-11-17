package timetable.ui;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import timetable.core.Event;

public class AppTest extends ApplicationTest{

    private AppController controller;
    private Map<ListView<String>, List<Event>> eventMap;
    private List<ListView<String>> days;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("AppTest.fxml"));
        Parent parent = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
        this.eventMap = controller.getEventMap();
        this.days = controller.getDays();
        controller.clearUserForTest();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @BeforeEach
    public void setup(){
        System.out.println("hei");
    }
    
    @Test
    public void ok(){
        System.out.println(days);
    }
}
