package PokemonPOC.Actions;

import PokemonPOC.Core.World;
import PokemonPOC.Dialogues.Dialogue;

/**
 * Created by guy on 8/4/15.
 * Special action recognised by the game World as triggering a dialogue scene.
 */
public class DialogueAction extends Action {
    public Dialogue dialogue;

    public DialogueAction(long activationTick, Dialogue dialogue) {
        super(activationTick);

        this.dialogue = dialogue;
    }

    @Override
    public void update(float currentTickF, World world) {
        this.setComplete();
    }
}
