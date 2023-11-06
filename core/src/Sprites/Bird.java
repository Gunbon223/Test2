package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird implements ISprites{
    private static int birdSpeed ;
    // di chuyen 120px moi 1s
    private static final float INCREASE_TIME = 1f;
    private static final float INCREASE_AMOUNT = 1f;

    private float timeElapsed;
    private Vector3 position; // vi tri x y
    private Vector3 velocity;
    private float GRAVITY = (float) -9.8;
    private Texture birdImg;
    private Rectangle birdRectangle;
    private Animation birdAnimation ;

    public Bird(int x,int y) {
        birdImg = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(birdImg) ,3 ,0.8f);
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        birdRectangle = new Rectangle(x, y, birdImg.getWidth()/3,birdImg.getHeight());
        timeElapsed = 0f;
        birdSpeed = 120;
    }
    @Override
    public void update(float deltaTime) {
        birdAnimation.update(deltaTime);
        //Tinh thoi gian
        timeElapsed += deltaTime;
        //Tang van toc dua tren thoi gian
        if (timeElapsed >= INCREASE_TIME) {
            birdSpeed += INCREASE_AMOUNT;
            timeElapsed = 0f;
        }
        velocity.add(0, GRAVITY, 0);
        velocity.scl(deltaTime);
        position.add(birdSpeed * deltaTime, velocity.y, 0);
        velocity.scl(1 / deltaTime);
        birdRectangle.setPosition(position.x, position.y);
    }
    public void jump() {
        velocity.y=400;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBirdImg() {
        return birdAnimation.getFrame();
    }

    public Rectangle getBirdRectangle() {
        return birdRectangle;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }




    public void dispose() {
        birdImg.dispose();
    }
}
