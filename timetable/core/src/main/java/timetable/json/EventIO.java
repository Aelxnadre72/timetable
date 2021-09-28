package timetable.json;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import timetable.core.Event;
import timetable.core.Timetable;

public class EventIO implements IO {

    // maybe needs to be changed to id later
    // or events needs an id attribute to sort event by week and id/or only id
    Timetable timetable;

    public EventIO(Timetable timetable){
        this.timetable = timetable;

        try{
            File file = new File("modules-template\\core\\src\\main\\java\\resources\\Events.json");
            if(file.createNewFile()){
                System.out.println("File created: Events.json");
                FileWriter writer = new FileWriter("modules-template\\core\\src\\main\\java\\resources\\Events.json");
                writer.write("[]");
                writer.close();
            }
        }catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public EventIO(){ //kun for testing
        try{
            File file = new File("modules-template\\core\\src\\main\\java\\resources\\Events.json");
            if(file.createNewFile()){
                System.out.println("File created: Events.json");
                FileWriter writer = new FileWriter("modules-template\\core\\src\\main\\java\\resources\\Events.json");
                writer.write("[]");
                writer.close();
            }
        }catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    // reads all, creates events and adds to timetable
    @Override
    public void read() {
        JSONParser jsonParser = new JSONParser();
         
        try{
	    FileReader reader = new FileReader("modules-template\\core\\src\\main\\java\\resources\\Events.json");
            Object obj = jsonParser.parse(reader);
 
            JSONArray eventList = (JSONArray) obj;

            for(Object event : eventList){
                JSONObject eventObject = (JSONObject) event;
                Event newEvent = new Event((String) eventObject.get("title"), (String) eventObject.get("description"), (String) eventObject.get("timeStart"), (String) eventObject.get("timeEnd"), (String) eventObject.get("day"));
                timetable.addEvent(newEvent);
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    // writes the last event in timetable.eventList to json
    @Override
    public void write(){
            JSONObject eventObject = new JSONObject();

            //Event event = timetable.getLastEvent();
            //eventObject.put("title", event.getTitle());
            //eventObject.put("description", event.getDescription());
            //eventObject.put("timeStart", event.getTimeStart());
            //eventObject.put("timeEnd", event.getTimeEnd());
            //eventObject.put("day", event.getDay());

            eventObject.put("title", "title");
            eventObject.put("description", "desc");
            eventObject.put("timeStart", "08:00");
            eventObject.put("timeEnd", "09:00");
            eventObject.put("day", "thursday");

            try{
                JSONParser jsonParser = new JSONParser();
                FileReader reader = new FileReader("modules-template\\core\\src\\main\\java\\resources\\Events.json");
                Object obj = jsonParser.parse(reader);
                JSONArray eventList = (JSONArray) obj;
                eventList.add(eventObject);

                FileWriter writer = new FileWriter("modules-template\\core\\src\\main\\java\\resources\\Events.json");
                writer.write(eventList.toJSONString());
                writer.flush();
                writer.close();
        }catch (FileNotFoundException e) {
			System.out.println("An error occured while writing to Events.json");
            e.printStackTrace();
		}catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EventIO test = new EventIO();
        test.write();
        System.out.println("HEI");
    }
}
