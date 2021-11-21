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
import timetable.json.TimetablePersistance;

public class RemoteUserAccess implements UserAccess{
    
    private User user;
    private ObjectMapper mapper;
    private final URI baseUri;
    public RemoteUserAccess(URI uri) {
        this.baseUri = uri;
        mapper = TimetablePersistance.createMapper();
    }

    @Override
    public boolean hasTimetable(int week, int year) {
        return getRemoteUser().hasTimetable(String.valueOf(week) + String.valueOf(year));

    }

    private String nameEncode(String name) {
        return URLEncoder.encode(name, StandardCharsets.UTF_8);
    }

    private URI timetableUri(String name) {
        return baseUri.resolve("?").resolve(nameEncode(name));
        
    }
    // private method to fetch remote user
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


    @Override
    public Timetable getTimetable(int week, int year) {
        String key = (String.valueOf(week) + String.valueOf(year));
        Timetable localTimetable = this.user.getTimetable(key);
        if (localTimetable == null) {
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
            }
            catch (IOException | InterruptedException e) {
                throw new RuntimeException();
            }
        }
        return localTimetable;
    }

    @Override
    public void addTimetable(Timetable timetable) {
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
            this.user.addTimetable(timetable);
        }
        
      
       }  
       catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
      } 
    }
    
    
}
