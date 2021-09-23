package core;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Event {
    private String title;
    private String description;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private LocalDate date;
    
    public Event(String title, String description, String timeStart, String timeEnd, String date){
        if(!isCorrectTimeFormat(timeStart) || !isCorrectDateFormat(date)){
            throw new IllegalArgumentException("Time or date is in incorrect format.");
        }
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return timeStart.format(formatter);
    }

    // set start time of event
    public void setTimeStart(String startString){
        if(!isCorrectTimeFormat(startString)){
            throw new IllegalArgumentException("setTimeStart has been given an argument of incorrect format.");
        }
        timeStart = timeParser(startString);
    }

    // get ending time of event
    public String getTimeEnd(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return timeEnd.format(formatter);
    }

    // set ending time of event
    public void setTimeEnd(String endString){
        if(!isCorrectTimeFormat(endString)){
            throw new IllegalArgumentException("setTimeEnd has been given an argument of incorrect format.");
        }
        timeEnd = timeParser(endString);
    }

    // set date of event
    public void setDate(String dateString){
        if(!isCorrectDateFormat(dateString)){
            throw new IllegalArgumentException("setDate has been given an argument of incorrect format.");
        }
        date = dateParser(dateString);
    }

    // get date of event
    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    // get the day of the week of the event
    public int getDayOfWeek(){
        // day of week is represented by 1 to 7
        return date.getDayOfWeek().getValue();
    }

    
    // get the week of year of the event
    public int getWeekOfYear(){
        // day of week is represented by 1 to 52
        WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
        return date.get(weekFields.weekOfWeekBasedYear());
    }

    // get the year
    public int getYear(){
        return date.getYear();
    }

    // converts from string to LocalTime format
    private static LocalTime timeParser(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(s, formatter);
    }

    // converts from string to LocalDate format
    private static LocalDate dateParser(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(s, formatter);
    }
    
    // check that the time is in the correct format
    private boolean isCorrectTimeFormat(String s){
        // checks if String s is of length 5, contains a colon at index 2 and every other character is a digit
        return s.length() == 5 && s.substring(2, 3).equals(":") && s.replace(":", "").matches("[0-9]+");
    }

    // check that the date is in the correct format
    private boolean isCorrectDateFormat(String s){
        // checks if String s is of length 10, contains a dot at index 2 and 5 and every other character is a digit
        return s.length() == 10 && s.substring(2, 3).equals(".") && s.substring(5, 6).equals(".");
    }

    public static void main(String[] args) {
        Event test = new Event("tittel", "beskrivelse", "08:00", "09:00", "22.09.2021");
        // test.setDate("21.02.2000");
        System.out.println(test.getDate());
        System.out.println(test.getWeekOfYear());
    }

}
