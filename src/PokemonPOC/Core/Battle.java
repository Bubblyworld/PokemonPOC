package PokemonPOC.Core;

import PokemonPOC.Displays.Display;
import PokemonPOC.Pokemon.Pokemon;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
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

    //Battle font.
    public Font font;

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

        //Load battle font.
        try {
            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("assets/pokemon.ttf"));
            font = new TrueTypeFont(awtFont.deriveFont(24F), false);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
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
