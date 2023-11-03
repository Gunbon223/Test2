package GameState;

import Utility.Constrain;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayState extends State{
    private Texture bird;


    public PlayState(StateManager stateManager) {
        super(stateManager);
        bird = new Texture("bird.png");
        camera.setToOrtho(false, Constrain.WIDTH/2,Constrain.HEIGHT/2);

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
    spriteBatch.draw(bird,50,50);
    spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
