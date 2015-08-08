package PokemonPOC.Dialogues;

import PokemonPOC.Core.World;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import java.awt.*;
import java.util.List;

/**
 * Created by guy on 8/4/15.
 * Standard text dialogue - no dialogue options, just pure rolling text.
 * We link into the next dialogue via the next variable.
 */
public class TextDialogue extends Dialogue {
    public Dialogue next;

    //Text, current position, and number of positions shown per tick.
    public String text;
    public float currentIndex;
    public float indicesPerTick;

    public TextDialogue(Font font, String text, float indicesPerTick) {
        super(font);

        this.next = null;
        this.text = text;
        this.indicesPerTick = indicesPerTick;
        this.currentIndex = 0;
    }

    /**
     * Add the given dialogue as the next in the chain after this one.
     */
    public Dialogue setNext(Dialogue next) {
        this.next = next;

        return this;
    }

    @Override
    public Dialogue update(float tickDelta, World world) {
        //If the player is holding down Z, we move forward twice as fast.
        float indicesPerTickPrime = indicesPerTick;
        if (world.playerInput.isKeyDown(Input.KEY_Z))
            indicesPerTickPrime *= 2;

        //Move the index forward, clamp it to the text length.
        currentIndex += tickDelta * indicesPerTickPrime;
        if (currentIndex > text.length())
            currentIndex = text.length();

        //If we are at the end of the text, pressing Z continues to the next in chain.
        if (currentIndex >= text.length()) {
            if (world.playerInput.isKeyPressed(Input.KEY_Z))
                return next;
        } else {
            //Reset the record so it doesn't persist till the end of the dialogue.
            world.playerInput.clearKeyPressedRecord();
        }

        return this;
    }

    @Override
    public void reset() {
        currentIndex = 0;

        if (next != null) next.reset();
    }

    @Override
    public void render(Graphics graphics, float x1, float y1, float x2, float y2) {
        graphics.setColor(Color.black);
        graphics.setFont(font);

        //Grab the lines to render.
        float width = x2 - x1;
        List<String> lines = this.splitText(text, width);

        //Index tells us how much we have printed thus far.
        float index = 0;
        float currentY = y1;
        for (String line : lines) {
            if (index + line.length() < currentIndex) {
                graphics.drawString(line, x1, currentY);

                index += line.length();
                currentY += font.getHeight(line);
            } else {
                graphics.drawString(line.substring(0, (int) (currentIndex - index)), x1, currentY);

                break;
            }
        }
    }
}
