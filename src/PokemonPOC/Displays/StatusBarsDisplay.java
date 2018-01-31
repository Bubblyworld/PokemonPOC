package PokemonPOC.Displays;

import PokemonPOC.Core.Battle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by guy on 2015/08/23.
 * Renders the status bars of both players' pokemon in battle.
 * Includes hp, level, name and effects.
 */
public class StatusBarsDisplay extends Display {
    //Relevant images
    Image playerOneStats, playerTwoStats;

    //Reference to the current battle.
    Battle battle;

    public StatusBarsDisplay(Battle battle) {
        this.battle = battle;

        try {
            playerOneStats = new Image("assets/battle_p1_stats.png");
            playerTwoStats = new Image("assets/battle_p2_stats.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float delta, Battle battle) {
        this.battle = battle;
    }

    @Override
    public void render(Graphics graphics) {
        //Draw the status overlays.
        playerOneStats.draw(252, 148);
        playerTwoStats.draw(26, 31);

        //Draw the pokemon names.
        graphics.setColor(Color.black);
        graphics.setFont(battle.font);

        graphics.drawString(battle.pokemonTwo.name, 40, 42);
        graphics.drawString(battle.pokemonOne.name, 285, 158);
    }
}
