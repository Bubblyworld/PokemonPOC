package PokemonPOC.Entities;

import PokemonPOC.Core.World;

/**
 * Created by guy on 8/5/15.
 * An entity that decorates another entity with some additional features.
 * I thought this would be a nicer way to design things than the hacks I was
 * doing before to get dialogues, map change triggers etcetera.
 * Reduces the clutter as well.
 */
public class EntityDecorator extends Entity {
    public Entity entity;

    public EntityDecorator(Entity entity) {
        super(entity.x, entity.y, entity.depth);

        this.entity = entity;
    }

    @Override
    public Entity clone() {
        return new EntityDecorator(entity);
    }

    @Override
    public boolean update(float currentTickF, float tickDelta, World world) {
        return entity.update(currentTickF, tickDelta, world);
    }

    @Override
    public void render(float cameraX, float cameraY) {
        entity.render(cameraX, cameraY);
    }
}
