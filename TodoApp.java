
import java.util.HashMap;
import java.util.Scanner;

class Task {

    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[completed] " : "[ ] ") + description;
    }
}

public class TodoApp {

    private static final HashMap<Integer, Task> tasks = new HashMap<>();
    private static int currentId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 ->
                    addTask(scanner);
                case 2 ->
                    viewTasks();
                case 3 ->
                    markTaskCompleted(scanner);
                case 4 ->
                    removeTask(scanner);
                case 5 -> {
                    running = false;
                    System.out.println("Exiting...");
                }
                default ->
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nTo-Do List Application");
        System.out.println("1. Add task");
        System.out.println("2. View tasks");
        System.out.println("3. Mark task as completed");
        System.out.println("4. Remove task");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addTask(Scanner scanner) {
        System.out.println("Enter the task description: ");
        String desc = scanner.nextLine();
        tasks.put(currentId, new Task(desc));
        System.out.println("Task added with ID: " + currentId);
        currentId++;
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available");
        }

        for (var entry : tasks.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void markTaskCompleted(Scanner scanner) {
        System.out.print("Enter the task number to mark as completed: ");
        int id = scanner.nextInt();
        Task task = tasks.get(id);

        if (task != null) {
            task.setCompleted(true);
            System.out.println("Task " + id + " marked as completed.");
        } else {
            System.out.println("Task ID not found.");
        }
    }

    private static void removeTask(Scanner scanner) {
        System.out.print("Enter the task number to remove: ");
        int id = scanner.nextInt();

        if (tasks.remove(id) != null) {
            System.out.println("Task " + id + " removed.");
        } else {
            System.out.println("Task ID not found.");
        }
    }

}
