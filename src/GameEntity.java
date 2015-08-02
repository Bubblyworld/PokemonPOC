import java.util.PriorityQueue;

//A game object that has position, can be updated and can be rendered.
public abstract class GameEntity {
    public float x, y;

    public GameEntity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(float currentTickF, float tickDelta, PriorityQueue<GameAction> actionQueue, GameCollisionMap collisionMap);
    public abstract void render(float cameraX, float cameraY);
}