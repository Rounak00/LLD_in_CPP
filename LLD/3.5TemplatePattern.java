//All classes have a specific steps to perform but can have own 

// Abstract Class - Template
abstract class Beverage {
    // Template method (final so subclasses can't override it)
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    private void boilWater() {
        System.out.println("Boiling water 🔥");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup ☕");
    }

    // Steps to be implemented by subclasses
    protected abstract void brew();
    protected abstract void addCondiments();
}

// Concrete Class - Tea
class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Steeping the tea 🍵");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon 🍋");
    }
}

// Concrete Class - Coffee
class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Dripping coffee through filter ☕");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk 🥛");
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        System.out.println("Making Tea:");
        Beverage tea = new Tea();
        tea.prepareRecipe();

        System.out.println("\nMaking Coffee:");
        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
