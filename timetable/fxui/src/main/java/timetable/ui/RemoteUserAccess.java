package timetable.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import timetable.core.Timetable;
import timetable.core.User;
import timetable.json.TimetablePersistence;

public class RemoteUserAccess implements UserAccess{
    
    private User user;
    private ObjectMapper mapper;
    private final URI baseUri;
    public RemoteUserAccess(URI uri) {
        this.baseUri = uri;
        mapper = TimetablePersistence.createMapper();
    }

    @Override
    public boolean hasTimetable(String weekYear) {
        return getRemoteUser().hasTimetable(weekYear);

    }

    private String nameEncode(String name) {
        return URLEncoder.encode(name, StandardCharsets.UTF_8);
    }

    private URI timetableUri(String name) {
        return baseUri.resolve("timetable").resolve(nameEncode(name));
        
    }
    /**
    * Private method to get remote user 
    *
    * @return user
    */
    
    private User getRemoteUser() {
        if (this.user == null) { //load remote user
            HttpRequest request = HttpRequest.newBuilder(baseUri)
            .header("Accept", "application/json")
            .GET()
            .build();
            try {
                final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
                this.user = mapper.readValue(response.body(), User.class);
              } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
              }
        
        }
        return user;
    }

    public User getInitialUser() {
        return getRemoteUser();
        
    }


    /**
    * 
    *
    * @return 
    */

    @Override
    public Timetable getTimetable(String weekYear) {
        String key = (weekYear);
        System.out.println("gets " + weekYear); // for å sjekke programflyt
        HttpRequest request = HttpRequest.newBuilder(timetableUri(key))
        .header("Accept", "application/json")
        .GET()
        .build();
        System.out.println(request); // printer ut requesten i terminalen
        try{
            HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseStr = response.body();
            System.out.println(responseStr);
            if (responseStr.contains("Not Found") == false) {
              Timetable timetable = mapper.readValue(responseStr, Timetable.class);
              this.user.addTimetable(timetable);
              return timetable;
            }
            return null;
            
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }
        
    }

    @Override
    public void putTimetable(Timetable timetable) {
      System.out.println("puts " + String.valueOf(timetable.getWeek())); // sjekke programflyt
        String weekYear = String.valueOf(timetable.getWeek()) + String.valueOf(timetable.getYear());
        if (hasTimetable(weekYear)) {   
            removeTimetable(weekYear);
        }
        try { 
            String jsonStr = mapper.writeValueAsString(timetable);
            HttpRequest request = HttpRequest.newBuilder(timetableUri(String.valueOf(timetable.getWeek()) + String.valueOf(timetable.getYear())))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(BodyPublishers.ofString(jsonStr))
              .build();
            final HttpResponse<String> response =
                HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseStr = response.body();
            System.out.println(responseStr); // printe ut responsen, "Not Found" i message-feltet
            Boolean added = mapper.readValue(responseStr, Boolean.class);
            if (added != null) {
                this.user.putTimetable(timetable);
            }
        }  
      
       catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
      } 
    }

    @Override
  public void removeTimetable(String weekYear) {
    System.out.println("removes " + weekYear); // programflyt
    try {
      HttpRequest request = HttpRequest.newBuilder(timetableUri(weekYear))
          .header("Accept", "application/json")
          .DELETE()
          .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseStr = response.body();
      Boolean removed = mapper.readValue(responseStr, Boolean.class);
      if (removed != null) {
        user.removeTimetable(weekYear);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void notifyTimetableChanged(Timetable timetable) {
    putTimetable(timetable);
  }

    
    
}
