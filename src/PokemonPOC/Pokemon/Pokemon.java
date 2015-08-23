package PokemonPOC.Pokemon;

import org.newdawn.slick.SpriteSheet;

/**
 * Created by guy on 8/9/15.
 * Models core stats and information for a given pokemon.
 * The battle spritesheet contains two sprites of size 128x128, first one facing the camera
 * and the second facing away. These are used for battle rendering.
 */
public class Pokemon {
    public String name;
    public PokemonStats stats;
    public SpriteSheet battleSheet;

    public Pokemon(String name, PokemonStats stats, SpriteSheet battleSheet) {
        this.name = name;
        this.stats = stats;
        this.battleSheet = battleSheet;
    }
}