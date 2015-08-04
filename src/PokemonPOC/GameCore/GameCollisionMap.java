package PokemonPOC.GameCore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guy on 8/2/15.
 * Contains collision data for the current map. Positions can be polled for whether
 * they are within a collision area or not.
 */
public class GameCollisionMap {
    public List<GameCollisionBox> entities;

    public GameCollisionMap() {
        entities = new ArrayList<>();
    }

    public void addCollisionBox(float x1, float y1, float x2, float y2) {
        entities.add(new GameCollisionBox(x1, y1, x2, y2));
    }

    public boolean isCollision(float x, float y) {
        for (GameCollisionBox entity : entities) {
            if (entity.isCollision(x, y))
                return true;
        }

        return false;
    }

    public void clear() {
        entities.clear();
    }
}
