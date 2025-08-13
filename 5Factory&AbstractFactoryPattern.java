//creation based on condition


//Factory 
public interface Shape{
   void draw();
}

public class Rectangle implements Shape{
    @Override
    public void draw(){
        System.out.println('This is a Rectangle');
    }
}

public class Circle implements Shape{
    @Override
    public void draw(){
        System.out.println('This is a Circle');
    }
}


public class ShapeFactory{
    Shape getShape(string i){
        switch(i){
            case 'CIRCLE': return new Circle();
            case 'RECTANGLE': return new Rectangle();
            default : return null;
        }
    }
}

main() {
    ShapeFactory s=new ShapeFactory();
    Shape obj=s.getShape('CIRCLE');
    obj.draw(); //This is Circle
}


//Abstract Factory Pattern - > Factory of Factories

// 1. Vehicle interface
interface Vehicle {
    void average();
}

// 2. Concrete Vehicle classes
class Luxury1 implements Vehicle {
    public void average() {
        System.out.println("Luxury1 average: 15 km/l");
    }
}

class Luxury2 implements Vehicle {
    public void average() {
        System.out.println("Luxury2 average: 12 km/l");
    }
}

class Ordinary1 implements Vehicle {
    public void average() {
        System.out.println("Ordinary1 average: 25 km/l");
    }
}

class Ordinary2 implements Vehicle {
    public void average() {
        System.out.println("Ordinary2 average: 22 km/l");
    }
}

// 3. Abstract Factory
abstract class VehicleFactory {
    abstract Vehicle getVehicle(String vehicleType);
}

// 4. Concrete Factories
class LuxuryFactory extends VehicleFactory {
    public Vehicle getVehicle(String vehicleType) {
        if (vehicleType == null) return null;
        if (vehicleType.equalsIgnoreCase("Luxury1")) {
            return new Luxury1();
        } else if (vehicleType.equalsIgnoreCase("Luxury2")) {
            return new Luxury2();
        }
        return null;
    }
}

class OrdinaryFactory extends VehicleFactory {
    public Vehicle getVehicle(String vehicleType) {
        if (vehicleType == null) return null;
        if (vehicleType.equalsIgnoreCase("Ordinary1")) {
            return new Ordinary1();
        } else if (vehicleType.equalsIgnoreCase("Ordinary2")) {
            return new Ordinary2();
        }
        return null;
    }
}

// 5. Factory Producer (returns a factory based on type)
class FactoryProducer {
    public static VehicleFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("Luxury")) {
            return new LuxuryFactory();
        } else if (choice.equalsIgnoreCase("Ordinary")) {
            return new OrdinaryFactory();
        }
        return null;
    }
}

// 6. Test the pattern
public class AbstractFactoryExample {
    public static void main(String[] args) {
        
        // Get Luxury Factory
        VehicleFactory luxuryFactory = FactoryProducer.getFactory("Luxury");

        Vehicle lux1 = luxuryFactory.getVehicle("Luxury1");
        lux1.average();

        Vehicle lux2 = luxuryFactory.getVehicle("Luxury2");
        lux2.average();

        // Get Ordinary Factory
        VehicleFactory ordinaryFactory = FactoryProducer.getFactory("Ordinary");

        Vehicle ord1 = ordinaryFactory.getVehicle("Ordinary1");
        ord1.average();

        Vehicle ord2 = ordinaryFactory.getVehicle("Ordinary2");
        ord2.average();
    }
}
