public class RegularEmployee extends Employee {
    private String department;
    
    public RegularEmployee(int id, String name, String email, double salary, String department) {
        super(id, name, email, salary);
        this.department = department;
    }
    
    @Override
    public String getRole() {
        return "Regular Employee";
    }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    @Override
    public String toString() {
        return super.toString() + ", Department: " + department;
    }
} 