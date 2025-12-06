package behavioral.state;

public class MenuState implements GameState {
    @Override
    public void handleInput() {
        System.out.println("Menu State: Handling Input (Start Game, etc.)");
    }

    @Override
    public void update() {
        System.out.println("Menu State: Updating UI");
    }
}
