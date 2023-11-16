package Sprites;

import Utility.Constrain;
import Utility.ISprites;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bird implements ISprites<TextureRegion,Vector2> {
    private static int birdSpeed ;
    private Sound flapSoundEffect;
    private Vector2 position; // vi tri x y
    private Vector2 velocity;
    private Texture birdImg;
    private Rectangle birdRectangle;
    private Animation birdAnimation ;

    public Bird(int positionX,int positionY) {
        birdImg = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(birdImg) ,3 ,0.7f);
        position = new Vector2(positionX,positionY);
        velocity = new Vector2(0,0);
        birdRectangle = new Rectangle(positionX, positionY, birdImg.getWidth()/3,birdImg.getHeight());
        birdSpeed = Constrain.START_BIRD_SPEED;
        flapSoundEffect = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float deltaTime) {
        birdAnimation.update(deltaTime);
        velocity.add(0, Constrain.GRAVITY);
        velocity.scl(deltaTime);
        position.add(birdSpeed * deltaTime, velocity.y);
        velocity.scl(1 / deltaTime);
        birdRectangle.setPosition(position.x, position.y);
    }

    public void jump() {
        flapSoundEffect.play(0.3f);
        velocity.y=Constrain.JUMP_VELOCITY;
    }

    public Rectangle getBirdRectangle() {
        return birdRectangle;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void dispose() {
        birdImg.dispose();
        flapSoundEffect.dispose();
    }
    @Override
    public TextureRegion getImg() {
        return birdAnimation.getFrame();
    }
    public Vector2 getPosition() {
        return position;
    }
    public Animation getBirdAnimation() {
        return birdAnimation;
    }
}
