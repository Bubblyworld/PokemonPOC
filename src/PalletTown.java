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
    }

    private static void initMap(GameWalkingState state, GameContainer container) throws SlickException {
        Image palletTownSprite = new Image("assets/pallet_town.png");
        GameMapEntity palletTownMap = new GameMapEntity(0, 0, palletTownSprite);

        state.entities.add(palletTownMap);
    }

    private static void initNpcs(GameWalkingState state, GameContainer container) throws SlickException {
        SpriteSheet playerSheet = new SpriteSheet("assets/player.png", 16, 32);
        GamePlayerEntity player = new GamePlayerEntity(112, 16, playerSheet, container.getInput());

        state.entities.add(player);
        state.camera = player;
    }
}
