package PokemonPOC.Core;

import PokemonPOC.Displays.Display;
import PokemonPOC.Pokemon.Pokemon;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guy on 2015/08/23.
 * Models all the data relevant in a battle, much like World models all the data relevant to the
 * in-game walking around screen.
 */
public class Battle {
    //Player one is the data for the current process' player, and the pokemon represent the pokemon currently in play.
    public PlayerData playerOne, playerTwo;
    public Pokemon pokemonOne, pokemonTwo;

    //List of current displays for the battle.
    public List<Display> displays;

    public Battle(PlayerData playerOne, PlayerData playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        try {
            this.pokemonOne = playerOne.getFirstPokemon();
            this.pokemonTwo = playerTwo.getFirstPokemon();
        } catch (Exception e) {
            e.printStackTrace();
        }

        displays = new ArrayList<>();
        //TODO: get first pokemon from the player's store
    }

    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        for (Display display : displays)
            display.render(graphics);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
        float deltaF = (float) delta / 1000.0f;

        for (Display display : displays)
            display.update(deltaF, this);
    }
}
