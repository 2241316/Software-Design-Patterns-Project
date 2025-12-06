package behavioral.state;

public class PauseState implements GameState {
    @Override
    public void handleInput() {
        System.out.println("Pause State: Waiting to Resume");
    }

    @Override
    public void update() {
        System.out.println("Pause State: Game Paused");
    }
}
