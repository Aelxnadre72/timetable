package timetable.core;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Create event with attributes.
 */
public class Event {
  private String title;
  private String category;
  private String description;
  private LocalTime timeStart;
  private LocalTime timeEnd;
  private LocalDate date;

  /**
   * Constructor to set event attributes.
   *
   * @param title title of event
   * @param category category of event
   * @param description description of event
   * @param timeStart start time of event
   * @param timeEnd end time of event
   * @param date date of event
  */
  public Event(
      String title,
      String category,
      String description,
      String timeStart,
      String timeEnd,
      String date) {
    this.title = title;
    this.category = category;
    this.description = description;
    this.timeStart = timeParser(timeStart);
    this.timeEnd = timeParser(timeEnd);
    this.date = dateParser(date);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /** 
   * Gets timeStart as String.
   *
   * @return start time
   * @throws DateTimeException if formatting does not work
   */
  public String getTimeStart() {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
      return timeStart.format(formatter);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setTimeStart(String startString) {
    timeStart = timeParser(startString);
  }

  /** 
   * Gets timeEnd as String.
   *
   * @return end time
   * @throws DateTimeException if formatting does not work
   */
  public String getTimeEnd() {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
      return timeEnd.format(formatter);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setTimeEnd(String endString) {
    timeEnd = timeParser(endString);
  }

  public void setDate(String date) {
    this.date = dateParser(date);
  }

  /** 
   * Gets date as String.
   *
   * @return date
   * @throws DateTimeException if formatting does not work
   */
  public String getDate() {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      return date.format(formatter);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Gets week as int in [1-53].
   *
   * @return week
   * @throws DateTimeException if formatting does not work
   */
  public int getWeek() {
    Locale.setDefault(new Locale("no", "NO"));
    WeekFields weekFields = WeekFields.of(Locale.getDefault());
    int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
    return weekNumber;
  }

  public int getYear() {
    return date.getYear();
  }

  /**
   * Gets day as int in [0-6].
   *
   * @return day of week  
   */
  public int getDayOfWeek() {
    return date.getDayOfWeek().getValue();
  }

  /**
   * Converts from string to LocalTime format.
   *
   * @param s to be parsed
   * @return returns the LocalTime format of string s
   * @throws DateTimeParseException if s is an invalid time
   */
  private static LocalTime timeParser(String s) {
    if (!isValidTime(s)) {
      throw new IllegalArgumentException();
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
      return LocalTime.parse(s, formatter);
    } catch (DateTimeParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Converts from string to LocalDate format.
   *
   * @param s to be parsed
   * @return returns the LocalDate format of string s
   * @throws DateTimeParseException if s is an invalid date
   */
  private static LocalDate dateParser(String s) {
    if (!isValidDate(s)) {
      throw new IllegalArgumentException();
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      Locale.setDefault(new Locale("no", "NO"));
      return LocalDate.parse(s, formatter);
    } catch (DateTimeParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Checks if the date is valid.
   *
   * @param date to be evaluated
   * @return true if the date is valid and false if its invalid
   */
  private static boolean isValidDate(String date) {
    if (date.length() != 10) {
      return false;
    } else if (!(date.substring(2, 3).equals(".") && date.substring(5, 6).equals("."))) {
      return false;
    } else if (Integer.parseInt(date.substring(6, 10)) < 2020
        || Integer.parseInt(date.substring(6, 10)) > 2030) {
      return false;
    } else if (Integer.parseInt(date.substring(0, 2)) >= 32
        || Integer.parseInt(date.substring(0, 2)) <= 0
        || Integer.parseInt(date.substring(3, 5)) <= 0
        || Integer.parseInt(date.substring(3, 5)) >= 13) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Checks if the time is valid.
   *
   * @param time to be evaluated
   * @return returns true if the time is valid and false if its invalid
   */
  private static boolean isValidTime(String time) {
    if (time.length() != 5) {
      return false;
    } else if (!(time.substring(2, 3).equals(":"))) {
      return false;
    } else if (Integer.parseInt(time.substring(0, 2)) < 0
        || Integer.parseInt(time.substring(0, 2)) > 23) {
      return false;
    } else if (Integer.parseInt(time.substring(3, 5)) < 0
        || Integer.parseInt(time.substring(3, 5)) > 59) {
      return false;
    } else {
      return true;
    }
  }
}
