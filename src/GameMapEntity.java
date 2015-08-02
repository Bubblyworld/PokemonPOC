import org.newdawn.slick.Image;

import java.util.PriorityQueue;

//A tilemap entity of a given height and size.
public class GameMapEntity extends GameEntity {
    //TODO: actually make it a tilemap
    Image image;

    public GameMapEntity(float x, float y, Image image) {
        super(x, y);
        this.image = image;
    }

    public void update(float currentTickF, float tickDelta, PriorityQueue<GameAction> actionQueue) {
    }

    public void render(float cameraX, float cameraY) {
        float renderX = x - cameraX;
        float renderY = y - cameraY;

        image.draw(renderX, renderY);
    }
}
