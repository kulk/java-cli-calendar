import java.util.ArrayList;

class Calendar {
  static ArrayList<Event> eventsList = new ArrayList<>();

  static Event createEvent(String title, String date) {
    Event eventInstance = new Event(title, date);
    return eventInstance;
  }
}