package PokemonPOC.MapInits;

import PokemonPOC.Core.World;
import org.newdawn.slick.SlickException;

/**
 * Created by guy on 8/2/15.
 * Base class for map initialisation code.
 * playerX and playerY determine where the player should be placed by a subclass
 * for this mapInit. isPlayerInitialised tells us if we should create a player npc
 * for this init.
 */
public abstract class MapInit {
    float playerX, playerY;
    boolean isPlayerInitialised;

    public MapInit() {
        this.isPlayerInitialised = false;
    }

    public MapInit(float playerX, float playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.isPlayerInitialised = true;
    }

    public abstract void initCollision(World world);
    public abstract void initMap(World world) throws SlickException;
    public abstract void initNpcs(World world) throws SlickException;
    public abstract void initTopSprites(World world) throws SlickException;
}
