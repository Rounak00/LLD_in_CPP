//assume drive is a feature and multiple type of srive is there
interface DriveStrategy{
   public void drive();
}

class NormalDrive implements DriveStrategy{
    @Override
    public void drive(){
        System.out.println("This is a normal drive");
    }
}

//vehicle now interface cant have contructor so we will create a concrete class of vehicle
class Vehicle{
    protected DriveStrategy d;
    public Vehicle(DriveStrategy d){
        this.d=d;
    }

    public void drive(){
        d.drive();
    }
}

class NormalCar extends Vehicle{
    public NormalCar(){
        super(new NormalDrive());
    }
}

// Main class to run
public class Main {
    public static void main(String[] args) {
        Vehicle n = new NormalCar();
        n.drive(); // Output: This is a normal drive
    }
}

/*
 Now here we can add multiple type of drives and multiple cars that will support only one type of drive;
 */