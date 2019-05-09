class Event {
  private String title, date;
  private int id;
  private static int lastId = 0; //Static, to keep count of id
  
  public Event() {
    this.setTitle("No title");
    this.setDate("No date");
    lastId += 1;
    this.id = lastId;
  }

  public Event(String title, String date) {
    this.setTitle(title);
    this.setDate(date);
    lastId += 1;
    this.id = lastId;
  }

  public String getTitle(){
    return this.title;
  }

  public void setTitle(String title){
    this.title = title;
  }

  public String getDate(){
    return this.date;
  }

  public void setDate(String date){
    this.date = date;
  }

  public int getId(){
    return this.id;
  }
}