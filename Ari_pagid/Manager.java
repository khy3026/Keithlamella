public class Manager extends Employee {
    private String department;
    private int teamSize;
    
    public Manager(int id, String name, String email, double salary, String department, int teamSize) {
        super(id, name, email, salary);
        this.department = department;
        this.teamSize = teamSize;
    }
    
    @Override
    public String getRole() {
        return "Manager";
    }
    
    public String getDepartment() { return department; }
    public int getTeamSize() { return teamSize; }
    
    public void setDepartment(String department) { this.department = department; }
    public void setTeamSize(int teamSize) { this.teamSize = teamSize; }
    
    @Override
    public String toString() {
        return super.toString() + ", Department: " + department + ", Team Size: " + teamSize;
    }
} 