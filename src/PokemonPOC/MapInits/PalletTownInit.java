package PokemonPOC.MapInits;

import PokemonPOC.Core.World;
import PokemonPOC.Entities.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;

/**
 * Created by guy on 8/1/15.
 * Initialises the pallet town map for the proof of concept.
 */
public class PalletTownInit extends MapInit {
    public PalletTownInit(float playerX, float playerY) {
        super(playerX, playerY);
    }

    public void initCollision(World world) {
        //Add collision information.
        world.collisionMap.addCollisionBox(80, 64, 160, 112); //left red house
        world.collisionMap.addCollisionBox(80, 112, 96, 128); //  left of door
        world.collisionMap.addCollisionBox(112, 112, 160, 128); //  right of door

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

    public void initMap(World world) throws SlickException {
        //The forest background and the actual palletTown image.
        Image palletTownTree = new Image("assets/pallet_tree.png");
        Image palletTownSprite = new Image("assets/pallet_town.png");

        world.entities.add(new TileMapEntity(-1600, -1600, 0, palletTownTree, 400, 400));
        world.entities.add(new StaticEntity(0, 0, 0, palletTownSprite));

        //Moving flowers in pallet town.
        SpriteSheet flowerSheet = new SpriteSheet("assets/flower_animation.png", 16, 16);

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                world.entities.add(new AnimatedEntity(96 + x*16, 192 + y*16, 0, flowerSheet, 1.0f));
            }
        }

        //Trigger to get into the hero's house.
        ArrayList<MapInit> heroHouse = new ArrayList<>();
        heroHouse.add(new PalletHeroHouse1FInit(48, 128));
        world.entities.add(new MapTriggerEntity(96, 112, 0, heroHouse));
    }

    public void initNpcs(World world) throws SlickException {
        SpriteSheet hikerSheet = new SpriteSheet("assets/hiker.png", 16, 32);

        //Add a hiker npc in the bottom right corner.
        world.entities.add(new NpcEntity(320, 288, 1, hikerSheet, world));

        //Initialise player if we need to.
        if (this.isPlayerInitialised) {
            SpriteSheet playerSheet = new SpriteSheet("assets/player.png", 16, 32);

            PlayerEntity player = new PlayerEntity(this.playerX, this.playerY, 1, playerSheet, world);
            world.entities.add(player);
            world.camera = player;
        }
    }

    //TODO: this is a pass at creating a spritesheet on top so the player can walk behind things.
    // This will be replaced with proper spritesheets later.
    public void initTopSprites(World world) throws SlickException {
        //The tops of the houses for the player to walk under.
        Image house1 = new Image("assets/pallet_house_1.png");
        Image house2 = new Image("assets/pallet_house_2.png");

        world.entities.add(new StaticEntity(80, 48, 2, house1));
        world.entities.add(new StaticEntity(224, 48, 2, house1));
        world.entities.add(new StaticEntity(208, 144, 2, house2));

        //The tops of the trees for the player to walk under.
        Image treeTop = new Image("assets/pallet_tree_top.png");

        for (int i = 0; i < 10; i++)
            world.entities.add(new StaticEntity(32 + i*32, 288, 2, treeTop));
    }
}
