package core;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private String description;
    private LocalTime timeStart; //getDayOfWeek(); function
    private LocalTime timeEnd;
    private LocalDate date;
    
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getTimeStart(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return timeStart.format(formatter);
    }

    public void setTimeStart(String startString){
        if(!isCorrectTimeFormat(startString)){
            throw new IllegalArgumentException("setTimeStart has been given an argument of incorrect format.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        timeEnd = LocalTime.parse(startString, formatter);
    }

    public String getTimeEnd(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return timeEnd.format(formatter);
    }

    public void setTimeEnd(String endString){
        if(!isCorrectTimeFormat(endString)){
            throw new IllegalArgumentException("setTimeEnd has been given an argument of incorrect format.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        timeEnd = LocalTime.parse(endString, formatter);
    }

    public void setDate(String dateString){
        if(!isCorrectDateFormat(dateString)){
            throw new IllegalArgumentException("setDate has been given an argument of incorrect format.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        date = LocalDate.parse(dateString, formatter);
    }

    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    public boolean isCorrectTimeFormat(String s){
        // checks if String s is of length 5, contains a colon at index 2 and every other character is a digit
        return s.length() == 5 && s.substring(2, 3).equals(":") && s.replace(":", "").matches("[0-9]+");
    }

    public boolean isCorrectDateFormat(String s){
        // checks if String s is of length 10, contains a dot at index 2 and 5 and every other character is a digit
        return s.length() == 10 && s.substring(2, 3).equals(".") && s.substring(5, 6).equals(".");
    }

    public static void main(String[] args) {
        Event test = new Event();
        test.setDate("21.02.2000");
        System.out.println(test.getDate());
    }

}
