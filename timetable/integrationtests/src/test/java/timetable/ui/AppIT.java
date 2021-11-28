package timetable.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

// import timetable.ui.RemoteUserAcces;

public class AppIT extends ApplicationTest {

  @BeforeAll
  public static void setupHeadless() {
    App.supportHeadless();
  }

  private AppController controller;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("App_it.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  public void setupTimetable() throws URISyntaxException {

    try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("it-user.json"))) {
      String port = System.getProperty("user.port");
      assertNotNull(port, "No user.port system property set");
      URI baseUri = new URI("http://localhost:" + port + "/user/");
      System.out.println("Base RemoteUserAcces URI: " + baseUri);
      // this.controller.setUserAccess(new RemoteUserAccess(baseUri));
      this.controller.userAccess = new RemoteUserAccess(baseUri);
    } catch (IOException ioe) {
      fail(ioe.getMessage());
    }
  }

  @Test
  public void testController() {
    assertNotNull(this.controller);
  }
}
