package PokemonPOC.Entities;

import PokemonPOC.Core.World;
import PokemonPOC.MapInits.MapInit;

import java.util.List;

/**
 * Created by guy on 8/3/15.
 * Creates an entity that triggers a map change, for instance a door.
 */
public class MapTriggerEntity extends TriggerEntity {
    List<MapInit> mapInits;

    public MapTriggerEntity(float x, float y, float depth, List<MapInit> mapInits) {
        super(x, y, depth);

        this.mapInits = mapInits;
    }

    @Override
    public boolean playerStepsOn(World world, PlayerEntity player) {
        world.enterMaps(mapInits);

        return false;
    }

    @Override
    public boolean playerStepsOff(World world, PlayerEntity player) {
        return true;
    }

    @Override
    public Entity clone() {
        return new MapTriggerEntity(x, y, depth, mapInits);
    }
}
