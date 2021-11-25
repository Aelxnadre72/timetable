package timetable.ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import timetable.core.Event;
import timetable.core.Timetable;
import timetable.core.User;
import timetable.json.TimetablePersistence;

public class RemoteUserAccess implements UserAccess{
    
    private User user;
    private ObjectMapper mapper;
    private final URI baseUri;
    private HttpClient client;
    
    public RemoteUserAccess(URI uri) {
        this.baseUri = uri;
        mapper = TimetablePersistence.createMapper();
        client = HttpClient.newBuilder().build();
    }

    @Override
    public boolean hasTimetable(String weekYear) {
        return getRemoteUser().hasTimetable(weekYear);

    }

/*     private String nameEncode(String name) {
        return URLEncoder.encode(name, StandardCharsets.UTF_8);
    } */

    private URI timetableUri(String name) {
        URI a = URI.create(baseUri.toString() + "/timetable/" + name);
        return a;
        
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
                client.send(request, HttpResponse.BodyHandlers.ofString());
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
        System.out.println(timetableUri(key));
        HttpRequest request = HttpRequest.newBuilder(timetableUri(key))
        .header("Accept", "application/json")
        .GET()
        .build();
        System.out.println(request); // printer ut requesten i terminalen
        try{
          HttpResponse<String> response = 
          client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseStr = response.body();
            System.out.println(responseStr);
            if (responseStr.contains("Not Found") == false && responseStr.contains("Request failed") == false) {
              Timetable timetable = mapper.readValue(responseStr, Timetable.class);
              return timetable;
            }
            return null;
            
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public void addTimetable(Timetable timetable) {
      System.out.println("puts " + String.valueOf(timetable.getWeek())); // sjekke programflyt
        String weekYear = String.valueOf(timetable.getWeek()) + String.valueOf(timetable.getYear());
        if (hasTimetable(weekYear)) {   
            removeTimetable(weekYear);
        }
        try { 
            String jsonStr = mapper.writeValueAsString(timetable);
            HttpRequest request = HttpRequest.newBuilder(timetableUri(weekYear))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(BodyPublishers.ofString(jsonStr))
              .build();
            final HttpResponse<String> response =
            client.send(request, HttpResponse.BodyHandlers.ofString());
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

    @Override
  public void removeTimetable(String weekYear) {
    System.out.println("removes " + weekYear); // programflyt
    try {
      HttpRequest request = HttpRequest.newBuilder(timetableUri(weekYear))
          .header("Accept", "application/json")
          .DELETE()
          .build();
      final HttpResponse<String> response =
      client.send(request, HttpResponse.BodyHandlers.ofString());
      String responseStr = response.body();
      Boolean removed = mapper.readValue(responseStr, Boolean.class);
      if (removed != null) {
        user.removeTimetable(weekYear);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void removeEvent(Timetable timetable, Event event) {
    Timetable t = new Timetable(timetable.getWeek(), timetable.getYear());
    for(Event e : timetable.getEventList().stream()
    .filter(ev -> ev.getDayOfWeek() != event.getDayOfWeek() 
    || !ev.getTimeStart().equals(event.getTimeStart())).collect(Collectors.toList())){
      t.addEvent(e);
    }
    if (t.getEventList().size() == 0){
      removeTimetable(String.valueOf(timetable.getWeek()) + String.valueOf(timetable.getYear()));
    }
    else {
      addTimetable(t);
    }
  }

    
    
}
