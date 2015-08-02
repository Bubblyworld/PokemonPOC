import org.newdawn.slick.SpriteSheet;

import java.util.PriorityQueue;

/**
 * Created by guy on 8/2/15.
 * Models a static entity that animates, like the flowers or the grass.
 * This assumes that the spritesheet is a horizontal one. It doesn't currently
 * support arbitrary layout of the animation sprites.
 */
public class GameAnimatedEntity extends GameEntity {
    SpriteSheet spriteSheet;
    float framesPerTick;
    float currentFrame;

    public GameAnimatedEntity(float x, float y, SpriteSheet spriteSheet, float framesPerTick) {
        this(x, y, spriteSheet, framesPerTick, 0);
    }

    public GameAnimatedEntity(float x, float y, SpriteSheet spriteSheet, float framesPerTick, int currentFrame) {
        super(x, y);

        this.spriteSheet = spriteSheet;
        this.framesPerTick = framesPerTick;
        this.currentFrame = currentFrame;
    }

    @Override
    public void update(float currentTickF, float tickDelta, PriorityQueue<GameAction> actionQueue, GameCollisionMap collisionMap) {
        //Update the current frame, and clamp it.
        currentFrame += tickDelta;

        if (currentFrame > spriteSheet.getHorizontalCount()) {
            float frameDiff = currentFrame - (long) currentFrame;

            currentFrame = ((long) currentFrame % spriteSheet.getHorizontalCount())
                    + frameDiff;
        }
    }

    @Override
    public void render(float cameraX, float cameraY) {
        float renderX = x - cameraX;
        float renderY = y - cameraY;

        spriteSheet
                .getSprite((int) currentFrame, 0)
                .draw(renderX, renderY);
    }
}
