import java.util.Scanner;

class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "[" + employeeId + "] " + name + " - " + position + " - ₹" + salary;
    }
}

public class EmployeeManagement {
    static Employee[] employees = new Employee[100];
    static int count = 0;

    // Add employee
    public static void addEmployee(Employee emp) {
        if (count < employees.length) {
            employees[count++] = emp;
            System.out.println("Employee added.");
        } else {
            System.out.println("Employee list is full.");
        }
    }

    // Search employee by ID
    public static Employee searchEmployee(int empId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == empId) {
                return employees[i];
            }
        }
        return null;
    }

    // Traverse all employees
    public static void listEmployees() {
        System.out.println("All Employees:");
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete employee by ID
    public static void deleteEmployee(int empId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == empId) {
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                System.out.println("Employee deleted.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        addEmployee(new Employee(101, "Alice", "Developer", 50000));
        addEmployee(new Employee(102, "Bob", "Manager", 75000));
        addEmployee(new Employee(103, "Charlie", "Analyst", 60000));

        System.out.println();
        listEmployees();

        System.out.println("\nSearching for employee with ID 102:");
        Employee found = searchEmployee(102);
        System.out.println(found != null ? found : "Not found.");

        System.out.println("\nDeleting employee with ID 101...");
        deleteEmployee(101);

        System.out.println();
        listEmployees();

        sc.close();
    }
}
