package PokemonPOC.Entities;

import PokemonPOC.Constants;
import PokemonPOC.Actions.NpcMovementAction;
import PokemonPOC.Core.CollisionBox;
import PokemonPOC.Core.CollisionMap;
import PokemonPOC.Core.World;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Guy Paterson-Jones on 8/1/15.
 * Models an Npc or Player entity in the game walking state.
 * For the moment, we assume that the spritesheet is a 3x3, with the sprites being:
 *    down_right_foot face_down down_left_foot
 *    up_right_foot   face_up   up_left_foot
 *    left_right_foot face_left left_left_foot
 */
public class NpcEntity extends Entity {
    //The spritesheet, current sprite and if we should flip it.
    public SpriteSheet spriteSheet;
    public int spriteX, spriteY;
    public boolean flipSpriteHorizontally;
    public int currentDirection;

    //Are we currently busy doing an action?
    public boolean busyWithAction;

    //Direction constants for figuring out the sprite.
    public static final int FACE_DOWN  = 0;
    public static final int FACE_UP    = 1;
    public static final int FACE_LEFT  = 2;
    public static final int FACE_RIGHT = 3;

    //Collision box for this sprite.
    public CollisionBox collisionBox;

    public NpcEntity(float x, float y, float depth, SpriteSheet spriteSheet, World world) {
        super(x, y, depth);

        this.spriteSheet = spriteSheet;
        spriteX = 1;
        spriteY = 0;

        busyWithAction = false;
        flipSpriteHorizontally = false;

        //Add a collision box for this sprite
        collisionBox = new CollisionBox(x, y, x + Constants.BLOCK_SIZE, y + Constants.BLOCK_SIZE);
        world.collisionMap.entities.add(collisionBox);
    }

    @Override
    public boolean update(float currentTickF, float tickDelta, World world) {
        //Every now and then our npc moves a little.
        if (!this.busyWithAction) {
            //TODO no hardcoding, additional AIs?
            //TODO this is terrible random walking gen, depends on processor speed
            float movesPerTick = 0.05f;
            if (Math.random() < tickDelta * movesPerTick) {
                long movementTick = getMovementTick(currentTickF);
                int randomDirection = (int) (Math.random() * 4);
                int dx = 0, dy = 0;

                if (randomDirection == 0) dx = -1;
                if (randomDirection == 1) dx = +1;
                if (randomDirection == 2) dy = -1;
                if (randomDirection == 3) dy = +1;

                if (this.canMoveInDirection(dx, dy, world.collisionMap))
                    world.actionQueue.add(new NpcMovementAction(movementTick, this, dx, dy, world));
                else
                    this.faceDirection(dx, dy);
            }
        }

        return true;
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
    public boolean canMoveInDirection(int dx, int dy, CollisionMap collisionMap) {
        return !collisionMap.isCollision(x + dx* Constants.BLOCK_SIZE, y + dy*Constants.BLOCK_SIZE);
    }

    public long getMovementTick(float currentTickF) {
        return (currentTickF - (long) currentTickF) < Constants.NPC_MOVEMENT_EPSILON ?
                (long) currentTickF :
                (long) currentTickF + 1;
    }

    /**
     * Face the given direction by changing our sprite.
     */
    public void faceDirection(int dx, int dy) {
        if (dx > 0) setSprite(NpcEntity.FACE_RIGHT, 1);
        if (dx < 0) setSprite(NpcEntity.FACE_LEFT, 1);
        if (dy > 0) setSprite(NpcEntity.FACE_DOWN, 1);
        if (dy < 0) setSprite(NpcEntity.FACE_UP, 1);
    }

    public void setSprite(int currentDirection, int currentStep) {
        this.currentDirection = currentDirection;
        flipSpriteHorizontally = false;
        spriteY = currentDirection;
        spriteX = currentStep;

        //Do we need to flip the sprite?
        if (currentDirection == NpcEntity.FACE_RIGHT) {
            spriteY = NpcEntity.FACE_LEFT;
            flipSpriteHorizontally = true;
        }

        if (currentStep == 3) {
            spriteX = 1;
        }
    }

    /**
     * Are we facing the given position?
     */
    public boolean isFacing(float px, float py) {
        //First check they are in an adjacent block
        if (Math.abs(px - x) + Math.abs(py - y) - Constants.BLOCK_SIZE > Constants.EPSILON)
            return false;

        //Check that we are facing them
        int dx = Math.round((px - x) / Constants.BLOCK_SIZE);
        int dy = Math.round((py - y) / Constants.BLOCK_SIZE);

        if (dx == -1 && dy == 0 && currentDirection == FACE_LEFT) return true;
        if (dx == +1 && dy == 0 && currentDirection == FACE_RIGHT) return true;
        if (dx == 0 && dy == -1 && currentDirection == FACE_UP) return true;
        if (dx == 0 && dy == +1 && currentDirection == FACE_DOWN) return true;

        return false;
    }
}
