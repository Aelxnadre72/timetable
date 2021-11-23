package timetable.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Event {
    private String title;
    private String category;
    private String description;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private LocalDate date;
    
    public Event(String title, String category, String description, String timeStart, String timeEnd, String date){
        this.title = title;
        this.category = category;
        this.description = description;
        this.timeStart = timeParser(timeStart);
        this.timeEnd = timeParser(timeEnd);
        this.date = dateParser(date);
    }

    /**
     * @return gets the title of event
     */
    public String getTitle(){
        return title;
    }

    /**
     * sets title of event
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /** 
     * @return gets category of event
     */
    public String getCategory(){
        return category;
    }

    /**
     * sets category of event
     * @param category
     */
    public void setCategory(String category){
        this.category = category;
    }

    /**
     * @return gets description of event
     */
    public String getDescription(){
        return description;
    }

    /**
     * sets description of event
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * @return gets start time of event
     */
    public String getTimeStart(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return timeStart.format(formatter);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sets start time of event
     * @param startString
     */
    public void setTimeStart(String startString){
        timeStart = timeParser(startString);
    }

    /**
     * @return gets ending time of event
     */
    public String getTimeEnd(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return timeEnd.format(formatter);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * set ending time of event
     * @param endString
     */
    public void setTimeEnd(String endString){
        timeEnd = timeParser(endString);
    }

    /**
     * set date of event
     * @param date
     */
    public void setDate(String date){
        this.date = dateParser(date);
    }

    /**
     * @return get date of event
     */
    public String getDate(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return date.format(formatter);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * @return returns week number
     */
    public int getWeek(){
        WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        return weekNumber;
    }

    /**
     * @return returns year of event
     */
    public int getYear(){
        return date.getYear();
    }

    /**
     * @return returns day of week 1-7
     */
    public int getDayOfWeek(){
        return date.getDayOfWeek().getValue();
    }

    

    /**
     * converts from string to LocalTime format
     * @param s
     * @return returns the LocalTime format of string s
     */
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

    /**
     * converts from string to LocalDate format
     * @param s
     * @return returns the LocalDate format of string s
     */
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
    
    /**
     * Checks if the date is valid 
     * @param date
     * @return true if the date is valid and false if its unvalid
     */
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
        else if(Integer.parseInt(date.substring(0, 2)) >= 32 || Integer.parseInt(date.substring(0, 2)) <= 0 || Integer.parseInt(date.substring(3, 5)) <= 0 || Integer.parseInt(date.substring(3, 5)) >= 13){
            return false;
        }
        else{
            return true;
        }
    }
    /**
     * Checks if the time is valid
     * @param time
     * @return returns true if the time is valid and false if its unvalid
     */
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

}
