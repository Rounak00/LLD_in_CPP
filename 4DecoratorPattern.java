// Component interface
interface Pizza {
    String getDescription();
    double getCost();
}

// Concrete Component 1
class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200.0;
    }
}

// Concrete Component 2
class FarmhousePizza implements Pizza {
    @Override
    public String getDescription() {
        return "Farmhouse Pizza";
    }

    @Override
    public double getCost() {
        return 300.0;
    }
}

// Decorator (Abstract)
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza; // reference to wrapped object

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}

// Concrete Decorator 1
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 50.0;
    }
}

// Concrete Decorator 2
class OliveDecorator extends PizzaDecorator {
    public OliveDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 40.0;
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        // Base pizza
        Pizza pizza1 = new MargheritaPizza();
        System.out.println(pizza1.getDescription() + " | Cost: " + pizza1.getCost());

        // Margherita + Cheese
        Pizza pizza2 = new CheeseDecorator(new MargheritaPizza());
        System.out.println(pizza2.getDescription() + " | Cost: " + pizza2.getCost());

        // Farmhouse + Cheese + Olives
        Pizza pizza3 = new OliveDecorator(new CheeseDecorator(new FarmhousePizza()));
        System.out.println(pizza3.getDescription() + " | Cost: " + pizza3.getCost());
    }
}

//Shreyansh approach
// Component interface
interface Pizza {
    String getDescription();
    double getCost();
}

// Concrete Component
class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200.0;
    }
}

// Decorator interface (optional, can just use Pizza)
interface PizzaDecorator extends Pizza {}

// Concrete Decorator 1
class CheeseDecorator implements PizzaDecorator {
    private Pizza pizza;

    public CheeseDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 50.0;
    }
}

// Concrete Decorator 2
class OliveDecorator implements PizzaDecorator {
    private Pizza pizza;

    public OliveDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 40.0;
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        // Base pizza
        Pizza pizza1 = new MargheritaPizza();
        System.out.println(pizza1.getDescription() + " | Cost: " + pizza1.getCost());

        // Margherita + Cheese
        Pizza pizza2 = new CheeseDecorator(new MargheritaPizza());
        System.out.println(pizza2.getDescription() + " | Cost: " + pizza2.getCost());

        // Margherita + Cheese + Olives
        Pizza pizza3 = new OliveDecorator(new CheeseDecorator(new MargheritaPizza()));
        System.out.println(pizza3.getDescription() + " | Cost: " + pizza3.getCost());
    }
}
