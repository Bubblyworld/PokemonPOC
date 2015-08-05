package PokemonPOC.Entities;

import PokemonPOC.Core.World;

/**
 * A game object that has position, rendering depth, can be updated and can be rendered.
 * If an entity is "dead" it is removed at first opportunity by the game world.
 *
 * Update returns whether the game should continue updating or not. This is important,
 * for instance, when a map jump needs to cause an entity reload.
 */
public abstract class Entity {
    public boolean isDead;
    public float depth;
    public float x, y;

    public Entity(float x, float y, float depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.isDead = false;
    }

    public abstract boolean update(float currentTickF, float tickDelta, World world);
    public abstract void render(float cameraX, float cameraY);
}