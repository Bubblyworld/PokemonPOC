package PokemonPOC.GameEntities;

import PokemonPOC.GameActions.GameAddEntityAction;
import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by guy on 8/4/15.
 * Creates an animation when the player steps on or off something.
 * This is used to create movement effects through grass, for instance.
 * Animations can be passed in as NULL, in which case they aren't displayed.
 *
 * TODO: how do we handle lifespan nicely here?
 *       perhaps we need an animation data wrapper or something somewhere.
 */
public class GameAnimatedTriggerEntity extends GameTriggerEntity {
    SpriteSheet onAnimation, offAnimation;
    float framesPerTick;
    float lifespan;

    public GameAnimatedTriggerEntity(float x, float y, float depth, float framesPerTick, SpriteSheet onAnimation, SpriteSheet offAnimation) {
        super(x, y, depth);

        this.framesPerTick = framesPerTick;
        this.onAnimation = onAnimation;
        this.offAnimation = offAnimation;

        this.lifespan = offAnimation.getHorizontalCount() * framesPerTick;
    }

    @Override
    public boolean playerStepsOn(GameWorld world, GamePlayerEntity player) {
        if (onAnimation != null) {
            world.actionQueue.add(
                    new GameAddEntityAction(
                            world.currentTick,
                            new GameAnimatedEntity(this.x, this.y, 2, onAnimation, framesPerTick, lifespan)
                    )
            );
        }

        return true;
    }

    @Override
    public boolean playerStepsOff(GameWorld world, GamePlayerEntity player) {
        if (offAnimation != null) {
            world.actionQueue.add(
                    new GameAddEntityAction(
                            world.currentTick,
                            new GameAnimatedEntity(this.x, this.y, 2, offAnimation, framesPerTick, lifespan)
                    )
            );
        }

        return true;
    }
}
