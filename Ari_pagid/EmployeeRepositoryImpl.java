// EmployeeRepositoryImpl.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    
    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (name, email, salary, type, programming_language, department) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getEmail());
            stmt.setDouble(3, employee.getSalary());
            
            if (employee instanceof Developer) {
                Developer dev = (Developer) employee;
                stmt.setString(4, "DEVELOPER");
                stmt.setString(5, dev.getProgrammingLanguage());
                stmt.setNull(6, Types.VARCHAR);
            } else if (employee instanceof Manager) {
                Manager mgr = (Manager) employee;
                stmt.setString(4, "MANAGER");
                stmt.setNull(5, Types.VARCHAR);
                stmt.setString(6, mgr.getDepartment());
            } else {
                RegularEmployee reg = (RegularEmployee) employee;
                stmt.setString(4, "REGULAR");
                stmt.setNull(5, Types.VARCHAR);
                stmt.setString(6, reg.getDepartment());
            }
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.id = rs.getInt(1);
                }
            }
        }
    }
    
    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createEmployeeFromResultSet(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                employees.add(createEmployeeFromResultSet(rs));
            }
        }
        return employees;
    }
    
    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET name = ?, email = ?, salary = ?, programming_language = ?, department = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getEmail());
            stmt.setDouble(3, employee.getSalary());
            
            if (employee instanceof Developer) {
                Developer dev = (Developer) employee;
                stmt.setString(4, dev.getProgrammingLanguage());
                stmt.setNull(5, Types.VARCHAR);
            } else if (employee instanceof Manager) {
                Manager mgr = (Manager) employee;
                stmt.setNull(4, Types.VARCHAR);
                stmt.setString(5, mgr.getDepartment());
            } else {
                RegularEmployee reg = (RegularEmployee) employee;
                stmt.setNull(4, Types.VARCHAR);
                stmt.setString(5, reg.getDepartment());
            }
            
            stmt.setInt(6, employee.getId());
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    private Employee createEmployeeFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        double salary = rs.getDouble("salary");
        String type = rs.getString("type");
        
        switch (type) {
            case "DEVELOPER":
                String language = rs.getString("programming_language");
                return new Developer(id, name, email, salary, language);
            case "MANAGER":
                String dept = rs.getString("department");
                // Note: teamSize isn't stored in DB in this simple example
                return new Manager(id, name, email, salary, dept, 0);
            case "REGULAR":
                String department = rs.getString("department");
                return new RegularEmployee(id, name, email, salary, department);
            default:
                throw new SQLException("Unknown employee type: " + type);
        }
    }
}