/*
Parking Spot -> 
1. Parking Spot - id, type, price, vehicle obj, parkVehi(), removeVehi() with is a 2 and 4 wheeler spot
2. ParkingSpot Manager - List of parlings, constructr make the dynamic list, find parking place, add parkign space, remove         
                         parking space, park vehi(), remove vehi(), has a with parking spot as have the list

   it will have 2 wheeler and 4 wheeler manager
*/

// Basic SDE 1 version only 
// Vehicle types
enum VehicleType { BIKE, CAR, TRUCK; }

// Vehicle class
class Vehicle {
    private String plateNumber;
    private VehicleType type;

    public Vehicle(String plateNumber, VehicleType type) {
        this.plateNumber = plateNumber;
        this.type = type;
    }

    public VehicleType getType() { return type; }
    public String getPlateNumber() { return plateNumber; }
}

// ParkingSpot
class ParkingSpot {
    private int id;
    private VehicleType type;
    private boolean isFree = true;
    private Vehicle parkedVehicle;

    public ParkingSpot(int id, VehicleType type) {
        this.id = id;
        this.type = type;
    }

    public boolean isFree() { return isFree; }
    public VehicleType getType() { return type; }

    public void park(Vehicle v) {
        if (!isFree) throw new RuntimeException("Spot already occupied");
        if (v.getType() != type) throw new RuntimeException("Wrong spot type");
        this.parkedVehicle = v;
        this.isFree = false;
    }

    public void free() {
        this.parkedVehicle = null;
        this.isFree = true;
    }

    public int getId() { return id; }
}

// Ticket
class Ticket {
    private int ticketId;
    private int spotId;
    private String vehiclePlate;

    public Ticket(int ticketId, int spotId, String vehiclePlate) {
        this.ticketId = ticketId;
        this.spotId = spotId;
        this.vehiclePlate = vehiclePlate;
    }

    public int getSpotId() { return spotId; }
    public int getTicketId() { return ticketId; }
}

// ParkingLot
class ParkingLot {
    private List<ParkingSpot> spots = new ArrayList<>();
    private int ticketCounter = 0;

    public ParkingLot() {
        // Example: 2 spots per type
        spots.add(new ParkingSpot(1, VehicleType.BIKE));
        spots.add(new ParkingSpot(2, VehicleType.BIKE));
        spots.add(new ParkingSpot(3, VehicleType.CAR));
        spots.add(new ParkingSpot(4, VehicleType.CAR));
        spots.add(new ParkingSpot(5, VehicleType.TRUCK));
    }

    public Ticket parkVehicle(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.isFree() && s.getType() == v.getType()) {
                s.park(v);
                return new Ticket(++ticketCounter, s.getId(), v.getPlateNumber());
            }
        }
        throw new RuntimeException("No spot available for " + v.getType());
    }

    public void unparkVehicle(Ticket t) {
        for (ParkingSpot s : spots) {
            if (s.getId() == t.getSpotId()) {
                s.free();
                System.out.println("Vehicle with plate " + t.getSpotId() + " left spot " + s.getId());
                return;
            }
        }
    }

    public void displayAvailability() {
        for (ParkingSpot s : spots) {
            System.out.println("Spot " + s.getId() + " (" + s.getType() + ") -> " + (s.isFree() ? "FREE" : "OCCUPIED"));
        }
    }
}

// Demo
public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();
        lot.displayAvailability();

        Vehicle car = new Vehicle("WB12AB1234", VehicleType.CAR);
        Ticket t1 = lot.parkVehicle(car);
        System.out.println("Car parked with ticket: " + t1.getTicketId());

        lot.displayAvailability();

        lot.unparkVehicle(t1);
        lot.displayAvailability();
    }
}
