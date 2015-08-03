package PokemonPOC.GameMapInits;

import PokemonPOC.GameEntities.GameAnimatedEntity;
import PokemonPOC.GameEntities.GamePlayerEntity;
import PokemonPOC.GameEntities.GameStaticEntity;
import PokemonPOC.GameEntities.GameTileMapEntity;
import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by guy on 8/1/15.
 * Initialises the pallet town map for the proof of concept.
 */
public class GamePalletTownInit extends GameMapInit {
    public void initCollision(GameWorld world) {
        //Add collision information.
        world.collisionMap.addCollisionBox(80, 64, 160, 128); //left red house
        world.collisionMap.addCollisionBox(224, 64, 304, 128); //right red house
        world.collisionMap.addCollisionBox(208, 160, 320, 224); //bottom right house
        world.collisionMap.addCollisionBox(80, 176, 160, 192); //left fence
        world.collisionMap.addCollisionBox(64, 112, 80, 128); //left postbox
        world.collisionMap.addCollisionBox(208, 112, 224, 128); //right postbox
        world.collisionMap.addCollisionBox(80, 224, 96, 240); //bottom left sign
        world.collisionMap.addCollisionBox(208, 256, 304, 272); //bottom right fence
        world.collisionMap.addCollisionBox(112, 272, 176, 320); //bottom pool

        //Forest bounds collision info.
        world.collisionMap.addCollisionBox(0, 0, 192, 32); //top left forest
        world.collisionMap.addCollisionBox(224, 0, 384, 32); //top right forest
        world.collisionMap.addCollisionBox(0, 0, 32, 320); //left forest
        world.collisionMap.addCollisionBox(352, 0, 384, 320); //right forest
        world.collisionMap.addCollisionBox(0, 320, 384, 352); //bottom forest
    }

    public void initMap(GameWorld world) throws SlickException {
        //The forest background and the actual palletTown image.
        Image palletTownTree = new Image("assets/pallet_tree.png");
        Image palletTownSprite = new Image("assets/pallet_town.png");

        world.entities.add(new GameTileMapEntity(-1600, -1600, 0, palletTownTree, 400, 400));
        world.entities.add(new GameStaticEntity(0, 0, 0, palletTownSprite));

        //Moving flowers in pallet town.
        SpriteSheet flowerSheet = new SpriteSheet("assets/flower_animation.png", 16, 16);

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                world.entities.add(new GameAnimatedEntity(96 + x*16, 192 + y*16, 0, flowerSheet, 1.0f));
            }
        }
    }

    public void initNpcs(GameWorld world) throws SlickException {
        SpriteSheet playerSheet = new SpriteSheet("assets/player.png", 16, 32);

        GamePlayerEntity player = new GamePlayerEntity(112, 16, 1, playerSheet, world.playerInput);
        world.entities.add(player);
        world.camera = player;
    }

    //TODO: this is a pass at creating a spritesheet on top so the player can walk behind things.
    // This will be replaced with proper spritesheets later.
    public void initTopSprites(GameWorld world) throws SlickException {
        //The tops of the houses for the player to walk under.
        Image house1 = new Image("assets/pallet_house_1.png");
        Image house2 = new Image("assets/pallet_house_2.png");

        world.entities.add(new GameStaticEntity(80, 48, 2, house1));
        world.entities.add(new GameStaticEntity(224, 48, 2, house1));
        world.entities.add(new GameStaticEntity(208, 144, 2, house2));

        //The tops of the trees for the player to walk under.
        Image treeTop = new Image("assets/pallet_tree_top.png");

        for (int i = 0; i < 10; i++)
            world.entities.add(new GameStaticEntity(32 + i*32, 288, 2, treeTop));
    }
}
