package PokemonPOC;

public class Constants {
    public static final int GAME_WALKING_STATE_ID = 0;

    //We make a distinction between tiles(16x16) and blocks(32x32).
    //Blocks represent what visibly appears to be a tile, but the pokemon engine
    //renders it individually internally.
    public static final float SEC_PER_TICK = 0.20f;
    public static final float NPC_PIX_PER_TICK = 16.0f;
    public static final float BLOCK_SIZE = 16.0f;

    //The amount of time between a new tick and current tick before player
    // has to wait till the next tick to move.
    public static final float NPC_MOVEMENT_EPSILON = 0.1f;

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 400;

    public static final float INFINITY = 999999999.0f;
}
