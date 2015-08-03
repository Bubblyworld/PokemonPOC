package PokemonPOC.GameCore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guy on 8/2/15.
 * Contains collision data for the current map. Positions can be polled for whether
 * they are within a collision area or not.
 */
public class GameCollisionMap {
    List<CollisionBox> entities;

    public GameCollisionMap() {
        entities = new ArrayList<>();
    }

    public void addCollisionBox(float x1, float y1, float x2, float y2) {
        entities.add(new CollisionBox(x1, y1, x2, y2));
    }

    public boolean isCollision(float x, float y) {
        for (CollisionBox entity : entities) {
            if (entity.isCollision(x, y))
                return true;
        }

        return false;
    }

    public void clear() {
        entities.clear();
    }

    class CollisionBox {
        float x1, x2, y1, y2;

        public CollisionBox(float x1, float y1, float x2, float y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public boolean isCollision(float x, float y) {
            if (x < x1 || x >= x2) return false;
            if (y < y1 || y >= y2) return false;

            return true;
        }
    }
}
