package PokemonPOC.GameEntities;

import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.Image;

/**
 * Created by guy on 8/2/15.
 * Models a static image entity.
 */
public class GameStaticEntity extends GameEntity {
    Image image;

    public GameStaticEntity(float x, float y, float depth, Image image) {
        super(x, y, depth);

        this.image = image;
    }

    @Override
    public boolean update(float currentTickF, float tickDelta, GameWorld world) {
        return true;
    }

    @Override
    public void render(float cameraX, float cameraY) {
        float renderX = x - cameraX;
        float renderY = y - cameraY;

        image.draw(renderX, renderY);
    }
}
