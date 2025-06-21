class Task {
    int taskId;
    String taskName;
    String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + taskId + "] " + taskName + " - " + status;
    }
}

// Node of the linked list
class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

// Singly Linked List implementation
public class TaskManagement {
    private TaskNode head = null;

    // Add task at end
    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Task added.");
    }

    // Search task by ID
    public Task searchTask(int taskId) {
        TaskNode temp = head;
        while (temp != null) {
            if (temp.task.taskId == taskId) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
    }

    // Traverse tasks
    public void listTasks() {
        TaskNode temp = head;
        System.out.println("Task List:");
        while (temp != null) {
            System.out.println(temp.task);
            temp = temp.next;
        }
    }

    // Delete task by ID
    public void deleteTask(int taskId) {
        if (head == null) {
            System.out.println("No tasks to delete.");
            return;
        }

        if (head.task.taskId == taskId) {
            head = head.next;
            System.out.println("Task deleted.");
            return;
        }

        TaskNode prev = head;
        TaskNode curr = head.next;

        while (curr != null) {
            if (curr.task.taskId == taskId) {
                prev.next = curr.next;
                System.out.println("Task deleted.");
                return;
            }
            prev = curr;
            curr = curr.next;
        }

        System.out.println("Task not found.");
    }

    // Main method for testing
    public static void main(String[] args) {
        TaskManagement tm = new TaskManagement();

        tm.addTask(new Task(1, "Design UI", "Pending"));
        tm.addTask(new Task(2, "Setup Database", "In Progress"));
        tm.addTask(new Task(3, "Implement Login", "Pending"));

        System.out.println();
        tm.listTasks();

        System.out.println("\nSearching for task with ID 2:");
        Task result = tm.searchTask(2);
        System.out.println(result != null ? result : "Not Found");

        System.out.println("\nDeleting task with ID 1:");
        tm.deleteTask(1);

        System.out.println();
        tm.listTasks();
    }
}
