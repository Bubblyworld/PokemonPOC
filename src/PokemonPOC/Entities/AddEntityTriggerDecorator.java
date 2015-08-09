package PokemonPOC.Entities;

import PokemonPOC.Actions.AddEntityAction;
import PokemonPOC.Core.World;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by guy on 8/4/15.
 * Creates an animation when the player steps on or off something.
 * This is used to create movement effects through grass, for instance.
 * Animations can be passed in as NULL, in which case they aren't displayed.
 */
public class AddEntityTriggerDecorator extends TriggerDecorator {
    Entity stepOnEntity, stepOffEntity;

    public AddEntityTriggerDecorator(Entity entity, Entity stepOnEntity, Entity stepOffEntity) {
        super(entity);

        this.stepOnEntity = stepOnEntity;
        this.stepOffEntity = stepOffEntity;
    }

    @Override
    public boolean playerStepsOn(World world, PlayerEntity player) {
        if (stepOnEntity != null) {
            world.actionQueue.add(
                    new AddEntityAction(world.currentTick, stepOnEntity.clone())
            );
        }

        return true;
    }

    @Override
    public boolean playerStepsOff(World world, PlayerEntity player) {
        if (stepOffEntity != null) {
            world.actionQueue.add(
                    new AddEntityAction(world.currentTick, stepOffEntity.clone())
            );
        }

        return true;
    }

    @Override
    public Entity clone() {
        return new AddEntityTriggerDecorator(
                entity.clone(),
                stepOnEntity == null ? null : stepOnEntity.clone(),
                stepOffEntity == null ? null : stepOffEntity.clone()
        );
    }
}
