import java.util.Scanner;
import java.util.InputMismatchException;

class Menu {
  static void mainMenu() {
    // Prints menu options. Assigns user input to variable 'choice' and submits choice to execMenu.
    String menuString = "\nPlease select an option:\n" + "1. Show events\n" + "2. New event\n" + "3. Edit event\n" + "4. Delete event\n" +"5. Exit\n";
    System.out.println(menuString);
    Scanner menuSelection = new Scanner(System.in);
    String choice = menuSelection.nextLine();
    execMenu(choice);
  }

  static void execMenu(String choice) {
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
      Menu.mainMenu();
      break;
    }
  }

  static void showEvent() {
    System.out.println("\nEvents in calendar:\n");
    if(Calendar.eventsList.isEmpty() == false){
      for(Event i : Calendar.eventsList){
        System.out.println(i.getId() + ". " + i.getTitle() + " - " + i.getDate());
      }
    }else{
      System.out.println("There are no events in the calendar\n");
    }
    Menu.mainMenu();
  }

  static void newEvent() {
    Scanner myObj = new Scanner(System.in);
    System.out.println("\nEnter event title:");
    String title = myObj.nextLine();
    System.out.println("\nEnter date:");
    String date = myObj.nextLine();
    Calendar.eventsList.add(Calendar.createEvent(title, date));
    System.out.println("\nEvent " + title + " has been added on " + date);
    Menu.mainMenu();
  }

  static int getIdInput(String editDelete){
    int id;
    Scanner myObj = new Scanner(System.in);
    System.out.println("\nProvide ID of event to " + editDelete +":");      
    while(true){
      try{
        id = myObj.nextInt();
        myObj.nextLine(); //throw away the \n not consumed by nextInt()
        break;
      }
      catch(InputMismatchException e){
        // Catch exception in case user does not enter an int
        System.out.println("\nInvalid id. Please try again:");
        myObj.nextLine();
      }
    }
    return id;
  }

  static void editEvent(){
    if(Calendar.eventsList.isEmpty() == false){
      int id = getIdInput("edit");
      boolean eventExists = false;
      
      for(Event i : Calendar.eventsList){// To find List position which matched id
        if(i.getId() == id){
          Scanner myObj = new Scanner(System.in);
          System.out.println("\nTitle is: " + i.getTitle() + "\nDate is: " + i.getDate() + "\n");
          System.out.println("Enter new title:");
          String newTitle = myObj.nextLine();
          System.out.println("\nEnter new date:");
          String newDate = myObj.nextLine();
          i.setTitle(newTitle);
          i.setDate(newDate);
          System.out.println("\nEvent has been updated");
          eventExists = true;
          break;
        }
      }   
      if(eventExists == false){
          System.out.println("\nEvent does not exist");
      }
    }else{
      System.out.println("\nThere are no events in the calendar\n");
    }
    Menu.mainMenu();
  }

  static void deleteEvent(){
    if(Calendar.eventsList.isEmpty() == false){
      int id = getIdInput("delete");
      boolean eventExists = false;

      for(Event i : Calendar.eventsList){// To find List position which matched id
        if(i.getId() == id){
          Calendar.eventsList.remove(i);
          System.out.println("\nEvent has been deleted\n");
          eventExists = true;
          break;
        }
        if(eventExists == false){
          System.out.println("\nEvent does not exist");
        }
      }
    }else{
      System.out.println("\nThere are no events in the calendar\n");
    }
    Menu.mainMenu();
  }

  static void exit() {
    System.out.println("\nProgram is terminated\n");
    System.exit(0);
  }
}