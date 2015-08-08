package PokemonPOC.Core;

import PokemonPOC.Constants;
import PokemonPOC.Actions.Action;
import PokemonPOC.Entities.Entity;
import PokemonPOC.MapInits.MapInit;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by guy on 8/2/15.
 * Contains and updates all data relevant to the game world - tilemaps, npcs, player etc.
 */
public class World {
    //State of game time variables, and action priority queue.
    public PriorityQueue<Action> actionQueue;
    public long currentTick;
    public float currentTickF;

    //List of game entities, and the game entity the camera centres on.
    public List<Entity> entities;
    public Entity camera;

    //Collision map data.
    public CollisionMap collisionMap;

    //Player input.
    public Input playerInput;

    //Game font.
    public Font font;

    public World(GameContainer container) {
        currentTick = 0;
        actionQueue = new PriorityQueue<>();
        entities = new ArrayList<>();
        collisionMap = new CollisionMap();
        playerInput = container.getInput();

        //Load pokemon font.
        try {
            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("assets/pokemon.ttf"));
            font = new TrueTypeFont(awtFont.deriveFont(24F), false);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        //We sort the entities by depth&y, then we render them.
        entities.sort((entity1, entity2) -> {
            if (entity1.depth < entity2.depth) return -1;
            if (entity1.depth > entity2.depth) return +1;
            if (entity1.y < entity2.y) return -1;
            if (entity1.y > entity2.y) return +1;
            return 0;
        });

        for (Entity entity : entities) {
            entity.render(camera.x - Constants.SCREEN_WIDTH / 2, camera.y - Constants.SCREEN_HEIGHT / 2);
        }

        /**
         * DEBUG - render the collision boxes
         */
        graphics.setColor(Color.red);
        for (CollisionBox box : collisionMap.entities) {
            graphics.drawRect(
                    box.x1 - camera.x + Constants.SCREEN_WIDTH/2,
                    box.y1 - camera.y + Constants.SCREEN_HEIGHT/2,
                    box.x2 - box.x1,
                    box.y2 - box.y1
            );
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
        float deltaSecs = (float) delta / 1000.0f;
        float deltaTicks = deltaSecs / Constants.SEC_PER_TICK;

        currentTickF += deltaTicks;
        currentTick = (long) currentTickF;

        //Update action queue. We store a list of actions that are not done yet to
        // push back onto the queue.
        List<Action> unfinishedActions = new ArrayList<>();
        while(!actionQueue.isEmpty() && actionQueue.peek().activationTick <= currentTick) {
            Action action = actionQueue.poll();

            action.update(currentTickF, this, game);
            if (!action.isComplete)
                unfinishedActions.add(action);
        }

        //Unfinished actions we push back onto the action queue.
        actionQueue.addAll(unfinishedActions);

        //Update entities, and clear out dead ones.
        for (Entity entity : entities) {
            if (!entity.update(currentTickF, deltaTicks, this))
                break;
        }

        entities.removeIf((entity) -> entity.isDead);
    }

    /**
     * Parameters for optional placement of this instance's player sprite.
     */
    public void enterMaps(List<MapInit> mapInits) {
        entities.clear();
        actionQueue.clear();
        collisionMap.clear();
        camera = null;

        try {
            for (MapInit init : mapInits) init.initMap(this);
            for (MapInit init : mapInits) init.initNpcs(this);
            for (MapInit init : mapInits) init.initTopSprites(this);
            for (MapInit init : mapInits) init.initCollision(this);
        } catch(SlickException e) {
            System.err.println("Error loading map inits.");
        }
    }
}
