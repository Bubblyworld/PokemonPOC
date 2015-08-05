package PokemonPOC.Entities;

import PokemonPOC.Actions.AddEntityAction;
import PokemonPOC.Core.World;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by guy on 8/4/15.
 * Creates an animation when the player steps on or off something.
 * This is used to create movement effects through grass, for instance.
 * Animations can be passed in as NULL, in which case they aren't displayed.
 */
public class AnimatedTriggerEntity extends TriggerEntity {
    SpriteSheet onAnimation, offAnimation;
    float framesPerTick;
    float lifespan;

    public AnimatedTriggerEntity(float x, float y, float depth, float framesPerTick, SpriteSheet onAnimation, SpriteSheet offAnimation) {
        super(x, y, depth);

        this.framesPerTick = framesPerTick;
        this.onAnimation = onAnimation;
        this.offAnimation = offAnimation;

        this.lifespan = offAnimation.getHorizontalCount() * framesPerTick;
    }

    @Override
    public boolean playerStepsOn(World world, PlayerEntity player) {
        if (onAnimation != null) {
            world.actionQueue.add(
                    new AddEntityAction(
                            world.currentTick,
                            new AnimatedEntity(this.x, this.y, 2, onAnimation, framesPerTick, lifespan)
                    )
            );
        }

        return true;
    }

    @Override
    public boolean playerStepsOff(World world, PlayerEntity player) {
        if (offAnimation != null) {
            world.actionQueue.add(
                    new AddEntityAction(
                            world.currentTick,
                            new AnimatedEntity(this.x, this.y, 2, offAnimation, framesPerTick, lifespan)
                    )
            );
        }

        return true;
    }
}
