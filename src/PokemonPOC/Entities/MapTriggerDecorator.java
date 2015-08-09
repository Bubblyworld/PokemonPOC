package PokemonPOC.Entities;

import PokemonPOC.Core.World;
import PokemonPOC.MapInits.MapInit;

import java.util.List;

/**
 * Created by guy on 8/3/15.
 * Creates an entity that triggers a map change, for instance a door.
 */
public class MapTriggerDecorator extends TriggerDecorator {
    List<MapInit> mapInits;

    public MapTriggerDecorator(Entity entity, List<MapInit> mapInits) {
        super(entity);

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
        return new MapTriggerDecorator(entity.clone(), mapInits);
    }
}
