// Employee.java (Abstract class)
public abstract class Employee {
    protected int id;
    protected String name;
    protected String email;
    protected double salary;
    
    public Employee(int id, String name, String email, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
    }
    
    // Abstract method
    public abstract String getRole();
    
    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(double salary) { this.salary = salary; }
    
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Email: %s, Salary: %.2f, Role: %s", 
            id, name, email, salary, getRole());
    }
}