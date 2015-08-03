package PokemonPOC.GameEntities;

import PokemonPOC.GameCore.GameWorld;

/**
 * A game object that has position, rendering depth, can be updated and can be rendered.
 * If an entity is "dead" it is removed at first opportunity by the game world.
 */
public abstract class GameEntity {
    public boolean isDead;
    public float depth;
    public float x, y;

    public GameEntity(float x, float y, float depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.isDead = false;
    }

    public abstract void update(float currentTickF, float tickDelta, GameWorld world);
    public abstract void render(float cameraX, float cameraY);
}