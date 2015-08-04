package PokemonPOC.GameCore;

import PokemonPOC.Constants;
import PokemonPOC.GameActions.GameAction;
import PokemonPOC.GameEntities.GameEntity;
import PokemonPOC.GameMapInits.GameMapInit;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by guy on 8/2/15.
 * Contains and updates all data relevant to the game world - tilemaps, npcs, player etc.
 */
public class GameWorld {
    //State of game time variables, and action priority queue.
    public PriorityQueue<GameAction> actionQueue;
    public long currentTick;
    public float currentTickF;

    //List of game entities, and the game entity the camera centres on.
    public List<GameEntity> entities;
    public GameEntity camera;

    //Collision map data.
    public GameCollisionMap collisionMap;

    //Player input.
    public Input playerInput;

    public GameWorld(GameContainer container) {
        currentTick = 0;
        actionQueue = new PriorityQueue<>();
        entities = new ArrayList<>();
        collisionMap = new GameCollisionMap();
        playerInput = container.getInput();
    }

    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        //We sort the entities by depth, then we render them.
        entities.sort((entity1, entity2) -> {
            if (entity1.depth < entity2.depth) return -1;
            if (entity1.depth > entity2.depth) return +1;
            return 0;
        });

        for (GameEntity entity : entities) {
            entity.render(camera.x - Constants.SCREEN_WIDTH / 2, camera.y - Constants.SCREEN_HEIGHT / 2);
        }

        /**
         * DEBUG - render the collision boxes
         */
        graphics.setColor(Color.red);
        for (GameCollisionBox box : collisionMap.entities) {
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
        List<GameAction> unfinishedActions = new ArrayList<>();
        while(!actionQueue.isEmpty() && actionQueue.peek().activationTick <= currentTick) {
            GameAction action = actionQueue.poll();

            action.update(currentTickF, this);
            if (!action.isComplete)
                unfinishedActions.add(action);
        }

        //Unfinished actions we push back onto the action queue.
        actionQueue.addAll(unfinishedActions);

        //Update entities, and clear out dead ones.
        for (GameEntity entity : entities) {
            if (!entity.update(currentTickF, deltaTicks, this))
                break;
        }

        entities.removeIf((entity) -> entity.isDead);
    }

    public void enterMaps(List<GameMapInit> mapInits) {
        enterMaps(mapInits, 0, 0);
    }

    /**
     * Parameters for optional placement of this instance's player sprite.
     */
    public void enterMaps(List<GameMapInit> mapInits, float cameraX, float cameraY) {
        entities.clear();
        actionQueue.clear();
        collisionMap.clear();
        camera = null;

        try {
            for (GameMapInit init : mapInits) init.initMap(this);
            for (GameMapInit init : mapInits) init.initNpcs(this);
            for (GameMapInit init : mapInits) init.initTopSprites(this);
            for (GameMapInit init : mapInits) init.initCollision(this);
        } catch(SlickException e) {
            System.err.println("Error loading map inits.");
        }
    }
}
