package PokemonPOC.GameMapInits;

import PokemonPOC.GameCore.GameWorld;
import PokemonPOC.GameEntities.GameMapTriggerEntity;
import PokemonPOC.GameEntities.GamePlayerEntity;
import PokemonPOC.GameEntities.GameStaticEntity;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;

/**
 * Created by guy on 8/3/15.
 * The hero's house, first floor.
 */
public class GamePalletHeroHouse1FInit extends GameMapInit {
    public GamePalletHeroHouse1FInit(float playerX, float playerY) {
        super(playerX, playerY);
    }

    @Override
    public void initCollision(GameWorld world) {
        world.collisionMap.addCollisionBox(0, 0, 192, 32); //top wall
        world.collisionMap.addCollisionBox(0, 112, 16, 128); //left plant
        world.collisionMap.addCollisionBox(176, 112, 192, 128); //right plant
        world.collisionMap.addCollisionBox(64, 64, 128, 96); //table, chairs
    }

    @Override
    public void initMap(GameWorld world) throws SlickException {
        Image houseImage = new Image("assets/pallet_house_1_1f.png");
        Image matImage = new Image("assets/pallet_floor_mat.png");

        world.entities.add(new GameStaticEntity(0, 0, 0, houseImage));
        world.entities.add(new GameStaticEntity(43, 132, 0, matImage));

        //Back to pallet town trigger.
        ArrayList<GameMapInit> palletInits = new ArrayList<>();
        palletInits.add(new GamePalletTownInit(96, 128));
        world.entities.add(new GameMapTriggerEntity(48, 144, 0, palletInits));

        //Second floor trigger.
        ArrayList<GameMapInit> floorInits = new ArrayList<>();
        floorInits.add(new GamePalletHeroHouse2FInit(144, 32));
        world.entities.add(new GameMapTriggerEntity(160, 32, 0, floorInits));
    }

    @Override
    public void initNpcs(GameWorld world) throws SlickException {
        SpriteSheet playerSheet = new SpriteSheet("assets/player.png", 16, 32);

        if (this.isPlayerInitialised) {
            GamePlayerEntity player = new GamePlayerEntity(this.playerX, this.playerY, 1, playerSheet, world);
            world.entities.add(player);
            world.camera = player;
        }
    }

    @Override
    public void initTopSprites(GameWorld world) throws SlickException {
        Image tvImage = new Image("assets/pallet_tv.png");
        Image plantImage = new Image("assets/pallet_plant.png");

        world.entities.add(new GameStaticEntity(80, 0, 2, tvImage));
        world.entities.add(new GameStaticEntity(0, 96, 2, plantImage));
        world.entities.add(new GameStaticEntity(176, 96, 2, plantImage));
    }
}
