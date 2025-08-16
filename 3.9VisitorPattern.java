//so for add new functionalities dont harm the class

// Step 1: Define the Visitor interface
interface Visitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

// Step 2: Define the Element interface
interface Shape {
    void accept(Visitor visitor);
}

// Step 3: Concrete Elements
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() { return radius; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Step 4: Concrete Visitor
class AreaCalculator implements Visitor {
    @Override
    public void visit(Circle circle) {
        double area = Math.PI * circle.getRadius() * circle.getRadius();
        System.out.println("Circle area: " + area);
    }

    @Override
    public void visit(Rectangle rectangle) {
        double area = rectangle.getWidth() * rectangle.getHeight();
        System.out.println("Rectangle area: " + area);
    }
}

// Step 5: Client
public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6)
        };

        Visitor areaCalculator = new AreaCalculator();

        for (Shape shape : shapes) {
            shape.accept(areaCalculator); // Visitor calculates area
        }
    }
}
