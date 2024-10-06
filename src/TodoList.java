import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class TodoList {
  private ArrayList<Task> todoList;

  public TodoList() {
    this.todoList = new ArrayList<>();
  }

  // addTask : Method to add Task in the TodoList
  public void addTask(Task task) {
    this.todoList.add(task);
  }

  // removeTask : Method to remove a Task from the TodoList
  public void removeTask(int index) {
    if (index >= 0 && index < this.todoList.size())
      this.todoList.remove(index);
  }

  // editTask : Method to edit/Change the task of the task referenced by the index
  // provided
  public void editTask(int index, String task) {
    if (index >= 0 && index < this.todoList.size())
      this.todoList.get(index).setTask(task);
  }

  // editCategory : Method to edit/Change the category of the task referenced by
  // the index provided
  public void editCategory(int index, String category) {
    if (index >= 0 && index < this.todoList.size())
      this.todoList.get(index).setCategory(category);
  }

  // markDone && markUndone : Method to mark a task as Complete or Incomplete
  // true=>Complete || false=>Incomplete
  public void markDone(int index) {
    if (index >= 0 && index < this.todoList.size())
      this.todoList.get(index).setStatus(true);
  }

  public void markUndone(int index) {
    if (index >= 0 && index < this.todoList.size())
      this.todoList.get(index).setStatus(false);
  }

  /*
   * serialize(): Method used to serialize the TodoList data into a
   * String to be able to save the entire Object structure inside a text file
   * used as a database to store all the tasks along with their status and
   * category
   */
  private String serialize() {
    StringBuilder serializedData = new StringBuilder();
    int iterator = 0;
    for (iterator = 0; iterator < this.todoList.size() - 1; iterator++) {
      serializedData.append(this.todoList.get(iterator).toString() + ";");
    }
    serializedData.append(this.todoList.get(iterator).toString() + ";");
    return serializedData.toString();
  }

  /*
   * saveToFile() : Saves the entire todoList in a text file specified
   * specified by a file path provided as an argument
   * Note: Uses the serialize() method to first transfor the todoList
   * into a string and then saves it in the file
   */

  public boolean saveToFile(String filePath) {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter writer = new FileWriter(file);
      writer.write(this.serialize());
      writer.close();
      return true;
    } catch (IOException e) {
      System.out.println("Failed to Save");
    }
    return false;
  }

  /*
   * loadFromFile(String filePath) : Reads the specified file and tries to
   * translate the
   * string into a Todolist
   */
  public boolean loadFromFile(String filePath) {
    try {
      StringBuilder data = new StringBuilder();
      File file = new File(filePath);
      if (!file.exists())
        file.createNewFile();
      Scanner reader = new Scanner(file);
      // Reading the lines from the file
      while (reader.hasNextLine())
        data.append(reader.nextLine());
      reader.close(); // Closing the reader
      // Creating the todoList using the String read
      if (data.isEmpty())
        return true;
      String[] tasks = data.toString().split(";");
      for (String task : tasks) {
        String[] taskArg = task.split(",");
        this.addTask(new Task(taskArg[0], taskArg[1], ((taskArg[2].equals("1")) ? true : false)));
      }
      return true;
    } catch (IOException e) {
      System.out.println("Failed to Load");
      return false;
    }
  }

  // showTasks() : method to Show the tasks in a tabular format

  public void showTasks() {
    int index = 0;
    System.out.println("\n+---+----------------------------------------+----------+------+");
    String heading = String.format("|%-3s|%-40s|%-10s|%-5s|", "Id", "Task", "Category", "Status");
    System.out.println(heading);
    System.out.println("+---+----------------------------------------+----------+------+");
    for (Task task : todoList) {
      String row = String.format("|%-3d|%-40s|%-10s|%-6s|", index, task.getTask(), task.getCategory(),
          (task.getStatus() ? "Done" : "Due"));
      System.out.println(row);
      index++;
    }
    System.out.println("+---+----------------------------------------+----------+------+");
    // System.out.println("\n");
  }

  public void showTasks(String category) {
    System.out.println("\n+---+----------------------------------------+----------+------+");
    String heading = String.format("|%-3s|%-40s|%-10s|%-5s|", "Id", "Task", "Category", "Status");
    System.out.println(heading);
    System.out.println("+---+----------------------------------------+----------+------+");
    for (int i = 0; i < todoList.size(); i++) {
      Task task = todoList.get(i);
      if (task.getCategory().equals(category)) {
        String row = String.format("|%-3d|%-40s|%-10s|%-6s|", i, task.getTask(), task.getCategory(),
            (task.getStatus() ? "Done" : "Due"));
        System.out.println(row);
      }
    }
    System.out.println("+---+----------------------------------------+----------+------+");
    // System.out.println("\n");
  }

  public void showTasks(boolean status) {
    System.out.println("\n+---+----------------------------------------+----------+------+");
    String heading = String.format("|%-3s|%-40s|%-10s|%-5s|", "Id", "Task", "Category", "Status");
    System.out.println(heading);
    System.out.println("+---+----------------------------------------+----------+------+");
    for (int i = 0; i < todoList.size(); i++) {
      Task task = todoList.get(i);
      if (task.getStatus() == status) {
        String row = String.format("|%-3d|%-40s|%-10s|%-6s|", i, task.getTask(), task.getCategory(),
            (task.getStatus() ? "Done" : "Due"));
        System.out.println(row);
      }
    }
    System.out.println("+---+----------------------------------------+----------+------+");
    // System.out.println("\n");
  }
}
