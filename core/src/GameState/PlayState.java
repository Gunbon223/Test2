package GameState;

import Sprites.Bird;
import Utility.Constrain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gunbon.game.FlappyGame;

public class PlayState extends State{

    private Bird bird;
    private Texture background;

    public PlayState(StateManager stateManager) {
        super(stateManager);
        camera.setToOrtho(false, Constrain.WIDTH/2,Constrain.HEIGHT/2);
        bird = new Bird(50,350);
        background = new Texture("background.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        bird.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,camera.position.x-(camera.viewportWidth/2),0);
        spriteBatch.draw(bird.getBirdImg(),bird.getPosition().x ,bird.getPosition().y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
