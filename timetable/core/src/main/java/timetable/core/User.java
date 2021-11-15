package timetable.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    
    private String id;

    private Map<String, Timetable> timetableMap = new HashMap<>();

    public User(String id){
        this.id = id;
    }

    // add timetable-object to timetableList. Key is week + year.
    public void addTimetable(Timetable timetable){
        String k = String.valueOf(timetable.getWeek()) + String.valueOf(timetable.getYear());
        if(timetableMap.containsKey(k)){
            throw new IllegalArgumentException("The timetable with this specific key already exist.");
        }
        timetableMap.put(k, timetable);
    }

    // get timetable-object with key k
    public Timetable getTimetable(String k){
        if(!timetableMap.containsKey(k)){
            return null;
        }
        return timetableMap.get(k);
    }

    public List<Timetable> getTimetableList(){
        List<Timetable> l = new ArrayList<>(timetableMap.values());
        return l;
    }
    
}
