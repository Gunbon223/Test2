package GameState;

import Sprites.Bird;
import Sprites.Ground;
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
    private Bird bird;
    private Texture background;
    private Ground ground;
    private  ArrayList<Tube> tubes;
    private ArrayList<Ground> grounds;

    public final float scale = 1.5f;
    public PlayState(StateManager stateManager) {
        super(stateManager);
        camera.setToOrtho(false, (float) (Constrain.WIDTH/scale), (float) (Constrain.HEIGHT/scale));
        bird = new Bird(30,50);
        background = new Texture("background.png");
//        ground = new Ground(camera);
        tubes = new ArrayList<>();
        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)+600));
        }
        grounds = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            grounds.add(new Ground(camera,i));
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
        ground.updateGround(camera);

        // bird position
        bird.update(deltaTime);
        camera.position.x=bird.getPosition().x+90;
        // ground add position
        // tube repostion when out of left screen to the right screen
        for(Tube i :tubes) {
            if (camera.position.x - (camera.viewportWidth/2) > i.getPosition().x + i.getImg().getWidth() ) {
                i.reposition(i.getPosition().x+((Tube.TUBE_WIDTH+TUBE_SPACING)*TUBE_COUNT));
            }
            if(i.collision(bird.getBirdRectangle())) {
                stateManager.setStates(new MenuState(stateManager));
                break;
            }
        }
        //bird max and min height  {
        if(bird.getPosition().y<=(ground.getGroundImg().getHeight()+ Ground.GROUND_Y_OFFSCREEN)) {
            bird.setPosition(new Vector3(bird.getPosition().x,ground.getGroundImg().getHeight()+Ground.GROUND_Y_OFFSCREEN,0));
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
            spriteBatch.draw(i.getImg(),i.getPosition().x,i.getPosition().y);
            spriteBatch.draw(i.getImg2(),i.getPosition2().x,i.getPosition2().y);
        }
        //render ground
        spriteBatch.draw(ground.getGroundImg(),ground.getGroundPosition1().x,ground.getGroundPosition1().y);
        spriteBatch.draw(ground.getGroundImg(),ground.getGroundPosition2().x,ground.getGroundPosition2().y);
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
