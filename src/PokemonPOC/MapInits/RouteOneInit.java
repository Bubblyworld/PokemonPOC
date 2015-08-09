package PokemonPOC.MapInits;

import PokemonPOC.Constants;
import PokemonPOC.Core.World;
import PokemonPOC.Entities.AddEntityTriggerDecorator;
import PokemonPOC.Entities.AnimatedEntity;
import PokemonPOC.Entities.PartialRenderDecorator;
import PokemonPOC.Entities.StaticEntity;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by guy on 8/2/15.
 * Initialises route 1 for the proof of concept.
 */
public class RouteOneInit extends MapInit {
    public void initCollision(World world) {
    }

    public void initMap(World world) throws SlickException {
        Image routeOne = new Image("assets/route_1.png");

        world.entities.add(new StaticEntity(0, -640, 0, routeOne));

        //Add grass that animated when you walk through it
//        SpriteSheet grassSheet = new SpriteSheet("assets/grass_animation.png", 16, 16);
//        for (int y = 0; y < 5; y++) {
//            for (int x = 0; x < 2; x++) {
//                world.entities.add(
//                    new AddEntityTriggerDecorator(
//                        192 + x * Constants.BLOCK_SIZE,
//                        -16 - y * Constants.BLOCK_SIZE,
//                        0,
//                        2.0f,
//                        null,
//                        grassSheet
//                    )
//                );
//            }
//        }
    }

    public void initNpcs(World world) throws SlickException {
    }

    public void initTopSprites(World world) throws SlickException {
        //Add grass sprites.
        Image grassImage = new Image("assets/grass_top.png");
        SpriteSheet grassSheet = new SpriteSheet("assets/grass_animation.png", 16, 16);

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 2; x++) {
                world.entities.add(
                        new AddEntityTriggerDecorator(
                                new PartialRenderDecorator(
                                        new StaticEntity(
                                                192 + x * Constants.BLOCK_SIZE,
                                                -16 - y * Constants.BLOCK_SIZE,
                                                2,
                                                grassImage
                                        ),
                                        Constants.BLOCK_SIZE,
                                        Constants.BLOCK_SIZE / 4
                                ),
                                null,
                                new AnimatedEntity(
                                        192 + x * Constants.BLOCK_SIZE,
                                        -16 - y * Constants.BLOCK_SIZE,
                                        1,
                                        grassSheet,
                                        1.5f,
                                        grassSheet.getHorizontalCount() / 1.5f
                                )
                        )
                );
            }
        }
    }
}
