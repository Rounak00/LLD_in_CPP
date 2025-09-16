// Employee interface
public interface Employee {
    void create(String client, Employee obj);
    void delete(String client, int employeeID);
    Employee get(String client, int employeeID);
}

// Actual Employee implementation
class EmployeeWork implements Employee {
    @Override
    public void create(String client, Employee obj) {
        System.out.println("Create the user");
    }

    @Override
    public void delete(String client, int employeeID) {
        System.out.println("Delete the user");
    }

    @Override
    public Employee get(String client, int employeeID) {
        System.out.println("Get the user");
        return this; // Returning current instance for simplicity
    }
}

// Proxy class
class EmployeeProxy implements Employee {
    private EmployeeWork e;

    public EmployeeProxy() {
        e = new EmployeeWork();
    }

    @Override
    public void create(String client, Employee obj) {
        if (client.equalsIgnoreCase("ADMIN")) {
            e.create(client, obj);
        } else {
            System.out.println("Access Denied: Only ADMIN can create users.");
        }
    }

    @Override
    public void delete(String client, int employeeID) {
        if (client.equalsIgnoreCase("ADMIN")) {
            e.delete(client, employeeID);
        } else {
            System.out.println("Access Denied: Only ADMIN can delete users.");
        }
    }

    @Override
    public Employee get(String client, int employeeID) {
        // Anyone can get details
        return e.get(client, employeeID);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Employee empProxy = new EmployeeProxy();

        // Trying with normal user
        empProxy.create("USER", new EmployeeWork());  
        empProxy.delete("USER", 101);   

        // Trying with admin
        empProxy.create("ADMIN", new EmployeeWork()); 
        empProxy.delete("ADMIN", 101);  

        // Get details (allowed for all)
        empProxy.get("USER", 101);
    }
}
