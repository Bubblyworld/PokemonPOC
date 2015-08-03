package PokemonPOC;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Game());
            container.setDisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);
            container.start();
        } catch (SlickException e) {
            System.err.println("Error initialising pokemon demo:");
            System.err.println(e.getStackTrace());
        }
    }
}
