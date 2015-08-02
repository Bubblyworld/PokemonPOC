import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by guy on 8/1/15.
 * Initialises the pallet town map for the proof of concept.
 */
public class PalletTown {
    public static void init(GameWalkingState state, GameContainer container) throws SlickException {
        initMap(state, container);
        initNpcs(state, container);
        initTopSprites(state, container);

        //Add collision information.
        state.collisionMap.clear();
        state.collisionMap.addCollisionBox(80, 64, 160, 128); //left red house
        state.collisionMap.addCollisionBox(224, 64, 304, 128); //right red house
        state.collisionMap.addCollisionBox(208, 160, 320, 224); //bottom right house
        state.collisionMap.addCollisionBox(80, 176, 160, 192); //left fence
        state.collisionMap.addCollisionBox(64, 112, 80, 128); //left postbox
        state.collisionMap.addCollisionBox(208, 112, 224, 128); //right postbox
        state.collisionMap.addCollisionBox(80, 224, 96, 240); //bottom left sign
        state.collisionMap.addCollisionBox(208, 256, 304, 272); //bottom right fence
        state.collisionMap.addCollisionBox(112, 272, 176, 320); //bottom pool

        //Forest bounds collision info.
        state.collisionMap.addCollisionBox(0, 0, 192, 32); //top left forest
        state.collisionMap.addCollisionBox(224, 0, 384, 32); //top right forest
        state.collisionMap.addCollisionBox(0, 0, 32, 320); //left forest
        state.collisionMap.addCollisionBox(352, 0, 384, 320); //right forest
        state.collisionMap.addCollisionBox(0, 320, 384, 352); //bottom forest
    }

    private static void initMap(GameWalkingState state, GameContainer container) throws SlickException {
        //The forest background and the actual palletTown image.
        Image palletTownTree = new Image("assets/pallet_tree.png");
        Image palletTownSprite = new Image("assets/pallet_town.png");

        state.entities.add(new GameTileMapEntity(-1600, -1600, palletTownTree, 400, 400));
        state.entities.add(new GameStaticEntity(0, 0, palletTownSprite));

        //Moving flowers in pallet town.
        SpriteSheet flowerSheet = new SpriteSheet("assets/flower_animation.png", 16, 16);

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                state.entities.add(new GameAnimatedEntity(96 + x*16, 192 + y*16, flowerSheet, 1.0f));
            }
        }
    }

    private static void initNpcs(GameWalkingState state, GameContainer container) throws SlickException {
        SpriteSheet playerSheet = new SpriteSheet("assets/player.png", 16, 32);

        GamePlayerEntity player = new GamePlayerEntity(112, 16, playerSheet, container.getInput());
        state.entities.add(player);
        state.camera = player;
    }

    //TODO: this is a pass at creating a spritesheet on top so the player can walk behind things.
    // This will be replaced with proper spritesheets later.
    private static void initTopSprites(GameWalkingState state, GameContainer container) throws SlickException {
        //The tops of the houses for the player to walk under.
        Image house1 = new Image("assets/pallet_house_1.png");
        Image house2 = new Image("assets/pallet_house_2.png");

        state.entities.add(new GameStaticEntity(80, 48, house1));
        state.entities.add(new GameStaticEntity(224, 48, house1));
        state.entities.add(new GameStaticEntity(208, 144, house2));

        //The tops of the trees for the player to walk under.
        Image treeTop = new Image("assets/pallet_tree_top.png");

        for (int i = 0; i < 10; i++)
            state.entities.add(new GameStaticEntity(32 + i*32, 288, treeTop));
    }
}
