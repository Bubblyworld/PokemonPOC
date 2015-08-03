package PokemonPOC;

import PokemonPOC.GameCore.GameWorld;
import PokemonPOC.GameMapInits.GameMapInit;
import PokemonPOC.GameMapInits.GamePalletTownInit;
import PokemonPOC.GameMapInits.GameRouteOneInit;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

/*
Models the state where the player is walking around in the various
in-game areas.
*/
public class GameWalkingState extends BasicGameState {
    //PokemonPOC.Game world data.
    GameWorld world;

    public void init(GameContainer container, StateBasedGame game) {
        world = new GameWorld(container);

        //Load pallet town and route one for the proof-of-concept.
        List<GameMapInit> mapInits = new ArrayList<>();
        mapInits.add(new GamePalletTownInit());
        mapInits.add(new GameRouteOneInit());
        world.enterMaps(mapInits);
    }

    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        world.render(container, game, graphics);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
        world.update(container, game, delta);
    }

    public void enter(GameContainer container, StateBasedGame game) {
    }

    public void leave(GameContainer container, StateBasedGame game) {
    }

    public int getID() {
        return Constants.GAME_WALKING_STATE_ID;
    }
}
