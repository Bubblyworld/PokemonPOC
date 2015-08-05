package PokemonPOC.Core;

/**
 * Created by guy on 8/4/15.
 * A collision box. Exactly that.
 */
public class CollisionBox {
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
