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
    }

    private static void initMap(GameWalkingState state, GameContainer container) throws SlickException {
        Image palletTownTree = new Image("assets/pallet_tree.png");
        Image palletTownSprite = new Image("assets/pallet_town.png");
        GameTileMapEntity palletForest = new GameTileMapEntity(-1600, -1600, palletTownTree, 400, 400);
        GameStaticEntity palletTownMap = new GameStaticEntity(0, 0, palletTownSprite);

        state.entities.add(palletForest);
        state.entities.add(palletTownMap);
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
        Image house1 = new Image("assets/pallet_house_1.png");
        Image house2 = new Image("assets/pallet_house_2.png");
        GameStaticEntity leftRedHouse = new GameStaticEntity(80, 48, house1);
        GameStaticEntity rightRedHouse = new GameStaticEntity(224, 48, house1);
        GameStaticEntity rightBlueHouse = new GameStaticEntity(208, 144, house2);

        state.entities.add(leftRedHouse);
        state.entities.add(rightRedHouse);
        state.entities.add(rightBlueHouse);
    }
}
