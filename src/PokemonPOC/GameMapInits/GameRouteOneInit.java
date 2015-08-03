package PokemonPOC.GameMapInits;

import PokemonPOC.GameEntities.GameStaticEntity;
import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by guy on 8/2/15.
 * Initialises route 1 for the proof of concept.
 */
public class GameRouteOneInit extends GameMapInit {
    public void initCollision(GameWorld world) {
    }

    public void initMap(GameWorld world) throws SlickException {
        Image routeOne = new Image("assets/route_1.png");

        world.entities.add(new GameStaticEntity(0, -640, 0, routeOne));
    }

    public void initNpcs(GameWorld world) throws SlickException {
    }

    public void initTopSprites(GameWorld world) throws SlickException {
    }
}
