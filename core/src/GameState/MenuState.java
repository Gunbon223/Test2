package GameState;

import Sprites.Bird;
import Utility.Constrain;
import Utility.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class MenuState extends State {
    private Texture background;
    private Texture btnPlay;
    private Bird bird;
    private HUD hud;

    public MenuState(StateManager stateManager) {
        super(stateManager);
        background = new Texture("background.png");
        btnPlay = new Texture("playbtn.png");
        menuCamera.setToOrtho(false,Constrain.WIDTH,Constrain.HEIGHT);
        bird = new Bird((Constrain.WIDTH / 2)-20,Constrain.HEIGHT/2-25);
        hud = new HUD(50, Color.WHITE,menuCamera.viewportWidth, menuCamera.viewportHeight);
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
        bird.getBirdAnimation().update(deltaTime);

        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(menuCamera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0 ,Constrain.WIDTH,Constrain.HEIGHT);
        spriteBatch.draw(btnPlay,(Constrain.WIDTH/2) - (btnPlay.getWidth()/2),(Constrain.HEIGHT/3));
        spriteBatch.draw(bird.getImg(),bird.getPosition().x,bird.getPosition().y, bird.getImg().getRegionWidth()*Constrain.SCALE,bird.getImg().getRegionHeight()*Constrain.SCALE);
        hud.getFont().draw(spriteBatch, "Flappy Bird", hud.getHudCentreX()-hud.getFont().getLineHeight()*2f, hud.getHudRowTop(),hud.getHudWidth() , Align.left, false);
        spriteBatch.end();
    }
    @Override
    public void dispose() {
        background.dispose();
        btnPlay.dispose();
    }

}
