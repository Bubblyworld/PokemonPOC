package PokemonPOC.Entities;

import PokemonPOC.Constants;
import PokemonPOC.Core.World;

/**
 * Created by guy on 8/8/15.
 * Renders an entity whenever the player is half a block away or closer on y axis,
 * and always on the x axis. Allows us to properly render things like animated grass
 * and ash such that it doesn't cut off the player's head.
 */
public class PartialRenderDecorator extends EntityDecorator {
    boolean isNpcClose;
    float xDist, yDist;

    public PartialRenderDecorator(Entity entity, float xDist, float yDist) {
        super(entity);

        this.xDist = xDist;
        this.yDist = yDist;
        this.isNpcClose = false;
    }

    @Override
    public Entity clone() {
        return new PartialRenderDecorator(entity.clone(), xDist, yDist);
    }

    @Override
    public boolean update(float currentTickF, float tickDelta, World world) {
        if (!super.update(currentTickF, tickDelta, world))
            return false;

        isNpcClose = false;
        for (Entity entity : world.entities) {
            if (entity instanceof NpcEntity) {
                if (Math.abs(entity.x - this.entity.x) < xDist) {
                    if (Math.abs(entity.y - this.entity.y) < yDist)
                        isNpcClose = true;
                }
            }
        }

        return true;
    }

    @Override
    public void render(float cameraX, float cameraY) {
        if (isNpcClose)
            super.render(cameraX, cameraY);
    }
}
