// 1. Target Interface — client expects weight in KG (Adaptee)
interface WeightMachine {
    double getWeightInKg();
}

// 2. Adaptee — existing class that returns weight in pounds
class WeightMachineInPound {
    public double getWeightInPound() {
        return 150.0; // Example: returns 150 pounds
    }
}

// 3. Adapter — converts pounds to kilograms
class WeightMachineAdapter implements WeightMachine {
    private WeightMachineInPound weightMachineInPound;

    public WeightMachineAdapter(WeightMachineInPound weightMachineInPound) {
        this.weightMachineInPound = weightMachineInPound;
    }

    @Override
    public double getWeightInKg() {
        double pound = weightMachineInPound.getWeightInPound();
        return pound * 0.453592; // 1 pound = 0.453592 kg
    }
}

// 4. Client
public class AdapterPatternExample {
    public static void main(String[] args) {
        // Existing weight machine (in pounds)
        WeightMachineInPound machineInPound = new WeightMachineInPound();

        // Use adapter to get weight in KG
        WeightMachine machineInKg = new WeightMachineAdapter(machineInPound);

        System.out.println("Weight in KG: " + machineInKg.getWeightInKg());
    }
}
