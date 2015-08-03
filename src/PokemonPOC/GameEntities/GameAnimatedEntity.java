package PokemonPOC.GameEntities;

import PokemonPOC.Constants;
import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by guy on 8/2/15.
 * Models a static entity that animates, like the flowers or the grass.
 * This assumes that the spritesheet is a horizontal one. It doesn't currently
 * support arbitrary layout of the animation sprites.
 *
 * We allow an optional lifespan argument that determines when the entity dies.
 * This is useful, for instance, for animating temporary grass splashes among
 * other things.
 */
public class GameAnimatedEntity extends GameEntity {
    SpriteSheet spriteSheet;
    float framesPerTick;
    float currentLife;
    float lifespan;

    public GameAnimatedEntity(float x, float y, float depth, SpriteSheet spriteSheet, float framesPerTick) {
        this(x, y, depth, spriteSheet, framesPerTick, Constants.INFINITY);
    }

    public GameAnimatedEntity(float x, float y, float depth, SpriteSheet spriteSheet, float framesPerTick, float lifespan) {
        super(x, y, depth);

        this.spriteSheet = spriteSheet;
        this.framesPerTick = framesPerTick;
        this.lifespan = lifespan;
        this.currentLife = 0;
    }

    @Override
    public boolean update(float currentTickF, float tickDelta, GameWorld world) {
        //Update the current life length;
        currentLife += tickDelta;

        if (currentLife > lifespan)
            this.isDead = true;

        return true;
    }

    @Override
    public void render(float cameraX, float cameraY) {
        float renderX = x - cameraX;
        float renderY = y - cameraY;

        spriteSheet
                .getSprite((int) (currentLife * framesPerTick) % spriteSheet.getHorizontalCount(), 0)
                .draw(renderX, renderY);
    }
}
