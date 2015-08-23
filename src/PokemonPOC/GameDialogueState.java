package PokemonPOC;

import PokemonPOC.Core.World;
import PokemonPOC.Dialogues.Dialogue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by guy on 8/4/15.
 * When a player is talking to an npc, we get this state.
 * We need the World entity to render things in the background while they talk.
 * We also need a Talk object to give us the dialogue and
 */
public class GameDialogueState extends BasicGameState {
    Image dialogueImage;

    Dialogue currentDialogue;
    World world;

    @Override
    public int getID() {
        return Constants.GAME_DIALOGUE_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        currentDialogue = null;
        world = null;

        dialogueImage = new Image("assets/dialogue.png");
    }

    public void updateState(Dialogue dialogue, World world) {
        this.currentDialogue = dialogue;
        this.world = world;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //Render the world if we have it
        if (world != null)
            world.render(container, game, g);

        //Render the dialogue image.
        dialogueImage.draw(0, Constants.SCREEN_HEIGHT - 110);

        //Render the current dialogue if it exists.
        if (currentDialogue != null) {
            currentDialogue.render(
                    g,
                    30,
                    Constants.SCREEN_HEIGHT - 100,
                    Constants.SCREEN_WIDTH - 30,
                    Constants.SCREEN_HEIGHT - 10
            );
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        //Update the current dialogue if it exists, otherwise jump back to in-game.
        if (currentDialogue != null && world != null) {
            float deltaSecs = (float) delta / 1000.0f;
            float deltaTicks = deltaSecs / Constants.SEC_PER_TICK;

            currentDialogue = currentDialogue.update(deltaTicks, world);
        } else
            game.enterState(Constants.GAME_WALKING_STATE_ID);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        //Give us a clean input slate.
        world.playerInput.clearMousePressedRecord();
        world.playerInput.clearKeyPressedRecord();
    }
}
