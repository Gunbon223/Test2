package GameState;

import Utility.Constrain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {
    private Texture background;
    private Texture btnPlay;
    private Texture bird;
    public MenuState(StateManager stateManager) {
        super(stateManager);
        background = new Texture("background.png");
        btnPlay = new Texture("playbtn.png");
        menuCamera.setToOrtho(false,Constrain.WIDTH,Constrain.HEIGHT);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            stateManager.setStates(new PlayState(stateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        camera.update();
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(menuCamera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0 ,Constrain.WIDTH,Constrain.HEIGHT);
        spriteBatch.draw(btnPlay,(Constrain.WIDTH/2) - (btnPlay.getWidth()/2),(Constrain.HEIGHT/3));
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        btnPlay.dispose();
    }

}
