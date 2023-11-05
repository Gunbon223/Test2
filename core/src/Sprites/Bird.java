package Sprites;

import GameState.PlayState;
import Utility.Constrain;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int BIRD_MOVE = 120;
    private Vector3 position; // vi tri x y
    private Vector3 velocity;
    private float GRAVITY = (float) -9.8;
    private Texture birdImg;
    private Rectangle birdRectangle;
    public Bird(int x,int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        birdImg = new Texture("bird.png");
        birdRectangle = new Rectangle(position.x, position.y, birdImg.getWidth(),birdImg.getWidth());
    }

    public void update(float deltaTime) {
            velocity.add(0, GRAVITY, 0);
            velocity.scl(deltaTime);
            position.add(BIRD_MOVE*deltaTime, velocity.y, 0);
//        if (position.y<0) {
//            position.y = 0;
//        }
        velocity.scl(1 / deltaTime);
        birdRectangle.setPosition(position.x, position.y);
    }
    public void jump() {
        velocity.y=400;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBirdImg() {
        return birdImg;
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
