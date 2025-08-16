//It have three parts  -> Invoker Reciever and Command
import java.util.Stack;

// Command Interface
interface ICommand {
    void execute();
    void undo();
}

// Receiver
class AC {
    private boolean isOn = false;

    public void turnOn() {
        isOn = true;
        System.out.println("AC is turned ON ❄️");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("AC is turned OFF 📴");
    }
}

// Concrete Command: Turn On
class TurnOnCommand implements ICommand {
    private AC ac;

    public TurnOnCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOn();
    }

    @Override
    public void undo() {
        ac.turnOff();
    }
}

// Concrete Command: Turn Off
class TurnOffCommand implements ICommand {
    private AC ac;

    public TurnOffCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOff();
    }

    @Override
    public void undo() {
        ac.turnOn();
    }
}

// Invoker
class Remote {
    private Stack<ICommand> history = new Stack<>();

    public void pressButton(ICommand command) {
        command.execute();
        history.push(command);
    }

    public void undo() {
        if (!history.isEmpty()) {
            ICommand lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        AC ac = new AC();
        ICommand turnOn = new TurnOnCommand(ac);
        ICommand turnOff = new TurnOffCommand(ac);

        Remote remote = new Remote();

        // Perform actions
        remote.pressButton(turnOn);   // AC ON
        remote.pressButton(turnOff);  // AC OFF

        // Undo actions
        remote.undo(); // Undo OFF → AC ON
        remote.undo(); // Undo ON → AC OFF
        remote.undo(); // Nothing left
    }
}
