import org.newdawn.slick.SpriteSheet;

import java.util.PriorityQueue;

/**
 * Created by Guy Paterson-Jones on 8/1/15.
 * Models an Npc or Player entity in the game walking state.
 * For the moment, we assume that the spritesheet is a 3x3, with the sprites being:
 *    down_right_foot face_down down_left_foot
 *    up_right_foot   face_up   up_left_foot
 *    left_right_foot face_left left_left_foot
 */
public class GameNpcEntity extends GameEntity {
    //The spritesheet, current sprite and if we should flip it.
    public SpriteSheet spriteSheet;
    public int spriteX, spriteY;
    public boolean flipSpriteHorizontally;

    //Are we currently busy doing an action?
    public boolean busyWithAction;

    //Direction constants for figuring out the sprite.
    public static final int FACE_DOWN  = 0;
    public static final int FACE_UP    = 1;
    public static final int FACE_LEFT  = 2;
    public static final int FACE_RIGHT = 3;

    public GameNpcEntity(float x, float y, SpriteSheet spriteSheet) {
        super(x, y);

        this.spriteSheet = spriteSheet;
        spriteX = 0;
        spriteY = 0;

        busyWithAction = false;
        flipSpriteHorizontally = false;
    }

    @Override
    public void update(float currentTickF, float tickDelta, PriorityQueue<GameAction> actionQueue, GameCollisionMap collisionMap) {
        //For the moment, we do absolutely nothing ^_^.
        long movementTick = getMovementTick(currentTickF);
        if (!busyWithAction) {
            actionQueue.add(new GameNpcMovementAction(movementTick, this, -1, 0));
        }
    }

    @Override
    public void render(float cameraX, float cameraY) {
        float renderX = x - cameraX;
        float renderY = y - cameraY;

        //TODO we subtract 16 as the sprite is 16x32
        spriteSheet
                .getSprite(spriteX, spriteY)
                .getFlippedCopy(flipSpriteHorizontally, false)
                .draw(renderX, renderY - 16.0f);
    }

    /**
     * Can we move in the given direction without hitting a collision entity?
     */
    public boolean canMoveInDirection(int dx, int dy, GameCollisionMap collisionMap) {
        return !collisionMap.isCollision(x + dx*Constants.BLOCK_SIZE, y + dy*Constants.BLOCK_SIZE);
    }

    public long getMovementTick(float currentTickF) {
        return (currentTickF - (long) currentTickF) < Constants.NPC_MOVEMENT_EPSILON ?
                (long) currentTickF :
                (long) currentTickF + 1;
    }

    public void setSprite(int currentDirection, int currentStep) {
        flipSpriteHorizontally = false;
        spriteY = currentDirection;
        spriteX = currentStep;

        //Do we need to flip the sprite?
        if (currentDirection == FACE_RIGHT) {
            spriteY = FACE_LEFT;
            flipSpriteHorizontally = true;
        }

        if (currentStep == 3) {
            spriteX = 1;
        }
    }
}
