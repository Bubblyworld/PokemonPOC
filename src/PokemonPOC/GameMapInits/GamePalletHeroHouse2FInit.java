package PokemonPOC.GameMapInits;

import PokemonPOC.GameCore.GameWorld;
import PokemonPOC.GameEntities.GameMapTriggerEntity;
import PokemonPOC.GameEntities.GamePlayerEntity;
import PokemonPOC.GameEntities.GameStaticEntity;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.GameState;

import java.util.ArrayList;

/**
 * Created by guy on 8/4/15.
 * Second floor of heros house.
 */
public class GamePalletHeroHouse2FInit extends GameMapInit {
    public GamePalletHeroHouse2FInit(float playerX, float playerY) {
        super(playerX, playerY);
    }

    @Override
    public void initCollision(GameWorld world) {

    }

    @Override
    public void initMap(GameWorld world) throws SlickException {
        Image houseImage = new Image("assets/pallet_house_1_2f.png");

        world.entities.add(new GameStaticEntity(0, 0, 0, houseImage));

        ArrayList<GameMapInit> mapInits = new ArrayList<>();
        mapInits.add(new GamePalletHeroHouse1FInit(144, 32));
        world.entities.add(new GameMapTriggerEntity(128, 32, 0, mapInits));
    }

    @Override
    public void initNpcs(GameWorld world) throws SlickException {
        if (this.isPlayerInitialised) {
            SpriteSheet playerSheet = new SpriteSheet("assets/player.png", 16, 32);
            GamePlayerEntity player = new GamePlayerEntity(this.playerX, this.playerY, 1, playerSheet, world);
            world.entities.add(player);
            world.camera = player;
        }
    }

    @Override
    public void initTopSprites(GameWorld world) throws SlickException {
        Image tvImage = new Image("assets/pallet_tv.png");

        world.entities.add(new GameStaticEntity(80, 48, 2, tvImage));
    }
}
