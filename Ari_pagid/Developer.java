// Developer.java
public class Developer extends Employee {
    private String programmingLanguage;
    
    public Developer(int id, String name, String email, double salary, String programmingLanguage) {
        super(id, name, email, salary);
        this.programmingLanguage = programmingLanguage;
    }
    
    @Override
    public String getRole() {
        return "Developer";
    }
    
    public String getProgrammingLanguage() { return programmingLanguage; }
    public void setProgrammingLanguage(String language) { this.programmingLanguage = language; }
    
    @Override
    public String toString() {
        return super.toString() + ", Programming Language: " + programmingLanguage;
    }
}