import org.newdawn.slick.Image;

import java.util.PriorityQueue;

/**
 * Created by guy on 8/2/15.
 * Models a static image entity.
 */
public class GameStaticEntity extends GameEntity {
    Image image;

    public GameStaticEntity(float x, float y, Image image) {
        super(x, y);

        this.image = image;
    }

    @Override
    public void update(float currentTickF, float tickDelta, PriorityQueue<GameAction> actionQueue, GameCollisionMap collisionMap) {
    }

    @Override
    public void render(float cameraX, float cameraY) {
        float renderX = x - cameraX;
        float renderY = y - cameraY;

        image.draw(renderX, renderY);
    }
}
