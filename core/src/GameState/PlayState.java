package GameState;

import Sprites.Bird;
import Sprites.Tube;
import Utility.Constrain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.gunbon.game.FlappyGame;

import java.util.*;

public class PlayState extends State{
    private static final int TUBE_SPACING=150;
    private static final int TUBE_COUNT=4;

    private Bird bird;
    private Texture background;
    private  ArrayList<Tube> tubes;
    public final float scale = 1.4f;
    public PlayState(StateManager stateManager) {
        super(stateManager);
        camera.setToOrtho(false, (float) (Constrain.WIDTH/scale), (float) (Constrain.HEIGHT/scale));
        bird = new Bird(Constrain.HEIGHT/4-80,Constrain.WIDTH/2);
        background = new Texture("background.png");
        tubes = new ArrayList<>();
        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)+400));
        }
        System.out.println("ps create");
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
        // bird position
        bird.update(deltaTime);
        camera.position.x=bird.getPosition().x +80;
        // tube repostion when out of left screen to the right screen
        for(Tube i :tubes) {
            if (camera.position.x - (camera.viewportWidth/2) > i.getPositionTop().x + i.getTopTube().getWidth() ) {
                i.reposition(i.getPositionTop().x+((Tube.TUBE_WIDTH+TUBE_SPACING)*TUBE_COUNT));
            }
            if(i.collision(bird.getBirdRectangle())) {
                resetCameraToDefault();

                stateManager.setStates(new MenuState(stateManager));
                break;
            }
        }
        //bird max and min height  {
        if(bird.getPosition().y<=0) {
            bird.setPosition(new Vector3(bird.getPosition().x,0,0));
        }
        if (bird.getPosition().y>=background.getHeight()) {
            bird.setPosition(new Vector3(bird.getPosition().x,background.getHeight(),0));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0, Constrain.WIDTH / scale, Constrain.HEIGHT / scale);        spriteBatch.draw(bird.getBirdImg(),bird.getPosition().x ,bird.getPosition().y);
        //render tube pos
        for (Tube i :tubes) {
            spriteBatch.draw(i.getTopTube(),i.getPositionTop().x,i.getPositionTop().y);
            spriteBatch.draw(i.getBottomTube(),i.getPositionBot().x,i.getPositionBot().y);

        }

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for (Tube i: tubes) {
            i.dispose();
        }
        System.out.println("ps dispose");
    }

    public Texture getBackground() {
        return background;
    }
}
