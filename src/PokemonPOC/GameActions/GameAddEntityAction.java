package PokemonPOC.GameActions;

import PokemonPOC.GameCore.GameWorld;
import PokemonPOC.GameEntities.GameEntity;

/**
 * Created by guy on 8/4/15.
 * An action that adds in an entity to the game.
 * We need this as concurrent modifications cannot be made by entities to the entity list
 * in GameWorld. To solve this issue, this action just gets executed next update cycle.
 */
public class GameAddEntityAction extends GameAction {
    GameEntity entity;

    public GameAddEntityAction(long activationTick, GameEntity entity) {
        super(activationTick);

        this.entity = entity;
    }

    @Override
    public void update(float currentTickF, GameWorld world) {
        world.entities.add(entity);
        this.setComplete();
    }
}
