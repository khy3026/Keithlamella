// EmployeeService.java
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeRepository repository;
    
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    
    public void addEmployee(Employee employee) throws SQLException {
        repository.addEmployee(employee);
    }
    
    public Employee getEmployee(int id) throws SQLException {
        return repository.getEmployeeById(id);
    }
    
    public List<Employee> getAllEmployees() throws SQLException {
        return repository.getAllEmployees();
    }
    
    public void updateEmployee(Employee employee) throws SQLException {
        repository.updateEmployee(employee);
    }
    
    public void deleteEmployee(int id) throws SQLException {
        repository.deleteEmployee(id);
    }
    
    // Polymorphic method to process all employees
    public void processAllEmployees() throws SQLException {
        List<Employee> employees = getAllEmployees();
        for (Employee emp : employees) {
            System.out.println("Processing: " + emp.getName());
            System.out.println("Role: " + emp.getRole()); // Polymorphic call
            
            // Specific processing based on type
            if (emp instanceof Developer) {
                Developer dev = (Developer) emp;
                System.out.println("Language: " + dev.getProgrammingLanguage());
            } else if (emp instanceof Manager) {
                Manager mgr = (Manager) emp;
                System.out.println("Department: " + mgr.getDepartment());
            }
            
            System.out.println("------");
        }
    }
}