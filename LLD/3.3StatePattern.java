// 1. State Interface — defines the common behavior for all states
interface State {
    void pressButton();
}

// 2. Concrete State: TV is ON
class OnState implements State {
    @Override
    public void pressButton() {
        System.out.println("TV is turning OFF...");
    }
}

// 3. Concrete State: TV is OFF
class OffState implements State {
    @Override
    public void pressButton() {
        System.out.println("TV is turning ON...");
    }
}

// 4. Context Class — holds the current state and delegates behavior to it
class TVRemote {
    private State currentState; // reference to current state

    // Constructor: set initial state
    public TVRemote(State initialState) {
        this.currentState = initialState;
    }

    // Change the state
    public void setState(State state) {
        this.currentState = state;
    }

    // Delegate the action to the current state's implementation
    public void pressButton() {
        currentState.pressButton();

        // Change state after performing action
        if (currentState instanceof OnState) {
            setState(new OffState());
        } else {
            setState(new OnState());
        }
    }
}

// 5. Main Class — to test the pattern
public class StatePatternExample {
    public static void main(String[] args) {
        // TV starts in OFF state
        TVRemote remote = new TVRemote(new OffState());

        // Pressing the button will toggle the state each time
        remote.pressButton(); // TV is turning ON...
        remote.pressButton(); // TV is turning OFF...
        remote.pressButton(); // TV is turning ON...
    }
}
