import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static EmployeeService service;

    public static void main(String[] args) {
        try {
            EmployeeRepository repository = new EmployeeRepositoryImpl();
            service = new EmployeeService(repository);
            
            boolean running = true;
            while (running) {
                System.out.println("\nEmployee Management System");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. View Employee by ID");
                System.out.println("4. Update Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. Process All Employees (Demo Polymorphism)");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        viewAllEmployees();
                        break;
                    case 3:
                        viewEmployeeById();
                        break;
                    case 4:
                        updateEmployee();
                        break;
                    case 5:
                        deleteEmployee();
                        break;
                    case 6:
                        processAllEmployees();
                        break;
                    case 7:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void addEmployee() throws SQLException {
        System.out.println("\nAdd New Employee");
        System.out.println("1. Regular Employee");
        System.out.println("2. Developer");
        System.out.println("3. Manager");
        System.out.print("Enter employee type: ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        Employee employee = null;
        
        switch (type) {
            case 1:
                System.out.print("Enter department: ");
                String department = scanner.nextLine();
                employee = new RegularEmployee(0, name, email, salary, department);
                break;
            case 2:
                System.out.print("Enter programming language: ");
                String language = scanner.nextLine();
                employee = new Developer(0, name, email, salary, language);
                break;
            case 3:
                System.out.print("Enter department: ");
                String mgrDept = scanner.nextLine();
                System.out.print("Enter team size: ");
                int teamSize = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                employee = new Manager(0, name, email, salary, mgrDept, teamSize);
                break;
            default:
                System.out.println("Invalid employee type");
                return;
        }
        
        service.addEmployee(employee);
        System.out.println("Employee added successfully!");
        System.out.println("Assigned ID: " + employee.getId());
    }

    private static void viewAllEmployees() throws SQLException {
        System.out.println("\nAll Employees:");
        List<Employee> employees = service.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }

    private static void viewEmployeeById() throws SQLException {
        System.out.print("\nEnter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Employee employee = service.getEmployee(id);
        if (employee != null) {
            System.out.println("\nEmployee Details:");
            System.out.println(employee);
            
            // Demonstrate polymorphism
            System.out.println("\nRole-specific information:");
            System.out.println("Role: " + employee.getRole());
            
            if (employee instanceof Developer) {
                Developer dev = (Developer) employee;
                System.out.println("Programming Language: " + dev.getProgrammingLanguage());
            } else if (employee instanceof Manager) {
                Manager mgr = (Manager) employee;
                System.out.println("Department: " + mgr.getDepartment());
                System.out.println("Team Size: " + mgr.getTeamSize());
            } else if (employee instanceof RegularEmployee) {
                RegularEmployee reg = (RegularEmployee) employee;
                System.out.println("Department: " + reg.getDepartment());
            }
        } else {
            System.out.println("Employee not found with ID: " + id);
        }
    }

    private static void updateEmployee() throws SQLException {
        System.out.print("\nEnter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Employee employee = service.getEmployee(id);
        if (employee == null) {
            System.out.println("Employee not found with ID: " + id);
            return;
        }
        
        System.out.println("\nCurrent Employee Details:");
        System.out.println(employee);
        
        System.out.print("\nEnter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            employee.setName(name);
        }
        
        System.out.print("Enter new email (leave blank to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            employee.setEmail(email);
        }
        
        System.out.print("Enter new salary (0 to keep current): ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (salary > 0) {
            employee.setSalary(salary);
        }
        
        // Type-specific updates
        if (employee instanceof Developer) {
            Developer dev = (Developer) employee;
            System.out.print("Enter new programming language (leave blank to keep current): ");
            String language = scanner.nextLine();
            if (!language.isEmpty()) {
                dev.setProgrammingLanguage(language);
            }
        } else if (employee instanceof Manager) {
            Manager mgr = (Manager) employee;
            System.out.print("Enter new department (leave blank to keep current): ");
            String dept = scanner.nextLine();
            if (!dept.isEmpty()) {
                mgr.setDepartment(dept);
            }
            System.out.print("Enter new team size (-1 to keep current): ");
            int teamSize = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (teamSize >= 0) {
                mgr.setTeamSize(teamSize);
            }
        } else if (employee instanceof RegularEmployee) {
            RegularEmployee reg = (RegularEmployee) employee;
            System.out.print("Enter new department (leave blank to keep current): ");
            String dept = scanner.nextLine();
            if (!dept.isEmpty()) {
                reg.setDepartment(dept);
            }
        }
        
        service.updateEmployee(employee);
        System.out.println("Employee updated successfully!");
    }

    private static void deleteEmployee() throws SQLException {
        System.out.print("\nEnter employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Employee employee = service.getEmployee(id);
        if (employee == null) {
            System.out.println("Employee not found with ID: " + id);
            return;
        }
        
        System.out.println("\nEmployee to be deleted:");
        System.out.println(employee);
        
        System.out.print("Are you sure you want to delete this employee? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            service.deleteEmployee(id);
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void processAllEmployees() throws SQLException {
        System.out.println("\nProcessing all employees (demonstrating polymorphism):");
        service.processAllEmployees();
    }
}