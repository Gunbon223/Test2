package GameState;

import Sprites.Bird;
import Sprites.Ground;
import Sprites.Tube;
import Utility.Constrain;
import Utility.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.*;

public class PlayState extends State{
    private Bird bird;
    private Texture background;
    private  ArrayList<Tube> tubes;
    private ArrayList<Ground> grounds;
    private HUD hud;
    private  int score;


    public PlayState(StateManager stateManager) {
        super(stateManager);
        camera.setToOrtho(false, (float) (Constrain.WIDTH/Constrain.SCALE), (float) (Constrain.HEIGHT/Constrain.SCALE));
        bird = new Bird(Constrain.START_POSITION_X,Constrain.START_POSITION_Y);
        background = new Texture("background.png");
        tubes = new ArrayList<>();
        for (int i = 0; i < Constrain.TUBE_COUNT; i++) {
            tubes.add(new Tube(i*(Constrain.TUBE_SPACING+Constrain.TUBE_WIDTH)+Constrain.TUBE_START_POSITION_X));
        }
        grounds = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            grounds.add(new Ground(camera,i));
        }
        score =0;
        System.out.println("ps create");
        hud = new HUD(25, Color.WHITE,camera.viewportWidth, camera.viewportHeight);
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
        camera.position.x=bird.getPosition().x+(bird.getImg().getRegionWidth()/3);
        for(Tube i :tubes) {
            if (camera.position.x - (camera.viewportWidth/2) > i.getPosition().x + i.getImg().getWidth() ) {
                i.reposition(i.getPosition().x + ((Constrain.TUBE_WIDTH + Constrain.TUBE_SPACING) * Constrain.TUBE_COUNT));
                i.setScored(false);
            }

            if(i.collision(bird.getBirdRectangle())) {
                stateManager.setStates(new MenuState(stateManager));
                break;
            }
            //score
            if (bird.getPosition().x > i.getPosition().x + i.getImg().getWidth() && !i.isScored()) {
                score++;
                i.setScored(true);
            }
        }
        //bird max/min height
        if(bird.getPosition().y<=(grounds.get(0).getImg().getHeight()+ Constrain.GROUND_Y_OFFSCREEN)) {
            bird.setPosition(new Vector2(bird.getPosition().x,grounds.get(0).getImg().getHeight()+Constrain.GROUND_Y_OFFSCREEN));
            stateManager.setStates(new MenuState(stateManager));

        }
        if (bird.getPosition().y>=background.getHeight()+bird.getImg().getRegionHeight()*1.5) {
            bird.setPosition(new Vector2(bird.getPosition().x, (float) (background.getHeight()+bird.getImg().getRegionHeight()*1.5)));
        }
        for(Ground i:grounds) {
            if (camera.position.x - (camera.viewportWidth/2) > i.getPosition().x + i.getImg().getWidth() ) {
                i.updateGround(i.getPosition().x +(i.getImg().getWidth()*3));
            }
        }
        hud.setHudCentreX(camera.position.x - hud.getFont().getLineHeight());
        camera.update();
    }
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0, Constrain.CAMERA_WIDTH, Constrain.CAMERA_HEIGHT);
        spriteBatch.draw(bird.getImg(),bird.getPosition().x ,bird.getPosition().y);
        for (Tube i :tubes) {
            spriteBatch.draw(i.getImg(),i.getPosition().x,i.getPosition().y);
            spriteBatch.draw(i.getImgBot(),i.getPositionBot().x,i.getPositionBot().y);
        }
        for (Ground i:grounds) {
            spriteBatch.draw(i.getImg(),i.getPositionRectangle().x,i.getPosition().y);
        }
        hud.getFont().draw(spriteBatch, "Score", hud.getHudCentreX(), hud.getHudRowTop(),hud.getHudWidth() , Align.left, false);
        hud.getFont().draw(spriteBatch, Integer.toString(score), hud.getHudCentreX()+hud.getFont().getLineHeight(), hud.getHudRowBottom(), hud.getHudWidth(), Align.left, false);
        spriteBatch.end();

    }
    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for (Tube i: tubes) {
            i.dispose();
        }
        for (Ground i: grounds) {
            i.dispose();
        }
    }



}