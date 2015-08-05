package PokemonPOC.Entities;

import PokemonPOC.Actions.DialogueAction;
import PokemonPOC.Core.World;
import PokemonPOC.Dialogues.Dialogue;
import org.newdawn.slick.Input;

/**
 * Created by guy on 8/5/15.
 * Triggers a dialogue when the player faces it and presses the respective command.
 */
public class DialogueDecorator extends EntityDecorator {
    Dialogue dialogue;

    public DialogueDecorator(Entity entity, Dialogue dialogue) {
        super(entity);

        this.dialogue = dialogue;
    }

    @Override
    public boolean update(float currentTickF, float tickDelta, World world) {
        if (!super.update(currentTickF, tickDelta, world))
            return false;

        for (Entity entity : world.entities) {
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;

                //If they are facing us, and pressing Z, trigger the dialogue.
                if (player.isFacing(this.entity.x, this.entity.y)) {
                    if (player.playerInput.isKeyDown(Input.KEY_Z))
                        world.actionQueue.add(new DialogueAction(world.currentTick, dialogue));
                }
            }
        }

        return true;
    }
}
