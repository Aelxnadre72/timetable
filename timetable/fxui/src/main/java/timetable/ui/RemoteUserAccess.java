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
        return baseUri.resolve("?").resolve(nameEncode(name));
        
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
        HttpRequest request = HttpRequest.newBuilder(timetableUri(key))
        .header("Accept", "application/json")
        .GET()
        .build();
        try{
            HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseStr = response.body();
            Timetable timetable = mapper.readValue(responseStr, Timetable.class);
            this.user.addTimetable(timetable);
            return timetable;
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }
        
    }

    @Override
    public void putTimetable(Timetable timetable) {
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
