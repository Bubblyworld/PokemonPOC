package PokemonPOC.Displays;

import PokemonPOC.Core.Battle;
import PokemonPOC.Pokemon.Pokemon;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by guy on 2015/08/23.
 * Displays the two players' pokemon in their respective places.
 */
public class PokemonDisplay extends Display {
    Battle battle;

    public PokemonDisplay(Battle battle) {
        this.battle = battle;
    }

    @Override
    public void update(float delta, Battle battle) {
        this.battle = battle;
    }

    @Override
    public void render(Graphics graphics) {
        //Render the players pokemon correctly.
        Image pokemonOneImage = battle.pokemonOne.battleSheet.getSprite(1, 0);
        Image pokemonTwoImage = battle.pokemonTwo.battleSheet.getSprite(0, 0);

        pokemonOneImage.draw(100, 96);
        pokemonTwoImage.draw(274, 40);
    }
}
