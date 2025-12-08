package behavioral.state;

/**
 * -------------------------------------------------------------
 * DESIGN PATTERN: State (Behavioral)
 * -------------------------------------------------------------
 * This class serves as the Context in the State pattern.
 * It maintains a reference to an instance of a GameState subclass,
 * which represents the current state of the game.
 */
public class StateManager {
    private GameState currentState;

    public void setState(GameState state) {
        this.currentState = state;
    }

    public GameState getState() {
        return currentState;
    }

    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    public void handleInput() {
        if (currentState != null) {
            currentState.handleInput();
        }
    }
}
