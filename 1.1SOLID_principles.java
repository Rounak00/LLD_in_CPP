/*
-->  - This is called has a relation
*/

// Single responsibility principle

//assume if invoice class does have all logics then that was bad
// a class should have only one reason to save that is should have only one responsibility

// Class 1: Only holds invoice data
class Invoice {
    private String customer;
    private double amount;

    public Invoice(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public String getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }
}

// Class 2: Only does business logic (e.g., tax calculation)
class InvoiceCalculator {
    public static double calculateTotalWithTax(Invoice invoice) {
        return invoice.getAmount() * 1.18; // 18% GST
    }
}

// Class 3: Only does persistence (save to file)
class InvoiceSaver {
    public static void saveToFile(Invoice invoice, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Customer: " + invoice.getCustomer() + "\n");
            writer.write("Amount: " + invoice.getAmount() + "\n");
        } catch (IOException e) {
            System.out.println("Failed to write to file: " + filename);
            e.printStackTrace();
        }
    }
}

// Main class to use the above classes
public class Main {
    public static void main(String[] args) {
        Invoice invoice = new Invoice("Rounak", 1000.0);

        double total = InvoiceCalculator.calculateTotalWithTax(invoice);
        System.out.println("Total with tax: " + total);

        InvoiceSaver.saveToFile(invoice, "invoice.txt");
    }
}




//Open close principle - open for extention but close for modification

//at first invoice saver only have the save to DB functionality but then when we need saveto file functionality then we have to extend it and also cant modify on that class

// 1. Invoice class
class Invoice {
    // Your invoice data and methods
}

// 2. Abstract base class (interface)
interface InvoiceSaver {
    void save(Invoice invoice);
}

// 3. Save to DB
class SaveToDB implements InvoiceSaver {
    @Override
    public void save(Invoice invoice) {
        // Save invoice to DB
    }
}

// 4. Save to File
class SaveToFile implements InvoiceSaver {
    @Override
    public void save(Invoice invoice) {
        // Save invoice to file
    }
}

// 5. Usage
class Main {
    static void processInvoice(InvoiceSaver saver, Invoice invoice) {
        saver.save(invoice);
    }
}



// Liskov Substitution Principle
/*
 A child should be able to use all functions of its parent
*/

// ===== Wrong Way =====

// Abstract interface
interface Bike {
    void accelerate();
    void turnOnEngine();
}

// ✅ Subclass 1: Motorcycle
class Motorcycle implements Bike {
    @Override
    public void accelerate() {
        System.out.println("Motorcycle is accelerating!");
    }

    @Override
    public void turnOnEngine() {
        System.out.println("Motorcycle engine is turned on!");
    }
}

// ❌ Subclass 2: Bicycle (Problem here)
class Bicycle implements Bike {
    @Override
    public void accelerate() {
        System.out.println("Bicycle is pedaling!");
    }

    @Override
    public void turnOnEngine() {
        throw new UnsupportedOperationException("Bicycle doesn't have an engine!");
    }
}

// ===== Right Way =====

// Smaller interface: BasicBike
interface BasicBike {
    void accelerate();
}

// Extended interface: EngineBike
interface EngineBike extends BasicBike {
    void turnOnEngine();
}

class MotorcycleV2 implements EngineBike {
    @Override
    public void accelerate() {
        System.out.println("Motorcycle is accelerating!");
    }

    @Override
    public void turnOnEngine() {
        System.out.println("Motorcycle engine is turned on!");
    }
}

class BicycleV2 implements BasicBike {
    @Override
    public void accelerate() {
        System.out.println("Bicycle is pedaling!");
    }
}





// Interface Segregation Principle
/* Break interfaces into small pieces so that a client (subclass/child) 
   doesn't need to use unnecessary functions */

// ===== Wrong Way =====
interface RestaurantEmp {
    void cook();
    void serve();
    void washDishes();
}

// ❌ Waiter is forced to implement unused methods
class Waiter implements RestaurantEmp {
    @Override
    public void cook() {
        // Not applicable
        System.out.println("Waiter doesn't cook!");
    }

    @Override
    public void serve() {
        System.out.println("Waiter is serving food.");
    }

    @Override
    public void washDishes() {
        // Not applicable
        System.out.println("Waiter doesn't wash dishes!");
    }
}

// ===== Right Way =====
interface Cookable {
    void cook();
}

interface Servable {
    void serve();
}

interface Washable {
    void washDishes();
}







// Dependency Inversion Principle
/*
   Classes should depend on interfaces rather than concrete classes
*/

// Interfaces
interface Keyboard {
    void connect();
}

interface Mouse {
    void connect();
}

// Implementations
class WiredKeyboard implements Keyboard {
    @Override
    public void connect() {
        System.out.println("Wired Keyboard connected.");
    }
}

class BluetoothKeyboard implements Keyboard {
    @Override
    public void connect() {
        System.out.println("Bluetooth Keyboard connected.");
    }
}

class WiredMouse implements Mouse {
    @Override
    public void connect() {
        System.out.println("Wired Mouse connected.");
    }
}

class BluetoothMouse implements Mouse {
    @Override
    public void connect() {
        System.out.println("Bluetooth Mouse connected.");
    }
}

// High-level module
class MacBook {
    private Keyboard keyboard;
    private Mouse mouse;

    // Constructor injection
    public MacBook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public void connectDevices() {
        keyboard.connect();
        mouse.connect();
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        WiredKeyboard wk = new WiredKeyboard();
        BluetoothMouse bm = new BluetoothMouse();

        MacBook mac = new MacBook(wk, bm); // Injecting dependencies
        mac.connectDevices();
    }
}
