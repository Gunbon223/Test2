package GameState;

import Sprites.Bird;
import Utility.Constrain;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gunbon.game.FlappyGame;

public class PlayState extends State{

    private Bird bird;


    public PlayState(StateManager stateManager) {
        super(stateManager);
        camera.setToOrtho(false, Constrain.WIDTH/2,Constrain.HEIGHT/2);
        bird = new Bird(50,50);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    spriteBatch.begin();
    spriteBatch.draw(bird.getBirdImg(),bird.getPosition().x ,bird.getPosition().y);
    spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
