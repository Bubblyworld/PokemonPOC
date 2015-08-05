package PokemonPOC.Actions;

import PokemonPOC.Constants;
import PokemonPOC.Core.CollisionBox;
import PokemonPOC.Entities.NpcEntity;
import PokemonPOC.Core.World;

/**
 * Created by guy on 8/1/15.
 * Controls the movement of a given NPC from one tile to an adjacent one.
 * Expects to be passed a dx, dy in range [-1, 1] in the integers.
 */
public class NpcMovementAction extends Action {
    float lastUpdateTickF;
    NpcEntity entity;
    float startX, startY;
    int dx, dy;

    public NpcMovementAction(long activationTick, NpcEntity entity, int dx, int dy, World world) {
        super(activationTick);

        this.lastUpdateTickF = activationTick;
        this.entity = entity;
        this.dx = dx;
        this.dy = dy;

        startX = entity.x;
        startY = entity.y;

        //Update entity, along with its collision box.
        entity.busyWithAction = true;
        world.collisionMap.entities.remove(entity.collisionBox);

        entity.collisionBox = new CollisionBox(
                entity.x + dx*Constants.BLOCK_SIZE,
                entity.y + dy*Constants.BLOCK_SIZE,
                entity.x + dx*Constants.BLOCK_SIZE + 16,
                entity.y + dy*Constants.BLOCK_SIZE + 16
        );
        world.collisionMap.entities.add(entity.collisionBox);
    }

    @Override
    public void update(float currentTickF, World world) {
        float deltaTickF = currentTickF - lastUpdateTickF;

        //We move the entity first.
        entity.x += Constants.NPC_PIX_PER_TICK * deltaTickF * dx;
        entity.y += Constants.NPC_PIX_PER_TICK * deltaTickF * dy;

        //Clamp and finish the movement if we have moved an entire tile.
        float startDx = Math.abs(entity.x - startX);
        float startDy = Math.abs(entity.y - startY);
        if (startDx >= Constants.BLOCK_SIZE || startDy >= Constants.BLOCK_SIZE) {
            entity.x = startX + Constants.BLOCK_SIZE * dx;
            entity.y = startY + Constants.BLOCK_SIZE * dy;

            entity.busyWithAction = false;
            this.setComplete();
            return;
        }

        //Update the entities sprite. We have a four frame animation sprite,
        // so we hardcode it in for now. TODO
        int currentStep =
                  (int) Math.abs(4.0 * startDx / Constants.BLOCK_SIZE * dx)
                + (int) Math.abs(4.0 * startDy / Constants.BLOCK_SIZE * dy);

        int currentDirection = NpcEntity.FACE_DOWN;
        if (dy < 0) currentDirection = NpcEntity.FACE_UP;
        if (dx > 0) currentDirection = NpcEntity.FACE_RIGHT;
        if (dx < 0) currentDirection = NpcEntity.FACE_LEFT;

        entity.setSprite(currentDirection, currentStep);
        lastUpdateTickF = currentTickF;
    }
}
