package PokemonPOC.MapInits;

import PokemonPOC.Core.World;
import PokemonPOC.Entities.MapTriggerEntity;
import PokemonPOC.Entities.PlayerEntity;
import PokemonPOC.Entities.StaticEntity;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;

/**
 * Created by guy on 8/4/15.
 * Second floor of heros house.
 */
public class PalletHeroHouse2FInit extends MapInit {
    public PalletHeroHouse2FInit(float playerX, float playerY) {
        super(playerX, playerY);
    }

    @Override
    public void initCollision(World world) {

    }

    @Override
    public void initMap(World world) throws SlickException {
        Image houseImage = new Image("assets/pallet_house_1_2f.png");

        world.entities.add(new StaticEntity(0, 0, 0, houseImage));

        ArrayList<MapInit> mapInits = new ArrayList<>();
        mapInits.add(new PalletHeroHouse1FInit(144, 32));
        world.entities.add(new MapTriggerEntity(128, 32, 0, mapInits));
    }

    @Override
    public void initNpcs(World world) throws SlickException {
        if (this.isPlayerInitialised) {
            SpriteSheet playerSheet = new SpriteSheet("assets/player.png", 16, 32);
            PlayerEntity player = new PlayerEntity(this.playerX, this.playerY, 1, playerSheet, world);
            world.entities.add(player);
            world.camera = player;
        }
    }

    @Override
    public void initTopSprites(World world) throws SlickException {
        Image tvImage = new Image("assets/pallet_tv.png");

        world.entities.add(new StaticEntity(80, 48, 2, tvImage));
    }
}
