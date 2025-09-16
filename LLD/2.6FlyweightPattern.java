import java.util.*;

// ---------- Interface ----------
public interface IRobot {
    void display(int x, int y);
}

// ---------- Sprite Class ----------
class Sprites {
    // Let's assume this contains expensive bitmap/graphic data
    public Sprites() {
        System.out.println("Loading sprite from disk...");
    }
}

// ---------- Concrete Flyweights ----------
class HumanoidRobot implements IRobot {
    private final String type;
    private final Sprites body; // shared

    HumanoidRobot(String type, Sprites body) {
        this.type = type;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public Sprites getBody() {
        return body;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Displaying Humanoid Robot at (" + x + "," + y + ")");
    }
}

class RoboticDog implements IRobot {
    private final String type;
    private final Sprites body; // shared

    RoboticDog(String type, Sprites body) {
        this.type = type;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public Sprites getBody() {
        return body;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Displaying Robotic Dog at (" + x + "," + y + ")");
    }
}

// ---------- Flyweight Factory ----------
class RoboticFactory {
    private static final Map<String, IRobot> roboticObjectCache = new HashMap<>();

    public static IRobot createRobot(String robotType) {
        if (roboticObjectCache.containsKey(robotType)) {
            return roboticObjectCache.get(robotType);
        } else {
            if (robotType.equals("HUMANOID")) {
                Sprites humanoidSprite = new Sprites();
                IRobot humanoidObject = new HumanoidRobot(robotType, humanoidSprite);
                roboticObjectCache.put(robotType, humanoidObject);
                return humanoidObject;
            } else if (robotType.equals("ROBOTICDOG")) {
                Sprites roboticDogSprite = new Sprites();
                IRobot roboticDogObject = new RoboticDog(robotType, roboticDogSprite);
                roboticObjectCache.put(robotType, roboticDogObject);
                return roboticDogObject;
            }
        }
        return null;
    }
}

// ---------- Main Client ----------
public class Main {
    public static void main(String[] args) {
        // Create an army of 10 humanoid robots
        for (int i = 0; i < 10; i++) {
            IRobot humanoid = RoboticFactory.createRobot("HUMANOID");
            humanoid.display(i, i + 1);
        }

        System.out.println("----------");

        // Create an army of 5 robotic dogs
        for (int i = 0; i < 5; i++) {
            IRobot dog = RoboticFactory.createRobot("ROBOTICDOG");
            dog.display(i * 2, i * 2 + 1);
        }
    }
}


//Word processor example
// Flyweight interface
public interface ILetter {
    void display(int row, int column);
}

// Concrete Flyweight
public class DocumentCharacter implements ILetter {
    private final char character;   // intrinsic state
    private final String fontType;  // intrinsic state
    private final int size;         // intrinsic state

    DocumentCharacter(char character, String fontType, int size) {
        this.character = character;
        this.fontType = fontType;
        this.size = size;
    }

    // only getter methods
    public char getCharacter() {
        return character;
    }

    public String getFontType() {
        return fontType;
    }

    public int getSize() {
        return size;
    }

    // extrinsic state is row, column
    @Override
    public void display(int row, int column) {
        System.out.println("Displaying '" + character + "' in font " + fontType 
                           + " size " + size + " at (" + row + "," + column + ")");
    }
}

// Flyweight Factory
public class LetterFactory {
    private static final Map<Character, ILetter> characterCache = new HashMap<>();

    public static ILetter createLetter(char characterValue) {
        if (characterCache.containsKey(characterValue)) {
            return characterCache.get(characterValue);
        } else {
            ILetter characterObj = new DocumentCharacter(characterValue, "Arial", 10);
            characterCache.put(characterValue, characterObj);
            return characterObj;
        }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        /*
         Suppose this is the data we want to write into the word processor.
         Total = 58 characters
         't' = 7 times
         'h' = 3 times
         'a' = 3 times and so on...
         */

        ILetter object1 = LetterFactory.createLetter('t');
        object1.display(0, 0);

        ILetter object2 = LetterFactory.createLetter('t');
        object2.display(0, 6);

        ILetter object3 = LetterFactory.createLetter('h');
        object3.display(0, 12);

        ILetter object4 = LetterFactory.createLetter('a');
        object4.display(0, 18);

        System.out.println("object1 == object2 ? " + (object1 == object2)); // true (same cached object)
    }
}
