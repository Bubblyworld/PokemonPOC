package PokemonPOC.GameEntities;

import PokemonPOC.Constants;
import PokemonPOC.GameCore.GameWorld;
import org.newdawn.slick.Image;

//A tilemap entity of a given height and size.
public class GameTileMapEntity extends GameEntity {
    //TODO: actually make it a tilemap
    //For now this actually just tiles the image for days
    Image image;
    int xCopies, yCopies;

    public GameTileMapEntity(float x, float y, float depth, Image image, int xCopies, int yCopies) {
        super(x, y, depth);

        this.image = image;
        this.xCopies = xCopies;
        this.yCopies = yCopies;
    }

    public void update(float currentTickF, float tickDelta, GameWorld world) {
    }

    public void render(float cameraX, float cameraY) {
        float renderX = x - cameraX;
        float renderY = y - cameraY;

        //To optimise this code, we need to figure out the rendering bounds.
        int renderStartX = (int) (cameraX - x) / image.getWidth();
        int renderStartY = (int) (cameraY - y) / image.getHeight();
        int renderEndX = (int) (cameraX + Constants.SCREEN_WIDTH - x) / image.getWidth() + 1;
        int renderEndY = (int) (cameraY + Constants.SCREEN_HEIGHT - y) / image.getHeight() + 1;

        //Clamp the rendering bounds to allowable values.
        renderStartX = clamp(renderStartX, 0, xCopies);
        renderEndX = clamp(renderEndX, 0, xCopies);
        renderStartY = clamp(renderStartY, 0, yCopies);
        renderEndY = clamp(renderEndY, 0, yCopies);

        for (int y = renderStartY; y < renderEndY; y++) {
            for (int x = renderStartX; x < renderEndX; x++) {
                image.draw(renderX + x*image.getWidth(), renderY + y*image.getHeight());
            }
        }
    }

    /**
     * Clamps x to within the given range, inclusive.
     */
    public int clamp(int x, int start, int end) {
        if (x < start) x = start;
        if (x > end) x = end;

        return x;
    }
}
