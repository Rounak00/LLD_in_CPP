// Vehicle interface
public interface Vehicle {
    int getTankCap();
    int getSeatCap();
}

// Real object
class Car implements Vehicle {
    @Override
    public int getTankCap() {
        return 40;
    }

    @Override
    public int getSeatCap() {
        return 5;
    }
}

// Null object
class NullVehicle implements Vehicle {
    @Override
    public int getTankCap() {
        return 0;
    }

    @Override
    public int getSeatCap() {
        return 0;
    }
}

// Factory that returns either a real or null object
class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if ("car".equalsIgnoreCase(type)) {
            return new Car();
        }
        return new NullVehicle(); // null object instead of returning null
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.getVehicle("car");
        Vehicle bike = VehicleFactory.getVehicle("bike"); // Will get NullVehicle

        System.out.println("Car Tank Capacity: " + car.getTankCap());
        System.out.println("Car Seat Capacity: " + car.getSeatCap());

        System.out.println("Bike Tank Capacity: " + bike.getTankCap());
        System.out.println("Bike Seat Capacity: " + bike.getSeatCap());
    }
}
