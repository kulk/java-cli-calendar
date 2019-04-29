import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Main {
  public static void main(String[] args) {
    Menu.main_menu();
  }
}

class Calendar {
  static ArrayList<Event> eventsList = new ArrayList<>();

  static Event createEvent(String title, String date) {
    try{//https://www.javainterviewpoint.com/java-constructor-newinstance-method-example/
      Class eventInstance = Class.forName("Event");
      Constructor constructor = eventInstance.getConstructor(new Class[]{String.class, String.class});
      Event event = (Event)constructor.newInstance(title, date);
      return event;      
    }
    catch(Exception e){
      e.printStackTrace();
      return null;
    }
  }
}

class Menu {
  static void main_menu() {
    // Prints menu options. Assigns user input to variable 'choice' and submits choice to exec_menu.
    String menu_string = "\nPlease select an option:\n" + "1. Show events\n" + "2. New event\n" + "3. Edit event\n" + "4. Delete event\n" +"5. Exit\n";
    System.out.println(menu_string);
    Scanner menuSelection = new Scanner(System.in);
    String choice = menuSelection.nextLine();
    exec_menu(choice);
  }

  static void exec_menu(String choice) {
    // Takes choice, uses switch statement to call the matching method
    switch (choice) {
    case "1":
      Menu.showEvent();
      break;
    case "2":
      Menu.newEvent();
      break;
    case "3":
      Menu.editEvent();
      break;
    case "4":
      Menu.deleteEvent();
      break;
    case "5":
      Menu.exit();
      break;
    default:
      System.out.println("Invalid selection, please try again.\n");
      Menu.main_menu();
      break;
    }
  }

  static void showEvent() {
    System.out.println("\nEvents in calendar:\n");
    if(Calendar.eventsList.isEmpty() == false){
      for(Event i : Calendar.eventsList){
        System.out.println(i.id + ". " + i.title + " - " + i.date);
      }
    }else{
      System.out.println("There are no events in the calendar\n");
    }
    Menu.main_menu();
  }

  static void newEvent() {
    Scanner myObj = new Scanner(System.in);
    System.out.println("\nEnter event title:");
    String title = myObj.nextLine();
    System.out.println("\nEnter date:");
    String date = myObj.nextLine();
    Calendar.eventsList.add(Calendar.createEvent(title, date));
    System.out.println("\nEvent " + title + " has been added on " + date);
    Menu.main_menu();
  }

  static void editEvent(){
    if(Calendar.eventsList.isEmpty() == false){
      Scanner myObj = new Scanner(System.in);
      System.out.println("\nProvide ID of event to Edit:");
      int id = myObj.nextInt();
      myObj.nextLine(); //throw away the \n not consumed by nextInt()
      
      for(Event i : Calendar.eventsList){// To find List position which matched id
        if(i.id == id){
          System.out.println("\nTitle is: " + i.title + "\nDate is: " + i.date + "\n");
          System.out.println("Enter new title:");
          String newTitle = myObj.nextLine();
          System.out.println("\nEnter new date:");
          String newDate = myObj.nextLine();
          i.title = newTitle;
          i.date = newDate;
          break;
        }
      }   
      System.out.println("\nEvent has been updated");
    }else{
      System.out.println("\nThere are no events in the calendar\n");
    }
    Menu.main_menu();
  }

  static void deleteEvent(){
    if(Calendar.eventsList.isEmpty() == false){
      Scanner myObj = new Scanner(System.in);
      System.out.println("\nProvide ID of event to Delete:\n");
      int id = myObj.nextInt();
      for(Event i : Calendar.eventsList){// To find List position which matched id
        if(i.id == id){
          Calendar.eventsList.remove(i);
          break;
        }
      }   
      System.out.println("\nEvent has been deleted\n");
    }else{
      System.out.println("\nThere are no events in the calendar\n");
    }
    Menu.main_menu();
  }
 
  static void exit() {
    System.out.println("\nProgram is terminated\n");
    System.exit(0);
  }
}

class Event {
  String title, date;
  int id;
  static int last_id = 0; //Static, to keep count of id
  
  public Event() {
    this.title = "No title";
    this.date = "No date";
    last_id += 1;
    this.id = last_id;
  }

  public Event(String title, String date) {
    this.title = title;
    this.date = date;
    last_id += 1;
    this.id = last_id;
  }
}

