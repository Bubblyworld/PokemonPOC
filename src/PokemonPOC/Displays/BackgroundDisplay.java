package PokemonPOC.Displays;

import PokemonPOC.Core.Battle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by guy on 2015/08/23.
 * Renders a battle background.
 */
public class BackgroundDisplay extends Display {
    Image backgroundImage;

    public BackgroundDisplay(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public void update(float delta, Battle battle) {
    }

    @Override
    public void render(Graphics graphics) {
        backgroundImage.draw(0, 0);
    }
}
