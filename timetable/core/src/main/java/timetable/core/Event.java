package timetable.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Event {
    private String title;
    private String description;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private LocalDate date;
    
    public Event(String title, String description, String timeStart, String timeEnd, String date){
        this.title = title;
        this.description = description;
        this.timeStart = timeParser(timeStart);
        this.timeEnd = timeParser(timeEnd);
        this.date = dateParser(date);
    }

    // get title
    public String getTitle(){
        return title;
    }

    // set title
    public void setTitle(String title){
        this.title = title;
    }

    // get description
    public String getDescription(){
        return description;
    }

    // set description
    public void setDescription(String description){
        this.description = description;
    }

    // get start time of event
    public String getTimeStart(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return timeStart.format(formatter);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // set start time of event
    public void setTimeStart(String startString){
        timeStart = timeParser(startString);
    }

    // get ending time of event
    public String getTimeEnd(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return timeEnd.format(formatter);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // set ending time of event
    public void setTimeEnd(String endString){
        timeEnd = timeParser(endString);
    }

    // set date of event
    public void setDate(String date){
        this.date = dateParser(date);
    }

    // get date of event
    public String getDate(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return date.format(formatter);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // returns week number
    public int getWeek(){
        WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        return weekNumber;
    }

    // returns year
    public int getYear(){
        return date.getYear();
    }

    // returns day of week 1-7
    public int getDayOfWeek(){
        return date.getDayOfWeek().getValue();
    }

    // getmonth, getday, getdate?

    // converts from string to LocalTime format
    private static LocalTime timeParser(String s){
        if(!isValidTime(s)){
            throw new IllegalArgumentException();
        }
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return LocalTime.parse(s, formatter);
        } catch(DateTimeParseException e){
            e.printStackTrace();
        }
        return null;
    }    

    // converts from string to LocalTime format
    private static LocalDate dateParser(String s){
        if(!isValidDate(s)){
            throw new IllegalArgumentException();
        }
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(s, formatter);
        } catch(DateTimeParseException e){
            e.printStackTrace();
        }
        return null;
    } 
    
    private static boolean isValidDate(String date){
        if (date.length()!=10){
            return false;
        }
        else if(!(date.substring(2,3).equals(".") && date.substring(5,6).equals("."))){
            return false;
        }
        else if(Integer.parseInt(date.substring(6, 10)) < 2020 || Integer.parseInt(date.substring(6, 10)) > 2030){
            return false;
        }
        else if(!(Integer.parseInt(date.substring(0, 2)) < 32 && Integer.parseInt(date.substring(0, 2)) > 0|| Integer.parseInt(date.substring(3, 5)) > 0 && Integer.parseInt(date.substring(3, 5)) < 13)){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean isValidTime(String time){
        if (time.length()!=5){
            return false;
        }  
        else if(!(time.substring(2,3).equals(":"))){
            return false;
        }
        else if(Integer.parseInt(time.substring(0, 2)) < 0 || Integer.parseInt(time.substring(0, 2)) > 23){
            return false;
        }
        else if(Integer.parseInt(time.substring(3, 5)) < 0 || Integer.parseInt(time.substring(3,5)) > 59) {
            return false;
        }
        else{
            return true;
        }
    }
    

    public static void main(String[] args) {
        Event test = new Event("title", "desc", "09:00", "10:00", "29.04.2020");
        System.out.println(test.getDayOfWeek());
    }

}
