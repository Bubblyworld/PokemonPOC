package PokemonPOC;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
    public Game() {
        super("Pokemon Demo");
    }

    public void initStatesList(GameContainer container) {
        addState(new GameWalkingState());
        addState(new GameDialogueState());
        addState(new BattleSelectState());

//        enterState(Constants.BATTLE_SELECT_STATE_ID);
        enterState(Constants.GAME_WALKING_STATE_ID);
    }
}
