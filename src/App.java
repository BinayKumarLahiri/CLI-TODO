import java.util.Scanner;
import java.io.*;

public class App {
    private TodoList todoList;
    private String dbPath;
    private boolean running;

    public App(String filePath) {
        this.todoList = new TodoList();
        this.dbPath = filePath;
        this.running = true;
    }

    public boolean parse(String cmd) {
        if (cmd.length() == 0)
            return false;
        String[] tokens = cmd.split(" ");
        if (tokens.length == 1) {
            switch (tokens[0].toLowerCase()) {
                case "list":
                    this.clearScreen();
                    this.todoList.showTasks();
                    return true;
                case "save":
                    this.todoList.saveToFile(this.dbPath);
                    return false;
                case "help":
                    this.clearScreen();
                    this.help();
                    return true;
                case "exit":
                    this.running = false;
                    return true;
                default:
                    this.clearScreen();
                    System.out.println("Invalid Command");
                    return false;
            }
        } else if (tokens.length == 2) {
            switch (tokens[0].toLowerCase()) {
                case "list":
                    this.clearScreen();
                    if (tokens[1].equals(".")) {
                        this.todoList.showTasks(false);
                        return true;
                    } else {
                        this.todoList.showTasks(tokens[1]);
                        return true;
                    }
                case "done":
                    int index = Integer.parseInt(tokens[1]);
                    this.todoList.markDone(index);
                    return false;
                case "undone":
                    int id = Integer.parseInt(tokens[1]);
                    this.todoList.markUndone(id);
                    return false;
                case "delete":
                    int idd = Integer.parseInt(tokens[1]);
                    this.todoList.removeTask(idd);
                    return false;
                default:
                    this.clearScreen();
                    System.out.println("Invalid Command");
                    return false;
            }
        } else if (tokens.length >= 3) {
            switch (tokens[0].toLowerCase()) {
                case "add":
                    int i = 1;
                    StringBuilder task = new StringBuilder();
                    StringBuilder category = new StringBuilder();
                    while (i < tokens.length && !tokens[i].equals(",")) {
                        task.append(tokens[i] + " ");
                        i++;
                    }
                    if (i == tokens.length || i == tokens.length - 1) {
                        System.out.println("Invalid Command");
                        return false;
                    }
                    i++;
                    while (i < tokens.length)
                        category.append(tokens[i++]);
                    this.todoList.addTask(new Task(task.toString(), category.toString(), false));
                    return false;
                case "edit":
                    int index = Integer.parseInt(tokens[1]);
                    StringBuilder newTask = new StringBuilder();
                    for (int it = 2; it < tokens.length; it++) {
                        newTask.append(tokens[it] + " ");
                    }
                    newTask.deleteCharAt(newTask.length() - 1);
                    this.todoList.editTask(index, newTask.toString());
                    return false;
                default:
                    System.out.println("Invalid Command");
                    return false;
            }
        } else {
            System.out.println("Invalid Command");
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        App todoApp = new App("./Database.txt");
        todoApp.todoList.loadFromFile(todoApp.dbPath);
        todoApp.todoList.showTasks();
        while (todoApp.running) {
            System.out.println("Enter 'help' for Help");
            System.out.print(">> ");
            String cmd = sc.nextLine();
            boolean isPrinted = todoApp.parse(cmd);

            if (!isPrinted) {
                todoApp.clearScreen();
                todoApp.todoList.showTasks();
            }
        }
        sc.close();
    }

    private void clearScreen() {
        // Check if the system is Windows
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                // Run the cls command only once using ProcessBuilder
                this.clearScreenOnce();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("This command is specific to Windows.");
        }
    }

    private void clearScreenOnce() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
        pb.inheritIO(); // Inherit terminal's I/O
        // Start the process and wait for it to finish
        Process process = pb.start();
        process.waitFor(); // Ensure it finishes before moving on
    }

    private void help() {
        String[][] commands = {
                { "list", "Lists all the tasks stored" },
                { "list .", "Displays all the Due Tasks" },
                { "list [category]", "Filters the tasks By Category" },
                { "add [task] , [category]", "Add a Task" },
                { "edit [id] [task]", "Edit a Task" },
                { "done [id]", "Mark Task as Done" },
                { "undone [id]", "Mark Task as Due" },
                { "delete [id]", "Removes a Task" },
                { "save", "Save all the task in file" },
                { "help", "Display all the Commands" },
                { "exit", "Exit the Program{Without Saving}" }
        };
        System.out.println("\n+---+-------------------------+--------------------------------+");
        String heading = String.format("|%-3s|%-25s|%-32s|", "Id", "Command",
                "Description");
        System.out.println(heading);
        System.out.println("+---+-------------------------+--------------------------------+");
        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i];
            String row = String.format("|%-3d|%-25s|%-32s|", i, command[0], command[1]);
            System.out.println(row);
        }
        System.out.println("+---+-------------------------+--------------------------------+");
    }
}
