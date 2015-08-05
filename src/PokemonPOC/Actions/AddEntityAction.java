package PokemonPOC.Actions;

import PokemonPOC.Core.World;
import PokemonPOC.Entities.Entity;

/**
 * Created by guy on 8/4/15.
 * An action that adds in an entity to the game.
 * We need this as concurrent modifications cannot be made by entities to the entity list
 * in World. To solve this issue, this action just gets executed next update cycle.
 */
public class AddEntityAction extends Action {
    Entity entity;

    public AddEntityAction(long activationTick, Entity entity) {
        super(activationTick);

        this.entity = entity;
    }

    @Override
    public void update(float currentTickF, World world) {
        world.entities.add(entity);
        this.setComplete();
    }
}
