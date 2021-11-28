package timetable.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Create user with attribute. */
public class User {
  private Map<String, Timetable> timetableMap = new HashMap<>();

  /**
   * Add timetable-object to timetableList.
   *
   * @param timetable timetable to be added
   * @return timetable that was added
   * @throws IllegalArgumentException if timetable with key already exists
   */
  public Timetable addTimetable(Timetable timetable) {
    // Key is week + year
    String k = String.valueOf(timetable.getWeek()) + String.valueOf(timetable.getYear());
    if (hasTimetable(k)) {
      throw new IllegalArgumentException("The timetable with this specific key already exist.");
    }
    return timetableMap.put(k, timetable);
  }

  /**
   * Get timetable-object in timetableMap with key k.
   *
   * @param k the key of the object
   * @return the object with the corresponding key
   * @throws IllegalArgumentException if no timetable tith key k exists
   */
  public Timetable getTimetable(String k) {
    if (!timetableMap.containsKey(k)) {
      return null;
    }
    return timetableMap.get(k);
  }

  public List<Timetable> getTimetableList() {
    List<Timetable> l = new ArrayList<>(timetableMap.values());
    return l;
  }

  /**
   * Check if timetableMap contains a timetable with key k.
   *
   * @param k the key
   * @return true if timetableMap containts key, false otherwise
   */
  public boolean hasTimetable(String k) {
    if (timetableMap.containsKey(k)) {
      return true;
    }
    return false;
  }

  /**
   * Remove timetable with key k.
   *
   * @param k key beloning to the timetable
   * @throws IllegalArgumentException if there is no timetable with the gived key k
   */
  public void removeTimetable(String k) {
    if (timetableMap.containsKey(k)) {
      timetableMap.remove(k);
    } else {
      throw new IllegalArgumentException("There is no timetable with this key.");
    }
  }
}
