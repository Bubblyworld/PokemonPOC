package PokemonPOC;

import PokemonPOC.Core.Battle;
import PokemonPOC.Core.PlayerData;
import PokemonPOC.Displays.BackgroundDisplay;
import PokemonPOC.Displays.DialogueDisplay;
import PokemonPOC.Displays.PokemonDisplay;
import PokemonPOC.Displays.StatusBarsDisplay;
import PokemonPOC.Pokemon.Pokemon;
import PokemonPOC.Pokemon.PokemonStats;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by guy on 8/9/15.
 * Models the battle state in which a player selects their choice of action.
 */
public class BattleSelectState extends BasicGameState {
    Battle battle;

    @Override
    public int getID() {
        return Constants.BATTLE_SELECT_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        //TODO do this properly, DEBUG

        //Create a new pokemon.
        PokemonStats pokeStats = new PokemonStats();
        Pokemon squirtle = new Pokemon("Squirtle", pokeStats, new SpriteSheet("assets/pokemon_squirtle.png", 128, 128));
        PlayerData guyData = new PlayerData("Guy");
        PlayerData sineadData = new PlayerData("Sinead");
        guyData.addPokemon(squirtle);
        sineadData.addPokemon(squirtle);

        battle = new Battle(guyData, sineadData);
        battle.displays.add(new BackgroundDisplay(new Image("assets/battle_grass.png")));
        battle.displays.add(new DialogueDisplay());
        battle.displays.add(new StatusBarsDisplay(battle));
        battle.displays.add(new PokemonDisplay(battle));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        battle.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        battle.update(container, game, delta);
    }
}
