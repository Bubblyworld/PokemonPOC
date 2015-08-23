package PokemonPOC.Displays;

import PokemonPOC.Core.Battle;
import org.newdawn.slick.Graphics;

/**
 * Created by guy on 2015/08/23.
 * Represents one of the various displays in a battle screen.
 * These include the attack animations, player's pokemon, status bars, and
 * selection screens.
 */
public abstract class Display {
    public abstract void update(float delta, Battle battle);
    public abstract void render(Graphics graphics);
}
