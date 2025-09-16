// Abstraction
abstract class LivingBeing {
    protected BreathingImplementor breathingImplementor;

    public LivingBeing(BreathingImplementor breathingImplementor) {
        this.breathingImplementor = breathingImplementor;
    }

    abstract void performBreath();
}

// Refined Abstractions
class Human extends LivingBeing {
    public Human(BreathingImplementor breathingImplementor) {
        super(breathingImplementor);
    }

    @Override
    void performBreath() {
        System.out.print("Human: ");
        breathingImplementor.breathe();
    }
}

class Fish extends LivingBeing {
    public Fish(BreathingImplementor breathingImplementor) {
        super(breathingImplementor);
    }

    @Override
    void performBreath() {
        System.out.print("Fish: ");
        breathingImplementor.breathe();
    }
}


// Implementor
interface BreathingImplementor {
    void breathe();
}

// Concrete Implementors
class HumanBreath implements BreathingImplementor {
    @Override
    public void breathe() {
        System.out.println("Breathing through lungs (air).");
    }
}

class FishBreath implements BreathingImplementor {
    @Override
    public void breathe() {
        System.out.println("Breathing through gills (water).");
    }
}



// Client
public class Main {
    public static void main(String[] args) {
        LivingBeing human = new Human(new HumanBreath());
        human.performBreath();

        LivingBeing fish = new Fish(new FishBreath());
        fish.performBreath();
    }
}

