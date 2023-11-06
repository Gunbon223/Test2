package GameState;

import Sprites.Bird;
import Sprites.Tube;
import Utility.Constrain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gunbon.game.FlappyGame;

import java.util.*;

public class PlayState extends State{
    private static final int TUBE_SPACING=150;
    private static final int TUBE_COUNT=4;
    private static final int GROUND_Y_OFFSCREEN=-50;

    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1,groundPosition2;
    private  ArrayList<Tube> tubes;

    public final float scale = 1.4f;
    public PlayState(StateManager stateManager) {
        super(stateManager);
        camera.setToOrtho(false, (float) (Constrain.WIDTH/scale), (float) (Constrain.HEIGHT/scale));
        bird = new Bird(50,50);
        background = new Texture("background.png");
        ground = new Texture("ground.png");
        tubes = new ArrayList<>();
        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)+400));
        }
        groundPosition1 = new Vector2(camera.position.x - camera.viewportWidth / 2 , GROUND_Y_OFFSCREEN);
        groundPosition2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSCREEN);
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
        camera.position.x=bird.getPosition().x +50;
        // ground add position
        updateGround();
        // tube repostion when out of left screen to the right screen
        for(Tube i :tubes) {
            if (camera.position.x - (camera.viewportWidth/2) > i.getPositionTop().x + i.getTopTube().getWidth() ) {
                i.reposition(i.getPositionTop().x+((Tube.TUBE_WIDTH+TUBE_SPACING)*TUBE_COUNT));
            }
            if(i.collision(bird.getBirdRectangle())) {
                stateManager.setStates(new MenuState(stateManager));
                break;
            }
        }
        //bird max and min height  {
        if(bird.getPosition().y<=(ground.getHeight()+GROUND_Y_OFFSCREEN)) {
            bird.setPosition(new Vector3(bird.getPosition().x,ground.getHeight()+GROUND_Y_OFFSCREEN,0));
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
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0, Constrain.WIDTH / scale, Constrain.HEIGHT / scale);
        spriteBatch.draw(bird.getBirdImg(),bird.getPosition().x ,bird.getPosition().y);
        //render tube
        for (Tube i :tubes) {
            spriteBatch.draw(i.getTopTube(),i.getPositionTop().x,i.getPositionTop().y);
            spriteBatch.draw(i.getBottomTube(),i.getPositionBot().x,i.getPositionBot().y);
        }
        //render ground
        spriteBatch.draw(ground,groundPosition1.x,groundPosition1.y);
        spriteBatch.draw(ground,groundPosition2.x,groundPosition2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube i: tubes) {
            i.dispose();
        }
        System.out.println("ps dispose");
    }

    private void updateGround() {
        groundPosition1.x = camera.position.x - camera.viewportWidth / 2;
        groundPosition2.x = (camera.position.x - camera.viewportWidth / 2) + ground.getWidth();
        if(camera.position.x - (camera.viewportWidth)/2 > groundPosition1.x + ground.getWidth()) {
            groundPosition1.add(ground.getWidth()*2,0);
        }
        if(camera.position.x - (camera.viewportWidth)/2 > groundPosition2.x + ground.getWidth()) {
            groundPosition2.add(ground.getWidth()*2,0);
        }
    }

    public Texture getBackground() {
        return background;
    }
}
