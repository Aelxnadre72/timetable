package timetable.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    // json burde ha filstruktur "ukenummerOgÅr: [event1, event2, event3,..]" feks "38: [event1, event2,..]" og en for
    // gjentagende eventer "gjentagende: [event1, event2,..]" på engelsk. Fordi hvis det er veldig mange eventer så blir det
    // veldig mye å sortere til ulike timetables. Timetable burde også ha år. For gjentakende eventer så burde det være et timetable-objekt med uke 0, slik
    // at disse eventene blir satt inn i listview'ene for hver uke når de lastes inn.
    // På denne måten slipper man også å skrive alle ukene på nytt til json, men heller kun skrive om uker der det har blitt lastet
    // inn og opprettet en timetable for den uken. Vi kan også begrense appen til feks 3 eventer samtidig på denne måten ved å
    // kun sjekke om det finnes flere eventer på samme tid og samme dag innenfor samme uke, i tillegg til uke 0 (gjentagende).
    // Når man da legger til en event, så vil den lese inn den uken det gjelder dersom den ikke allerede har blitt laget et
    // timetable objekt av, og deretter sjekke om det er plass (færre enn 3) på samme tid og dato, og derettere legge til dersom
    // det ikke er for mange eventer fra før. Da ender man kun opp med en user.timetableList der kun uker man har sett på eller lagt til
    // eller slettet fra er opprettet. Kun disse vil bli skrevet på nytt til json når appen lukkes.
    // på denne måten kan vi også lage en liste elns der man kan hoppe til en valgfri uke i et valgfritt år uten å ha lastet inn alle uker og år i forkant.
    // feks en liste for år. så vil det være enten 52 eller 53 uker (tall) bortover øverst på siden som man kan trykke på.
    // må også ha uke 53 fordi noen år har uke 53. 
    //A year has 53 weeks if 1 January is on a Thursday on a non-leap year, or on a Wednesday or a Thursday on a leap year.
    
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
