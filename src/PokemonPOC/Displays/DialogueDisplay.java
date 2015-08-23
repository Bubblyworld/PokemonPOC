package PokemonPOC.Displays;

import PokemonPOC.Core.Battle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by guy on 2015/08/23.
 * Renders the dialogue box on the bottom of the battle screen.
 * The message displayed can be set and updated.
 */
public class DialogueDisplay extends Display {
    Image dialogueImage;

    public DialogueDisplay() {
        try {
            dialogueImage = new Image("assets/battle_dialogue.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float delta, Battle battle) {
    }

    @Override
    public void render(Graphics graphics) {
        dialogueImage.draw(0, 224);
    }
}
