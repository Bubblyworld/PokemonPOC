import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
Models the state where the player is walking around in the various
in-game areas.
*/
public class GameWalkingState extends BasicGameState {
    //State of game time variables, and action priority queue.
    public PriorityQueue<GameAction> actionQueue;
    public long currentTick;
    public float currentTickF;

    //List of game entities, and the game entity the camera centres on.
    public List<GameEntity> entities;
    public GameEntity camera;

    public void init(GameContainer container, StateBasedGame game) {
        currentTick = 0;
        actionQueue = new PriorityQueue<>();
        entities = new ArrayList<>();

        try {
            PalletTown.init(this, container);
        } catch(SlickException e) {
            System.err.println("Error initialising the GameWalkingState.");
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        for (GameEntity entity : entities) {
            entity.render(camera.x - Constants.SCREEN_WIDTH / 2, camera.y - Constants.SCREEN_HEIGHT / 2);
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

            action.update(currentTickF);
            if (!action.isComplete)
                unfinishedActions.add(action);
        }

        //Unfinished actions we push back onto the action queue.
        actionQueue.addAll(unfinishedActions);

        //Update entities.
        for (GameEntity entity : entities) {
            entity.update(currentTickF, deltaTicks, actionQueue);
        }
    }

    public void enter(GameContainer container, StateBasedGame game) {
    }

    public void leave(GameContainer container, StateBasedGame game) {
    }

    public int getID() {
        return Constants.GAME_WALKING_STATE_ID;
    }
}
