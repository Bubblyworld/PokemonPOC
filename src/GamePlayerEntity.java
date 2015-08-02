import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;

import java.util.PriorityQueue;

/**
 * Created by guy on 8/2/15.
 * Models a player entity - basically an NPC entity that is controlled by the keyboard.
 * That is the only difference really, apart from semantics around battle starts and things.
 */
public class GamePlayerEntity extends GameNpcEntity {
    Input playerInput;

    public GamePlayerEntity(float x, float y, SpriteSheet spriteSheet, Input playerInput) {
        super(x, y, spriteSheet);

        this.playerInput = playerInput;
    }

    @Override
    /**
     * If we aren't busy with something already, allow arrows to control the players'
     * movement.
     */
    public void update(float currentTickF, float tickDelta, PriorityQueue<GameAction> actionQueue) {
        long movementTick = getMovementTick(currentTickF);

        if (!busyWithAction) {
            if (playerInput.isKeyDown(Input.KEY_UP))
                actionQueue.add(new GameNpcMovementAction(movementTick, this, 0, -1));
            else if (playerInput.isKeyDown(Input.KEY_DOWN))
                actionQueue.add(new GameNpcMovementAction(movementTick, this, 0, +1));
            else if (playerInput.isKeyDown(Input.KEY_LEFT))
                actionQueue.add(new GameNpcMovementAction(movementTick, this, -1, 0));
            else if (playerInput.isKeyDown(Input.KEY_RIGHT))
                actionQueue.add(new GameNpcMovementAction(movementTick, this, +1, 0));
        }
    }
}
