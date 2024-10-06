# CLI-TODO: Terminal-based To-Do List App

Welcome to the **CLI-TODO: Terminal-based Todo List App**! This app allows you to manage your tasks directly from the command line, offering an easy-to-use interface for tracking tasks with a range of commands to add, modify, and organize your tasks.

---

## Features

- Add, remove, edit tasks
- Categorize tasks
- Mark tasks as complete/incomplete
- List all or filtered tasks by Category
- Save/load task lists from a file
- Lightweight and works entirely in the terminal
- Easy to User Interface
- Intuitive Commands to Interact with the program

## Getting Started

### Prerequisites

- Java installed on your system
- Basic knowledge of running terminal commands

### Running the App

1. Clone the repository to your local machine.
2. Compile and run the program using the following commands:

```bash
cd src
javac App.java
java App
```

## Available Commands

CLI-TODO used a collection of commands to interact with the program to add, mark, remove, edit and do lot more.

```bash
+---+-------------------------+--------------------------------+
|Id |Command                  |Description                     |
+---+-------------------------+--------------------------------+
|0  |list                     |Lists all the tasks stored      |
|1  |list .                   |Displays all the Due Tasks      |
|2  |list [category]          |Filters the tasks By Category   |
|3  |add [task] , [category]  |Add a Task                      |
|4  |edit [id] [task]         |Edit a Task                     |
|5  |done [id]                |Mark Task as Done               |
|6  |undone [id]              |Mark Task as Due                |
|7  |delete [id]              |Removes a Task                  |
|8  |save                     |Save all the task in file       |
|9  |help                     |Display all the Commands        |
|10 |exit                     |Exit the Program{Without Saving}|
+---+-------------------------+--------------------------------+
```

## Example

```bash

+---+----------------------------------------+----------+------+
|Id |Task                                    |Category  |Status|
+---+----------------------------------------+----------+------+
|0  |Cook Food                               |Home      |Done  |
|1  |Buy groceries                           |Leisure   |Done  |
|2  |Complete homework                       |Home      |Done  |
|3  |Learn JavaScript                        |Leisure   |Done  |
|4  |Clean room                              |Work      |Due   |
|5  |Prepare presentation                    |Leisure   |Done  |
|6  |Attend meeting                          |Study     |Done  |
|7  |Read book                               |Home      |Due   |
|8  |Exercise                                |Work      |Done  |
|9  |Write blog post                         |Study     |Due   |
|10 |Study for exam                          |Study     |Due   |
|11 |Cook dinner                             |Work      |Due   |
|12 |Plan vacation                           |Home      |Done  |
|13 |Fix computer                            |Study     |Due   |
|14 |Add Certificate                         |Home      |Due   |
|15 |Call friend                             |Work      |Done  |
|16 |Pay bills                               |Study     |Due   |
|17 |Organize closet                         |Home      |Due   |
|18 |Backup files                            |Leisure   |Done  |
|19 |Research investment                     |Fitness   |Due   |
+---+----------------------------------------+----------+------+
Enter 'help' for Help
>>
```
