package behavioral.state;

// State pattern - manages different game states
public class StateManager {
    private GameState currentState;

    // Switch to new state
    public void setState(GameState state) {
        this.currentState = state;
    }

    // Get current state
    public GameState getState() {
        return currentState;
    }

    // Update current state
    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    // Handle input in current state
    public void handleInput() {
        if (currentState != null) {
            currentState.handleInput();
        }
    }
}
