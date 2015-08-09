package PokemonPOC.Entities;

import PokemonPOC.Core.World;

/**
 * Created by guy on 8/3/15.
 * Models an invisible entity that has some action when the player moves on or
 * off the entity. isSteppedOn is true if there is currently a player standing
 * on the trigger entity.
 */

public abstract class TriggerDecorator extends EntityDecorator {
    boolean isSteppedOn;

    public TriggerDecorator(Entity entity) {
        super(entity);

        this.isSteppedOn = false;
    }

    public boolean update(float currentTickF, float tickDelta, World world) {
        if (!super.update(currentTickF, tickDelta, world))
            return false;

        //Run through each entity - if it is a player, see if it is stepping on us or not.
        for (Entity entity : world.entities) {
            if (entity instanceof PlayerEntity) {
                if (entity.x == this.entity.x && entity.y == this.entity.y) {
                    if (!isSteppedOn) {
                        isSteppedOn = true;
                        if (!playerStepsOn(world, (PlayerEntity) entity))
                            return false;
                    }
                } else {
                    if (isSteppedOn) {
                        isSteppedOn = false;
                        if (!playerStepsOff(world, (PlayerEntity) entity))
                            return false;
                    }
                }
            }
        }

        return true;
    }

    public abstract boolean playerStepsOn(World world, PlayerEntity player);
    public abstract boolean playerStepsOff(World world, PlayerEntity player);
}
