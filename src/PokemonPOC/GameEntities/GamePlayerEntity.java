package PokemonPOC.GameEntities;

import PokemonPOC.GameActions.GameNpcMovementAction;
import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by guy on 8/2/15.
 * Models a player entity - basically an NPC entity that is controlled by the keyboard.
 * That is the only difference really, apart from semantics around battle starts and things.
 */
public class GamePlayerEntity extends GameNpcEntity {
    Input playerInput;

    public GamePlayerEntity(float x, float y, float depth, SpriteSheet spriteSheet, Input playerInput) {
        super(x, y, depth, spriteSheet);

        this.playerInput = playerInput;
    }

    @Override
    /**
     * If we aren't busy with something already, allow arrows to control the players'
     * movement.
     */
    public boolean update(float currentTickF, float tickDelta, GameWorld world) {
        long movementTick = getMovementTick(currentTickF);

        if (!busyWithAction) {
            int dx = 0, dy = 0;

            if (playerInput.isKeyDown(Input.KEY_UP))         dy = -1;
            else if (playerInput.isKeyDown(Input.KEY_DOWN))  dy = +1;
            else if (playerInput.isKeyDown(Input.KEY_LEFT))  dx = -1;
            else if (playerInput.isKeyDown(Input.KEY_RIGHT)) dx = +1;

            if (dx != 0 || dy != 0) {
                if (this.canMoveInDirection(dx, dy, world.collisionMap))
                    world.actionQueue.add(new GameNpcMovementAction(movementTick, this, dx, dy));
            }
        }

        return true;
    }
}
