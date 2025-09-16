//we can store onbjects history
import java.util.*;

// Memento
class ConfigurationMemento {
    int height;
    int width;

    public ConfigurationMemento(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

// Originator
class ConfigurationOriginator {
    int height;
    int width;

    public ConfigurationOriginator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public ConfigurationMemento createMemento() {
        return new ConfigurationMemento(this.height, this.width);
    }

    public void restoreMemento(ConfigurationMemento mementoToBeRestored) {
        this.height = mementoToBeRestored.getHeight();
        this.width = mementoToBeRestored.getWidth();
    }
}

// Caretaker
class ConfigurationCareTaker {
    List<ConfigurationMemento> history = new ArrayList<>();

    public void addMemento(ConfigurationMemento memento) {
        history.add(memento);
    }

    public ConfigurationMemento undo() {
        if (!history.isEmpty()) {
            int lastMementoIndex = history.size() - 1;
            ConfigurationMemento lastMemento = history.get(lastMementoIndex);
            history.remove(lastMementoIndex);
            return lastMemento;
        }
        return null;
    }
}

// Client
public class MementoPatternDemo {
    public static void main(String[] args) {
        ConfigurationCareTaker careTakerObject = new ConfigurationCareTaker();

        // Initial state
        ConfigurationOriginator originatorObject = new ConfigurationOriginator(5, 10);

        // Save state
        ConfigurationMemento snapshot1 = originatorObject.createMemento();
        careTakerObject.addMemento(snapshot1);

        // Change state
        originatorObject.setHeight(7);
        originatorObject.setWidth(12);

        // Save state
        ConfigurationMemento snapshot2 = originatorObject.createMemento();
        careTakerObject.addMemento(snapshot2);

        // Change again
        originatorObject.setHeight(9);
        originatorObject.setWidth(14);

        // Undo (restore last saved state)
        ConfigurationMemento restoredState = careTakerObject.undo();
        originatorObject.restoreMemento(restoredState);

        System.out.println("Restored State => height: " + originatorObject.height + " width: " + originatorObject.width);
    }
}
