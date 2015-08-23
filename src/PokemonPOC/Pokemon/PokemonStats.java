package PokemonPOC.Pokemon;

/**
 * Created by guy on 2015/08/23.
 * Models the stats of a given pokemon.
 */
public class PokemonStats {
    public int hp, currentHp;
    public int attack, currentAttack;
    public int defense, currentDefense;
    public int specialAttack, currentSpecialAttack;
    public int specialDefense, currentSpecialDefense;
    public int speed, currentSpeed;

    /**
     * Default constructor. Sets them all to basic values.
     */
    public PokemonStats() {
        this(10, 10, 10, 10, 10, 10);
    }

    public PokemonStats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        resetStats();
    }

    /**
     * Reset the current stats to their normal values.
     */
    public void resetStats() {
        currentHp = hp;
        currentAttack = attack;
        currentDefense = defense;
        currentSpecialAttack = specialAttack;
        currentSpecialDefense = specialDefense;
        currentSpeed = speed;
    }
}
