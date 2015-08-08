package PokemonPOC;

import PokemonPOC.Core.World;
import PokemonPOC.MapInits.MapInit;
import PokemonPOC.MapInits.PalletTownInit;
import PokemonPOC.MapInits.RouteOneInit;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
    World world;

    public void init(GameContainer container, StateBasedGame game) {
        world = new World(container);

        //Load pallet town and route one for the proof-of-concept.
        List<MapInit> mapInits = new ArrayList<>();
        mapInits.add(new PalletTownInit(96, 128));
        mapInits.add(new RouteOneInit());
        world.enterMaps(mapInits);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        world.render(container, game, graphics);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        world.update(container, game, delta);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        //Give us a clean input slate.
        world.playerInput.clearMousePressedRecord();
        world.playerInput.clearKeyPressedRecord();
    }

    @Override
    public int getID() {
        return Constants.GAME_WALKING_STATE_ID;
    }
}
