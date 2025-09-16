import java.util.*;

// 1. Component Interface — common interface for both leaf and composite
interface Employee {
    void showDetails();
}

// 2. Leaf — represents individual objects (no child elements)
class Developer implements Employee {
    private String name;
    private String role;

    public Developer(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public void showDetails() {
        System.out.println("Developer: " + name + ", Role: " + role);
    }
}

class Designer implements Employee {
    private String name;
    private String role;

    public Designer(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public void showDetails() {
        System.out.println("Designer: " + name + ", Role: " + role);
    }
}

// 3. Composite — can hold leaf objects or other composites
class CompanyDirectory implements Employee {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public void removeEmployee(Employee emp) {
        employees.remove(emp);
    }

    @Override
    public void showDetails() {
        for (Employee emp : employees) {
            emp.showDetails();
        }
    }
}

// 4. Client
public class CompositePatternExample {
    public static void main(String[] args) {
        // Leaf objects
        Employee dev1 = new Developer("Rounak", "Backend Developer");
        Employee dev2 = new Developer("Sunanda", "Frontend Developer");
        Employee des1 = new Designer("Amit", "UI/UX Designer");

        // Composite for developer team
        CompanyDirectory devDirectory = new CompanyDirectory();
        devDirectory.addEmployee(dev1);
        devDirectory.addEmployee(dev2);

        // Composite for designer team
        CompanyDirectory designDirectory = new CompanyDirectory();
        designDirectory.addEmployee(des1);

        // Main company directory holding both teams
        CompanyDirectory companyDirectory = new CompanyDirectory();
        companyDirectory.addEmployee(devDirectory);
        companyDirectory.addEmployee(designDirectory);

        // Client treats everything the same way
        companyDirectory.showDetails();
    }
}
//output
// Developer: Rounak, Role: Backend Developer
// Developer: Sunanda, Role: Frontend Developer
// Designer: Amit, Role: UI/UX Designer
