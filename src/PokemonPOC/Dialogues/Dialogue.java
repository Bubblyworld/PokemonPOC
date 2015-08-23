package PokemonPOC.Dialogues;

import PokemonPOC.Core.World;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guy on 8/4/15.
 * Models a dialogue from an NPC to the player.
 * Essentially, a dialogue is a tree - each node has a string of text associated with it,
 * and depending on the kind of dialogue has some sort of optional procedure to grab the next
 * textual link in the chain.
 */
public abstract class Dialogue {
    Dialogue next;
    Font font;

    public Dialogue(Dialogue next, Font font) {
        this.next = next;
        this.font = font;
    }

    /**
     * Updates the given dialogue. Returns the dialogue that continues the script - it is usually
     * just the current Dialogue, but in the case of an option might be the result of the script etc.
     * If this ever returns null, the dialogue is considered over.
     * @param tickDelta Time since last update in ticks,
     * @param world The game world in the background.
     * @return The dialogue to continue the script with. Usually `this`.
     */
    public abstract Dialogue update(float tickDelta, World world);

    /**
     * Reset the dialogue for next time.
     */
    public abstract void reset();

    /**
     * Renders the dialogue within the given box.
     */
    public abstract void render(Graphics graphics, float x1, float y1, float x2, float y2);

    /**
     * Util method. Given a string, breaks it up into partial sentences such that the
     * width of each sentence is at most the given width.
     */
    public List<String> splitText(String text, float maxWidth) {
        List<String> result = new ArrayList<>();

        String[] words = text.split(" ");
        String currentWord = words[0];
        for (int i = 1; i < words.length; i++) {
            String word = words[i];

            if (font.getWidth(currentWord + " " + word) <= maxWidth)
                currentWord += " " + word;
            else {
                result.add(currentWord);
                currentWord = word;
            }
        }

        result.add(currentWord);
        return result;
    }
}
