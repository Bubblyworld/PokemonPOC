package PokemonPOC.Entities;

import PokemonPOC.Core.World;
import org.newdawn.slick.Image;

/**
 * Created by guy on 8/2/15.
 * Models a static image entity.
 */
public class StaticEntity extends Entity {
    Image image;

    public StaticEntity(float x, float y, float depth) {
        this(x, y, depth, null);
    }

    public StaticEntity(float x, float y, float depth, Image image) {
        super(x, y, depth);

        this.image = image;
    }

    @Override
    public Entity clone() {
        return new StaticEntity(x, y, depth, image);
    }

    @Override
    public boolean update(float currentTickF, float tickDelta, World world) {
        return true;
    }

    @Override
    public void render(float cameraX, float cameraY) {
        float renderX = x - cameraX;
        float renderY = y - cameraY;

        if (image != null)
            image.draw(renderX, renderY);
    }
}
