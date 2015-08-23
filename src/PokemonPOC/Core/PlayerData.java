package PokemonPOC.Core;

import PokemonPOC.Pokemon.Pokemon;

import java.util.ArrayList;

/**
 * Created by guy on 8/9/15.
 * Contains the data for a given player.
 */
public class PlayerData {
    String name;
    ArrayList<Pokemon> currentPokemon;

    public PlayerData(String name) {
        this.name = name;
        currentPokemon = new ArrayList<>();
    }

    /**
     * Get the first battle-worthy pokemon from the player's inventory.
     */
    public Pokemon getFirstPokemon() throws Exception {
        for (Pokemon pokemon : currentPokemon) {
            if (pokemon.stats.currentHp > 0)
                return pokemon;
        }

        throw new Exception("Player has no conscious pokemon.");
    }

    /**
     * Safely adds a pokemon to the player's inventory. If there is no space,
     * we send it to their PC.
     */
    public void addPokemon(Pokemon pokemon) {
        currentPokemon.add(pokemon);
        //TODO PC thing
    }
}
