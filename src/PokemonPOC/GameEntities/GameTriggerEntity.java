package PokemonPOC.GameEntities;

import PokemonPOC.GameCore.GameWorld;

/**
 * Created by guy on 8/3/15.
 * Models an invisible entity that has some action when the player moves on or
 * off the entity. isSteppedOn is true if there is currently a player standing
 * on the trigger entity.
 */
public abstract class GameTriggerEntity extends GameEntity {
    boolean isSteppedOn;

    public GameTriggerEntity(float x, float y, float depth) {
        super(x, y, depth);

        this.isSteppedOn = false;
    }

    public boolean update(float currentTickF, float tickDelta, GameWorld world) {
        //Run through each entity - if it is a player, see if it is stepping on us or not.
        for (GameEntity entity : world.entities) {
            if (entity instanceof GamePlayerEntity) {
                if (entity.x == x && entity.y == y) {
                    if (!isSteppedOn) {
                        isSteppedOn = true;
                        if (!playerStepsOn(world, (GamePlayerEntity) entity)) {
                            return false;
                        }
                        //TODO conditions on ending, for multiplayer
                    }
                } else {
                    if (isSteppedOn) {
                        isSteppedOn = false;
                        if (!playerStepsOff(world, (GamePlayerEntity) entity))
                            return false;
                    }
                }
            }
        }

        return true;
    }

    public void render(float cameraX, float cameraY) {
    }

    public abstract boolean playerStepsOn(GameWorld world, GamePlayerEntity player);
    public abstract boolean playerStepsOff(GameWorld world, GamePlayerEntity player);
}
