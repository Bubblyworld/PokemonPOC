package PokemonPOC.GameMapInits;

import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.SlickException;

/**
 * Created by guy on 8/2/15.
 * Base class for map initialisation code.
 * playerX and playerY determine where the player should be placed by a subclass
 * for this mapInit. isPlayerInitialised tells us if we should create a player npc
 * for this init.
 */
public abstract class GameMapInit {
    float playerX, playerY;
    boolean isPlayerInitialised;

    public GameMapInit() {
        this.isPlayerInitialised = false;
    }

    public GameMapInit(float playerX, float playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.isPlayerInitialised = true;
    }

    public abstract void initCollision(GameWorld world);
    public abstract void initMap(GameWorld world) throws SlickException;
    public abstract void initNpcs(GameWorld world) throws SlickException;
    public abstract void initTopSprites(GameWorld world) throws SlickException;
}
