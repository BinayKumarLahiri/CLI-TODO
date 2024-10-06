public class Task {
  private String task; // actual Task stored inside the ask Object
  private String category; // Defines muliple caegories a ask can have
  private boolean status; // Defines he saus of the Task
  // Constructor

  public Task(String task, String category, boolean status) {
    this.task = task;
    this.category = category;
    this.status = status;
  }

  // Setting Geers and Setters
  public void setTask(String task) {
    this.task = task;
  }

  public String getTask() {
    return this.task;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCategory() {
    return this.category;
  }

  public boolean getStatus() {
    return this.status;
  }

  public void setStatus(boolean status) {
    this.status = status; // Marks the Task as Complete or Incomplete : true=>Complete ||
                          // false=>Incomplete
  }

  // Serialize data to store it in a text file and further read it from the file
  // to generate the required object structure
  @Override
  public String toString() {
    StringBuilder serializedData = new StringBuilder();
    serializedData.append(this.task + "," + this.category + "," + ((this.status == true) ? "1" : "0"));
    return serializedData.toString();
  }
}
