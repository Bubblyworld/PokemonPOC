package PokemonPOC.GameMapInits;

import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.SlickException;

/**
 * Created by guy on 8/2/15.
 * Base class for map initialisation code.
 */
public abstract class GameMapInit {
    public abstract void initCollision(GameWorld world);
    public abstract void initMap(GameWorld world) throws SlickException;
    public abstract void initNpcs(GameWorld world) throws SlickException;
    public abstract void initTopSprites(GameWorld world) throws SlickException;
}
