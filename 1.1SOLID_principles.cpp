/*
-->  - This is called has a relation
*/

// Single responsibility principle

//assume if invoice class does have all logics then that was bad
// a class should have only one reason to save that is should have only one responsibility

// Class 1: Only holds invoice data
class Invoice {
    std::string customer;
    double amount;
public:
    Invoice(const std::string& cust, double amt)
        : customer(cust), amount(amt) {}

    std::string getCustomer() const { return customer; }
    double getAmount() const { return amount; }
};

// Class 2: Only does business logic (e.g., tax calculation)
class InvoiceCalculator {
public:
    static double calculateTotalWithTax(const Invoice& invoice) {
        return invoice.getAmount() * 1.18; // 18% GST
    }
};

// Class 3: Only does persistence (save to file)
class InvoiceSaver {
public:
    static void saveToFile(const Invoice& invoice, const std::string& filename) {
        std::ofstream out(filename);
        if (!out) {
            std::cerr << "Failed to open file: " << filename << "\n";
            return;
        }
        out << "Customer: " << invoice.getCustomer() << "\n";
        out << "Amount: " << invoice.getAmount() << "\n";
        out.close();
    }
};

// Main function: Use the classes
int main() {
    Invoice invoice("Rounak", 1000.0);

    double total = InvoiceCalculator::calculateTotalWithTax(invoice);
    std::cout << "Total with tax: " << total << "\n";

    InvoiceSaver::saveToFile(invoice, "invoice.txt");

    return 0;
}



//Open close principle - open for extention but close for modification

//at first invoice saver only have the save to DB functionality but then when we need saveto file functionality then we have to extend it and also cant modify on that class

// 1. Invoice class
class Invoice {
    // Your invoice data and methods
};

// 2. Abstract base class (interface)
class InvoiceSaver {
public:
    virtual void save(const Invoice& invoice) = 0;
    virtual ~InvoiceSaver() = default;
};

// 3. Save to DB
class SaveToDB : public InvoiceSaver {
public:
    void save(const Invoice& invoice) override {
        // Save invoice to DB
    }
};

// 4. Save to File
class SaveToFile : public InvoiceSaver {
public:
    void save(const Invoice& invoice) override {
        // Save invoice to file
    }
};

// 5. Usage
void processInvoice(InvoiceSaver& saver, const Invoice& invoice) {
    saver.save(invoice);
}


//Liskov Substitution
/*
 A child should have use all function of its parent 
*/
// Abstract interface
class Bike {
public:
    virtual void accelerate() = 0;
    virtual void turnOnEngine() = 0;
    virtual ~Bike() = default;
};

// ✅ Subclass 1: Motorcycle
class Motorcycle : public Bike {
public:
    void accelerate() override {
        std::cout << "Motorcycle is accelerating!" << std::endl;
    }

    void turnOnEngine() override {
        std::cout << "Motorcycle engine is turned on!" << std::endl;
    }
};

// ❌ Subclass 2: Bicycle (Problem here)
class Bicycle : public Bike {
public:
    void accelerate() override {
        std::cout << "Bicycle is pedaling!" << std::endl;
    }

    void turnOnEngine() override {
        throw std::logic_error("Bicycle doesn't have an engine!");
    }
};


// =====> Right Way
// Smaller interface: BasicBike
class BasicBike {
public:
    virtual void accelerate() = 0;
    virtual ~BasicBike() = default;
};

// Extended interface: EngineBike
class EngineBike : public BasicBike {
public:
    virtual void turnOnEngine() = 0;
};

class Motorcycle : public EngineBike {
public:
    void accelerate() override {
        std::cout << "Motorcycle is accelerating!" << std::endl;
    }

    void turnOnEngine() override {
        std::cout << "Motorcycle engine is turned on!" << std::endl;
    }
};

class Bicycle : public BasicBike {
public:
    void accelerate() override {
        std::cout << "Bicycle is pedaling!" << std::endl;
    }
};


// Interface segmented principle
/*break interfaces in small pices so that client(sub class/ child) no need to use unnecessery functions*/

class RestaurantEmp {
public:
    virtual void cook() = 0;
    virtual void serve() = 0;
    virtual void washDishes() = 0;
    virtual ~RestaurantEmp() = default;
};

// ❌ Waiter is forced to implement unused methods
class Waiter : public RestaurantEmp {
public:
    void cook() override {
        // Not applicable
        std::cout << "Waiter doesn't cook!\n";
    }

    void serve() override {
        std::cout << "Waiter is serving food.\n";
    }

    void washDishes() override {
        // Not applicable
        std::cout << "Waiter doesn't wash dishes!\n";
    }
};


// now the proper way , break the interface
class Cookable {
public:
    virtual void cook() = 0;
    virtual ~Cookable() = default;
};

class Servable {
public:
    virtual void serve() = 0;
    virtual ~Servable() = default;
};

class Washable {
public:
    virtual void washDishes() = 0;
    virtual ~Washable() = default;
};

//Dependency inversion principle

/*
class should dependent on interface rather than concrete class
*/

// Interfaces / abstract class
class Keyboard {
public:
    virtual void connect() = 0;
    virtual ~Keyboard() {}
};

class Mouse {
public:
    virtual void connect() = 0;
    virtual ~Mouse() {}
};

// Implementations
class WiredKeyboard : public Keyboard {
public:
    void connect() override {
        cout << "Wired Keyboard connected.\n";
    }
};

class BluetoothKeyboard : public Keyboard {
public:
    void connect() override {
        cout << "Bluetooth Keyboard connected.\n";
    }
};

class WiredMouse : public Mouse {
public:
    void connect() override {
        cout << "Wired Mouse connected.\n";
    }
};

class BluetoothMouse : public Mouse {
public:
    void connect() override {
        cout << "Bluetooth Mouse connected.\n";
    }
};

// High-level module
class MacBook {
    Keyboard& keyboard;
    Mouse& mouse;
public:
    MacBook(Keyboard& k, Mouse& m) : keyboard(k), mouse(m) {}

    void connectDevices() {
        keyboard.connect();
        mouse.connect();
    }
};

// Main
int main() {
    WiredKeyboard wk;
    BluetoothMouse bm;

    MacBook mac(wk, bm);  // Injecting references
    mac.connectDevices();

    return 0;
}

//pass by address

// // MacBook depends on abstractions, not on concrete classes
// class MacBook {
//     Keyboard* keyboard;
//     Mouse* mouse;

// public:
//     MacBook(Keyboard* k, Mouse* m) : keyboard(k), mouse(m) {}

//     void work() {
//         keyboard->type();
//         mouse->click();
//     }
// };

// // Main function
// int main() {
//     BluetoothKeyboard bk;
//     WiredMouse wm;

//     MacBook mac(&bk, &wm);
//     mac.work();

//     return 0;
// }