package PokemonPOC.GameMapInits;

import PokemonPOC.Constants;
import PokemonPOC.GameEntities.GameAnimatedTriggerEntity;
import PokemonPOC.GameEntities.GameStaticEntity;
import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import sun.text.resources.no.CollationData_no;

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

        //Add grass that animated when you walk through it
        SpriteSheet grassSheet = new SpriteSheet("assets/grass_animation.png", 16, 16);
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 2; x++) {
                world.entities.add(new GameAnimatedTriggerEntity(
                        192 + x * Constants.BLOCK_SIZE,
                        -16 - y * Constants.BLOCK_SIZE,
                        0,
                        2.0f,
                        null,
                        grassSheet
                ));
            }
        }
    }

    public void initNpcs(GameWorld world) throws SlickException {
    }

    public void initTopSprites(GameWorld world) throws SlickException {
        //Add grass sprites?
        Image grassImage = new Image("assets/grass_top.png");
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 2; x++) {
                world.entities.add(new GameStaticEntity(
                        192 + x * Constants.BLOCK_SIZE,
                        -16 - y * Constants.BLOCK_SIZE,
                        2,
                        grassImage
                ));
            }
        }
    }
}
