package PokemonPOC.Actions;

import PokemonPOC.Constants;
import PokemonPOC.Core.World;
import PokemonPOC.Dialogues.Dialogue;
import PokemonPOC.GameDialogueState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by guy on 8/4/15.
 * Special action recognised by the game World as triggering a dialogue scene.
 */
public class DialogueAction extends Action {
    public Dialogue dialogue;

    public DialogueAction(long activationTick, Dialogue dialogue) {
        super(activationTick);

        this.dialogue = dialogue;
        dialogue.reset();
    }

    @Override
    public void update(float currentTickF, World world, StateBasedGame game) {
        GameDialogueState dialogueState =
                (GameDialogueState) game.getState(Constants.GAME_DIALOGUE_STATE_ID);

        dialogueState.updateDialogue(dialogue, world);
        game.enterState(Constants.GAME_DIALOGUE_STATE_ID);

        this.setComplete();
    }
}
