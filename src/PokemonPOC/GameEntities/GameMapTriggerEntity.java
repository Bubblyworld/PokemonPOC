package PokemonPOC.GameEntities;

import PokemonPOC.GameMapInits.GameMapInit;
import PokemonPOC.GameCore.GameWorld;

import java.util.List;

/**
 * Created by guy on 8/3/15.
 * Creates an entity that triggers a map change, for instance a door.
 */
public class GameMapTriggerEntity extends GameTriggerEntity {
    List<GameMapInit> mapInits;

    public GameMapTriggerEntity(float x, float y, float depth, List<GameMapInit> mapInits) {
        super(x, y, depth);

        this.mapInits = mapInits;
    }

    @Override
    public void playerStepsOn(GameWorld world, GamePlayerEntity player) {
        world.enterMaps(mapInits);
    }

    @Override
    public void playerStepsOff(GameWorld world, GamePlayerEntity player) {
    }
}
