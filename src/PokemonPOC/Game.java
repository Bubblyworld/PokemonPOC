package PokemonPOC;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
    public Game() {
        super("Pokemon Demo");
    }

    public void initStatesList(GameContainer container) {
        addState(new GameWalkingState());

        enterState(Constants.GAME_WALKING_STATE_ID);
    }
}
